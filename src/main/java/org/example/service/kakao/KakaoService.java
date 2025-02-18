package org.example.service.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;
import org.example.dto.member.MemberDto;
import org.example.dto.send.TemplateObject;
import org.example.entity.Member;
import org.example.entity.Token;
import org.example.jwt.JwtDto;
import org.example.jwt.JwtProvider;
import org.example.jwt.KakaoToken;
import org.example.repository.member.MemberRepository;
import org.example.repository.token.TokenRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoService {
    private final KakaoApi kakaoApi;
    private final KakaoFeign kakaoFeign;
    private final MemberRepository memberRepository;
    private final AuthenticationProvider authenticationProvider;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtProvider jwtProvider;

    private final String Content_type ="application/x-www-form-urlencoded;charset=utf-8";
    private final String grant_type = "authorization_code";
    private final String client_id = "53e2138a4604fecace12418c569e9753";
    private final String login_redirect ="http://192.168.23.102:32319/user/login/oauth2/kakao";
    private final String logout_redirect ="http://192.168.23.102:32319";
    private final String secret ="O1o1d7oxGIq1tTjak2wIU3b9ivPgxe5h";
    private KakaoToken kakaoToken_user;

    public JwtDto GenerateToken(String code) throws ParseException, IOException, org.json.simple.parser.ParseException {
        String email = OAuthSignUp(code);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,"default1234");
        Authentication authentication = authenticationProvider.authenticate(token);
        JwtDto jwtDto = jwtProvider.createToken(authentication);
        tokenRepository.save(Token.builder().refreshToken(jwtDto.getRefreshToken()).email(email).build());
        return jwtDto;
    }

    public KakaoToken getToken(String code) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoToken kakaoToken=objectMapper.readValue(kakaoFeign.getAccessToken(Content_type,grant_type,client_id,login_redirect,code,secret), KakaoToken.class);
        log.info(kakaoToken.toString());
        kakaoToken_user=kakaoToken;
        return kakaoToken;
    }

    public String getkakaoInfo(String code) throws ParseException, JsonProcessingException {
        return kakaoApi.getUSerInfo("Bearer "+getToken(code).getAccessToken());
    }

    @Transactional
    public String OAuthSignUp(String code) throws ParseException, IOException, org.json.simple.parser.ParseException {
        String user = getkakaoInfo(code);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(user);
        JSONObject kakaoAccount=(JSONObject) jsonObject.get("kakao_account");
        JSONObject properties=(JSONObject) jsonObject.get("properties");
        MemberDto memberDto =MemberDto.builder()
                .email(kakaoAccount.get("email").toString())
                .profileImage(properties.get("profile_image").toString())
                .nickName(properties.get("nickname").toString())
                .userName(properties.get("nickname").toString())
                .password(passwordEncoder.encode("default1234"))
                .memberInfo("안녕하세요 신규 회원입니다.")
                .socialType(1)
                .role("ROLE_TEACHER")
                .build();
        Optional<Member> member = memberRepository.findByEmail(memberDto.getEmail());
        Member member1 = Member.builder()
                .memberDto(memberDto)
                .build();
        if (member.isEmpty()){
            log.info(member1.getRole());
            memberRepository.save(member1);
        }
       else {memberRepository.updateInfo(member1);}
       log.info("User DB 저장");

       return memberDto.getEmail();
    }

    public void sendRealImage(TemplateObject templateObject) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String template = "template_object="+objectMapper.writeValueAsString(templateObject);;

        kakaoApi.sendImage("Bearer "+ kakaoToken_user.getAccessToken(),"application/x-www-form-urlencoded",template);
    }
    public String kakaoLogOut(){
        return kakaoFeign.logOut(client_id,logout_redirect);
    }
}
