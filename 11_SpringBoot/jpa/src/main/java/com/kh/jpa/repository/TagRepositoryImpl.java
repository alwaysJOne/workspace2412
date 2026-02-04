package com.kh.jpa.repository;

import com.kh.jpa.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Tag 엔티티의 데이터베이스 작업을 처리하는 Repository 구현 클래스
 * EntityManager를 사용하여 JPQL 기반 쿼리 실행
 */
@Repository
public class TagRepositoryImpl {

    @PersistenceContext // EntityManager 주입
    private EntityManager em;

    /**
     * 태그 저장 (영속화)
     * @param tag 저장할 태그 엔티티
     * @return 저장된 태그 엔티티
     */
    public Tag save(Tag tag) {
        em.persist(tag);
        return tag;
    }

    /**
     * 태그 ID로 조회
     * @param tagId 조회할 태그 ID
     * @return Optional로 감싼 태그 엔티티
     */
    public Optional<Tag> findById(Long tagId) {
        return Optional.ofNullable(em.find(Tag.class, tagId));
    }

    /**
     * 태그 이름으로 조회
     * @param tagName 조회할 태그 이름
     * @return Optional로 감싼 태그 엔티티
     */
    public Optional<Tag> findByTagName(String tagName) {
        String query = "select t from Tag t where t.tagName = :tagName";
        List<Tag> tags = em.createQuery(query, Tag.class)
                .setParameter("tagName", tagName)
                .getResultList();

        // 결과가 없으면 Optional.empty(), 있으면 첫 번째 결과 반환
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    /**
     * 모든 태그 조회
     * @return 모든 태그 리스트
     */
    public List<Tag> findAll() {
        return em.createQuery("select t from Tag t", Tag.class)
                .getResultList();
    }

    /**
     * 태그 삭제
     * @param tag 삭제할 태그 엔티티
     */
    public void delete(Tag tag) {
        em.remove(tag);
    }
}
