package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.member.MemberDto;
import org.example.dto.signup.SignUpRes;
import org.example.repository.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberValidationService {
    private final MemberRepository memberRepository;


    public SignUpRes validateEmailAndNickName(MemberDto memberDto){
        if(duplicateEmail(memberDto.getEmail())){
            return SignUpRes.builder().message("이미 가입된 회원입니다").state("중복 가입").build();
        }
        if (checkNickNameLength(memberDto.getNickName())){
            return SignUpRes.builder().message("닉네임 길이는 10글자보다 짧아야합니다.").state("중복 닉네임").build();
        }
        if (duplicateNickName(memberDto.getNickName())) {
            return SignUpRes.builder().message("이미 사용 중인 닉네임입니다").state("중복 닉네임").build();
        }
        return null;
    }

    public boolean duplicateNickName(String nickName){
        return memberRepository.findByNickName(nickName).isPresent();
    }
    public boolean duplicateEmail(String email){
        return memberRepository.existsByEmail(email);
    }
    public boolean checkNickNameLength(String nick_name){
        return nick_name.length()>=10;
    }
}
