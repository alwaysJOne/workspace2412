package com.kh.boot.service;

import com.kh.boot.domain.vo.Member;
import com.kh.boot.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //@Component보다 더 구체화해서 service객체에 알맞게 bean에 등록
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Member loginMember(String userId) {
        return memberMapper.loginMember(userId);
    }

    @Override
    public int insertMember(Member m) {
        return memberMapper.insertMember(m);
    }

    @Override
    public int idCheck(String userId) {
        return memberMapper.idCheck(userId);
    }
}
