package com.kh.login.dto;

import com.kh.login.domain.Member;
import com.kh.login.enums.Role;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone_number;
    private Role role;
    private LocalDateTime created_at;

    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone_number(member.getPhone())
                .role(member.getRole())
                .created_at(member.getCreatedAt())
                .build();
    }
}
