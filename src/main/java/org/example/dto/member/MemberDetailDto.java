package org.example.dto.member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberDetailDto extends MemberDto{
    private int point;
    private int follower;
    private int following;

    @Builder
    public MemberDetailDto(String role,String memberInfo,String email, String nickName, String profileImage, String userName, String password,int point, int follower, int following, int socialType){
        super(role, memberInfo, email, nickName, profileImage, userName, password, socialType);
        this.point = point;
        this.follower = follower;
        this.following = following;
    }

    public static MemberDetailDtoBuilder builder() {
        return new MemberDetailDtoBuilder();
    }

    public static class MemberDetailDtoBuilder extends MemberDto.MemberDtoBuilder {
        @Override
        public MemberDetailDto build() {
            MemberDetailDto memberDetailDto = new MemberDetailDto(
                    role, memberInfo, email, nickName, profileImage, userName, password,
                    follower, following, point, socialType
            );
            return memberDetailDto;
        }
    }
}
