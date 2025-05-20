package com.kh.jpa.service;

import org.springframework.data.domain.Pageable;

public interface BoardService {
    void getBoardList(Pageable pageable);
}
