package com.kh.jpa.dto;

import com.kh.jpa.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private String user_id;
        private String user_pwd;
        private String user_name;
        private String email;
        private Member.Gender gender;
        private String phone;
        private String address;
        private Integer age;

        public Member toEntity() {
            return Member.builder()
                         .userId(this.user_id)
                         .userPwd(this.user_pwd)
                         .userName(this.user_name)
                         .email(this.email)
                         .gender(this.gender)
                         .age(this.age)
                         .phone(this.phone)
                         .address(this.address)
                         .build();
        }

    }
}
