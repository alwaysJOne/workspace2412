package com.kh.login.service;

import com.kh.login.auth.JwtTokenProvider;
import com.kh.login.domain.Member;
import com.kh.login.enums.SocialType;
import com.kh.login.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoogleOauth2LoginSuccess extends SimpleUrlAuthenticationSuccessHandler {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    //Oayth2 인증에 설공했을 때 호출
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        //OAuth2User는 인증된 사용자의 정보를 담는 객체(Goole에서 반환한 사용자 json포함)
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        /* 만약 여러가지 플랫폼을 통해서 oauth2인증을 진행시 아래코드로 분기처리 후 사용
        String registrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId()
                .toUpperCase();
        SocialType socialType = SocialType.valueOf(registrationId); //google 또는 kakao
        */

        //Google에서 제공하는 정보 추출
        String openId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");

        Member member = memberRepository.findBySocialIdAndSocialType(openId, SocialType.GOOGLE).orElse(null);
        if (member == null) {
            member = Member.builder()
                    .socialId(openId)
                    .email(email)
                    .name(oAuth2User.getAttribute("name") != null ? oAuth2User.getAttribute("name") : "Google User")
                    .password("")
                    .phone(null)
                    .socialType(SocialType.GOOGLE)
                    .build();
            memberRepository.save(member);
        }

        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());

        Cookie jwtCookie = new Cookie("token", jwtToken);
        jwtCookie.setPath("/"); //모든 경로에서 쿠키 사용가능
        response.addCookie(jwtCookie);
        response.sendRedirect("http://localhost:3000");

    }

}
