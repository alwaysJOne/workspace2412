package com.kh.boot.mappers;

import com.kh.boot.domain.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
@Mapper : MyBatis의 매퍼 인터페이스를 정의할 때 사용하는 어노테이션
          스프링Bean으로 등록하여 의존성 주입이 가능하게 만들어 준다.
 */
@Mapper
public interface MemberMapper {
    Member loginMember(@Param("userId") String userId);
    int insertMember(Member member);
    int idCheck(@Param("userId") String userId);
}
