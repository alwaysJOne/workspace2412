package com.kh.boot.service;

import com.kh.boot.domain.vo.Member;
import org.springframework.stereotype.Service;

@Service //@Component보다 더 구체화해서 service객체에 알맞게 bean에 등록
public class MemberServiceImpl implements MemberService {

    @Override
    public Member loginMember(Member m) {
        return null;
    }
}
