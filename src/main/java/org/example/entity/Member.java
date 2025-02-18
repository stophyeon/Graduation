package org.example.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.dto.member.MemberDetailDto;
import org.example.dto.member.MemberDto;
import org.hibernate.annotations.ColumnDefault;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(unique = true)
    private String email;
    private String userName;
    private String password;
    @Column(unique = true)
    private String nickName;
    private String profileImage;
    @ColumnDefault("0")
    private int follower;
    @ColumnDefault("0")
    private int following;
    private int point;
    private String role;
    private int socialType;//0일반 1카카오
    private String memberInfo;

    @Builder
    public Member(MemberDto memberDto){
        this.email=memberDto.getEmail();
        this.password= memberDto.getPassword();
        this.nickName= memberDto.getNickName();
        this.userName= memberDto.getUserName();
        this.profileImage =memberDto.getProfileImage();
        this.socialType = memberDto.getSocialType();
        this.memberInfo=memberDto.getMemberInfo();
        this.role=memberDto.getRole();
    }

    @Builder
    public Member(MemberDetailDto memberDto){
        this.email=memberDto.getEmail();
        this.password= memberDto.getPassword();
        this.nickName= memberDto.getNickName();
        this.userName= memberDto.getUserName();
        this.profileImage =memberDto.getProfileImage();
        this.point= memberDto.getPoint();
        this.socialType = memberDto.getSocialType();
        this.memberInfo=memberDto.getMemberInfo();
        this.role=memberDto.getRole();
    }

    public static MemberDetailDto toDto(Member member){
        return MemberDetailDto.builder()
                .email(member.getEmail())
                .userName(member.getUserName())
                .nickName(member.getNickName())
                .profileImage(member.getProfileImage())
                .follower(member.follower)
                .following(member.following)
                .point(member.getPoint())
                .memberInfo(member.getMemberInfo())
                .role(member.getRole())
                .build();
    }


}
