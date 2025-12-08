package com.kh.board.service;

import com.kh.board.entity.Board;
import com.kh.board.entity.Member;
import java.util.List;

public interface BoardService {
    Member findMemberByUserId(String userId);
    Board findOne(Long boardId);
    List<Board> findAll();
    int save(Board board);
    int delete(Long boardId);
    Long update(Board board);
}
