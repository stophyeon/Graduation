package org.example.dto.member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberDto implements MemberInterface{
    @Email
    protected String email;
    protected String userName;
    protected String password;
    protected String profileImage;
    protected String nickName;
    protected int socialType;
    protected String memberInfo;
    protected String role;

    @Builder
    public MemberDto(String role,String memberInfo,String email, String nickName, String profileImage, String userName, String password, int socialType){
        this.email=email;
        this.nickName=nickName;
        this.profileImage = profileImage;
        this.userName=userName;
        this.password=password;
        this.socialType = socialType;
        this.memberInfo= memberInfo;
        this.role=role;
    }


}
