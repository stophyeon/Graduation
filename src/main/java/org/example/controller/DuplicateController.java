package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.MemberService;
import org.example.service.MemberValidationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DuplicateController {

    private final MemberValidationService memberValidationService;

    @GetMapping("/nick_name")
    public boolean checkNickName(@RequestParam("nick_name") String nickName){
        return memberValidationService.duplicateNickName(nickName);
    }

    @GetMapping("/email")
    public boolean checkEmail(@RequestParam("email") String email){
        return memberValidationService.duplicateEmail(email);
    }
}
