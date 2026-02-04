package com.kh.jpa.repository;

import com.kh.jpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Member 엔티티의 데이터베이스 작업을 처리하는 Repository 구현 클래스
 * EntityManager를 사용하여 JPQL 기반 쿼리 실행
 */
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext // EntityManager 주입
    private EntityManager em;

    /**
     * 전체 회원 조회
     */
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    /**
     * 이름으로 회원 검색 (LIKE 검색)
     */
    @Override
    public List<Member> findByName(String name) {
        String query = "select m from Member m where m.userName LIKE :username";
        return em.createQuery(query, Member.class)
                .setParameter("username", "%" + name + "%")
                .getResultList();
    }

    /**
     * 회원 저장 (영속화)
     */
    @Override
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * 회원 ID로 단건 조회
     */
    @Override
    public Optional<Member> findOne(String userId) {
        return Optional.ofNullable(em.find(Member.class, userId));
    }

    /**
     * 회원 삭제
     */
    @Override
    public void delete(Member member) {
        em.remove(member);
    }
}
