package com.kh.board.service;

import com.kh.board.entity.Board;
import java.util.List;

public interface BoardService {
    Board findOne(Long boardId);
    List<Board> findAll();
}
