package com.kh.jpa.service;

import com.kh.jpa.dto.MemberDto;
import com.kh.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    //createDto : 사용자가 입력한 회원정보
    public String createMember(MemberDto.Create createDto) {
        memberRepository.save();
        return null;
    }
}
