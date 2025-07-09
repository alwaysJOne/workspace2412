package com.kh.login.controller;

import com.kh.login.auth.JwtTokenProvider;
import com.kh.login.domain.Member;
import com.kh.login.dto.AccessTokenDto;
import com.kh.login.dto.KakaoProfileDto;
import com.kh.login.dto.MemberCreateDto;
import com.kh.login.dto.MemberLoginDto;
import com.kh.login.dto.MemberResponseDto;
import com.kh.login.dto.RedirectDto;
import com.kh.login.enums.SocialType;
import com.kh.login.service.KakaoService;
import com.kh.login.service.MemberService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoService kakaoService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody MemberCreateDto memberCreateDto) {
        Member member = memberService.create(memberCreateDto);
        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody MemberLoginDto memberLoginDto) {
        Member member = memberService.login(memberLoginDto);

        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("token", jwtToken);
        return new ResponseEntity<>(loginInfo, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMemberInfo() {
        //jwt토큰에서 이메일 추출
        String email = jwtTokenProvider.getUserEmailFromToken();
        MemberResponseDto memberInfo = memberService.getMemberInfoByEmail(email);
        return new ResponseEntity<>(memberInfo, HttpStatus.OK);
    }

    @PostMapping("/kakao/login")
    public ResponseEntity<?> kakaoLogin(@RequestBody RedirectDto redirectDto) {
        AccessTokenDto accessTokenDto = kakaoService.getAccessToken(redirectDto.getCode());
        KakaoProfileDto kakaoProfileDto = kakaoService.getKakaoProfile(accessTokenDto.getAccess_token());
        Member originMember = memberService.getMemberBySocialId(kakaoProfileDto.getId(), SocialType.KAKAO);
        if (originMember == null) { //가입된 기록이 없음 회원가입해야함
            originMember = memberService.createOauth(
                    kakaoProfileDto.getId(),
                    kakaoProfileDto.getKakao_account().getEmail(),
                    kakaoProfileDto.getKakao_account().getProfile().getNickname(),
                    SocialType.KAKAO
            );
        }

        String jwtToken = jwtTokenProvider.createToken(originMember.getEmail(), originMember.getRole().toString());
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("token", jwtToken);
        return new ResponseEntity<>(loginInfo, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getMemberList() {
        List<MemberResponseDto> dtos = memberService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
