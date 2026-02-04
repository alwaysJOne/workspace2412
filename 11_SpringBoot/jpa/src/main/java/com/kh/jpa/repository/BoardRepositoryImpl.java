package com.kh.jpa.repository;

import com.kh.jpa.entity.Board;
import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Board 엔티티의 데이터베이스 작업을 처리하는 Repository 구현 클래스
 * EntityManager를 사용하여 JPQL 기반 쿼리 실행
 */
@Repository
public class BoardRepositoryImpl implements BoardRespository {

    @PersistenceContext // EntityManager 주입
    private EntityManager em;

    /**
     * 상태값으로 게시글 조회 (페이징 처리)
     * @param status 조회할 게시글 상태 (Y: 활성, N: 비활성)
     * @param pageable 페이징 정보 (페이지 번호, 크기, 정렬 등)
     * @return Page로 감싼 게시글 리스트
     */
    @Override
    public Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable) {
        // 게시글 데이터 조회 (페이징 처리)
        String query = "select b from Board b where b.status=:status";
        List<Board> boards = em.createQuery(query, Board.class)
                .setParameter("status", status)
                .setFirstResult((int) pageable.getOffset()) // 시작 위치
                .setMaxResults(pageable.getPageSize()) // 조회할 개수
                .getResultList();

        // 전체 개수 조회 (페이징 정보용)
        String countQuery = "select count(b) from Board b where b.status=:status";
        Long totalCount = em.createQuery(countQuery, Long.class)
                .setParameter("status", status)
                .getSingleResult();

        // PageImpl로 페이징 정보와 함께 반환
        return new PageImpl<>(boards, pageable, totalCount);
    }

    /**
     * 게시글 ID로 조회
     * @param id 조회할 게시글 ID
     * @return Optional로 감싼 게시글 엔티티
     */
    @Override
    public Optional<Board> findById(Long id) {
        if(id == null) return Optional.empty();
        return Optional.ofNullable(em.find(Board.class, id));
    }

    /**
     * 게시글 저장 (영속화)
     * @param board 저장할 게시글 엔티티
     * @return 저장된 게시글 엔티티
     */
    @Override
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    /**
     * 게시글 삭제
     * @param board 삭제할 게시글 엔티티
     */
    @Override
    public void delete(Board board) {
        em.remove(board);
    }
}
