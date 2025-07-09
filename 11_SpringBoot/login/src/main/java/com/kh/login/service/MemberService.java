package com.kh.login.service;

import com.kh.login.domain.Member;
import com.kh.login.dto.MemberCreateDto;
import com.kh.login.dto.MemberLoginDto;
import com.kh.login.dto.MemberResponseDto;
import com.kh.login.enums.SocialType;
import com.kh.login.exception.UserAleadyExistsException;
import com.kh.login.exception.UserNotFoundException;
import com.kh.login.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(MemberCreateDto memberCreateDto) {
        //이메일중복검사
        if (memberRepository.existsByEmail(memberCreateDto.getEmail())) {
            throw new UserAleadyExistsException("이미 존재하는 이메일입니다.");
        }
        //전화번호 중복검사
        if (memberRepository.existsByPhone(memberCreateDto.getPhone_number())) {
            throw new UserAleadyExistsException("이미 존재하는 전화번호입니다.");
        }

        //생성
        Member member = Member.builder()
                .name(memberCreateDto.getName())
                .email(memberCreateDto.getEmail())
                .password(passwordEncoder.encode(memberCreateDto.getPassword()))
                .phone(memberCreateDto.getPhone_number())
                .build();

        memberRepository.save(member);
        return member;
    }

    public Member login(MemberLoginDto loginDto) {
        Optional<Member> optMember = memberRepository.findByEmail(loginDto.getEmail());
        if (!optMember.isPresent()) {
            throw new UserNotFoundException("이메일이 존재하지 않습니다.");
        }

        Member m = optMember.get();
        if (!passwordEncoder.matches(loginDto.getPassword(), m.getPassword())) {
            throw new UserNotFoundException("비밀번호가 일치하지 않습니다.");
        }
        return m;
    }

    public MemberResponseDto getMemberInfoByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("회원정보를 찾을 수 없습니다."));
        return MemberResponseDto.from(member);
    }

    public Member getMemberBySocialId(String socialId, SocialType socialType) {
        Member member = memberRepository.findBySocialIdAndSocialType(socialId, socialType).orElse(null);
        return member;
    }

    public Member createOauth(String socialId, String email, String name, SocialType socialType) {
        Member member = Member.builder()
                .email(email)
                .name(name)
                .password("")
                .phone(null)
                .socialType(socialType)
                .socialId(socialId)
                .build();

        memberRepository.save(member);
        return member;
    }


}
