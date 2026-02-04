package com.kh.board.service;

import com.kh.board.entity.Board;
import com.kh.board.entity.Member;
import com.kh.board.mapper.BoardMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public Board findOne(Long boardId) {return boardMapper.findOne(boardId);}

    @Override
    public List<Board> findAll() {return boardMapper.findAll();}

    @Override
    public int save(Board board) {return boardMapper.save(board);}

    @Override
    public int delete(Long boardId) { return boardMapper.delete(boardId); }

    @Override
    public Long update(Board board) {
        boardMapper.update(board);
        return board.getBoardId();
    }

    @Override
    public Long patch(Long boardId, String title, String contents, String fileName) {
        Board existingBoard = boardMapper.findOne(boardId);

        if (existingBoard == null) {
            throw new RuntimeException("Board not found");
        }

        existingBoard.patch(title, contents, fileName);

        boardMapper.update(existingBoard);
        return existingBoard.getBoardId();
    }

    @Override
    public Member findMemberByUserId(String userId) {
        // This is a placeholder implementation. In a real application,
        // you would retrieve the Member from a database or another service.
        Member member = new Member();
        member.setEmail(userId);
        return member;
    }
}
