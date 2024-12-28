package org.example.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ProfileDto;
import org.example.dto.chat.ChatMember;
import org.example.dto.login.LoginSuccessDto;
import org.example.dto.member.MemberDto;
import org.example.dto.RefreshDto;
import org.example.dto.exception.ExceptionResponse;
import org.example.dto.signup.SignUpRes;
import org.example.entity.Member;
import org.example.entity.Token;
import org.example.jwt.JwtDto;
import org.example.jwt.JwtProvider;
import org.example.repository.follow.FollowRepository;
import org.example.repository.member.MemberRepository;
import org.example.repository.token.TokenRepository;
import org.example.service.storage.NcpStorageService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final FollowRepository followRepository;
    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;
    private final TokenRepository tokenRepository;
    private final NcpStorageService ncpStorageService;
    private final MemberValidationService memberValidationService;
    private final ProfileImageService profileImageService;

    @Transactional
    public SignUpRes join(MemberDto memberDto, MultipartFile profileImg) throws IOException {
            if(!profileImg.isEmpty()){
                String file_name=ncpStorageService.imageUpload(profileImg);
                memberDto.setProfileImage(file_name);
            }

            SignUpRes res=memberValidationService.validateEmailAndNickName(memberDto);
            if(res!=null) return res;

            memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
            memberDto.setSocialType(0);

            memberRepository.save(Member.builder()
                    .memberDto(memberDto)
                    .build());

            return SignUpRes.builder()
                    .message("회원가입 되었습니다")
                    .state("처리 성공")
                    .build();
    }

    public LoginSuccessDto login(MemberDto memberDto){
        Optional<Member> member = memberRepository.findByEmail(memberDto.getEmail());
        member.orElseThrow();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(memberDto.getEmail(),memberDto.getPassword());
        Authentication auth = authenticationProvider.authenticate(token);
        JwtDto jwtDto = jwtProvider.createToken(auth);
        tokenRepository.save(Token.builder().email(memberDto.getEmail()).refreshToken(jwtDto.getRefreshToken()).build());
        return LoginSuccessDto.builder()
                .message("로그인 성공")
                .jwtDto(jwtDto).
                build();
        }

    public RefreshDto refreshToken(String refreshToken){
        log.info(refreshToken);
        Token token = tokenRepository.findByRefreshToken(refreshToken);
        return RefreshDto.builder().access_token(jwtProvider.recreateToken(token.getEmail())).build();
    }

    @Transactional
    public void deleteRefresh(String email){
        tokenRepository.deleteByEmail(email);
    }

    public MemberDto myProfile(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        member.orElseThrow();
        return Member.toDto(member.get());
    }

    public ProfileDto profile(String nickName, String email) {
        log.info(nickName);
        log.info(email);
        Optional<Member> member = memberRepository.findByNickName(nickName);
        Optional<Member> me = memberRepository.findByEmail(email);
        member.orElseThrow();
        if (followRepository.existsByFollowingIdAndFollowerId(member.get().getMemberId(),me.get().getMemberId())){
            return ProfileDto.builder().follow(true).memberDto(Member.toDto(member.get())).build();
        }
        else{return ProfileDto.builder().follow(false).memberDto(Member.toDto(member.get())).build();}

    }

    public String profileImg(String email){
        Optional<Member> member = memberRepository.findByEmail(email);

        return member.map(Member::getProfileImage).orElse(null);
    }

    public String getNickName(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.map(Member::getNickName).orElse(null);

    }

    public ExceptionResponse updateProfile(MultipartFile profileImg, MemberDto memberDto, String email) throws IOException {
        Member member = memberRepository.findByEmail(email).orElseThrow();

        String fileName = profileImageService.uploadProfileImage(profileImg);
        profileImageService.deleteProfileImage(member.getProfileImage());

        memberDto.setProfileImage(fileName);
        memberRepository.updateInfo(Member.builder().memberDto(memberDto).build());

        return ExceptionResponse.builder()
                .state("success")
                .message("회원 정보 수정에 성공했습니다.")
                .build();
    }

    public List<String> autoComplete(String word) {
        return memberRepository.findMemberKeyWord(word);
    }

    public String getEmail(String nickName){
        Optional<Member> member = memberRepository.findEmailByNickName(nickName);
        return member.map(Member::getEmail).orElse(null);
    }

    public ChatMember forChatting(String nickName){
        return memberRepository.findChatMemberByNickName(nickName);
    }
}
