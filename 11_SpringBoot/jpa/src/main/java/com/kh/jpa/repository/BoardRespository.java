package com.kh.jpa.repository;

import com.kh.jpa.entity.Board;
import com.kh.jpa.enums.CommonEnums;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Board Repository 인터페이스
 * BoardRepositoryImpl에서 구현
 */
public interface BoardRespository {
    Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable);
    Optional<Board> findById(Long id);
    Board save(Board board);
    void delete(Board board);
}
