package com.kh.jpa.repository;

import com.kh.jpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository // 해당 클레스는 DB와 관련된 작업을 수행하는 클래스다.
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext // EntityManager를 주입해줘.
    private EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member); // 영속
    }

    public Optional<Member> findOne(String userId) {
        return Optional.ofNullable(em.find(Member.class, userId));
    }
}
