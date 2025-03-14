package com.kh.boot.service;

import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.domain.vo.Reply;
import com.kh.boot.mappers.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public int selectBoardCount() {
        return boardMapper.selectBoardCount();
    }

    @Override
    public ArrayList<Board> selectBoardList(PageInfo pi) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return boardMapper.selectBoardList(rowBounds);
    }

    @Override
    public int insertBoard(Board board) {
        return boardMapper.insertBoard(board);
    }

    @Override
    public Board selectBoard(int boardNo) {
        return boardMapper.selectBoard(boardNo);
    }

    @Override
    public int increaseCount(int boardNo) {
        return boardMapper.increaseCount(boardNo);
    }

    @Override
    public int insertReply(Reply reply) {
        return boardMapper.insertReply(reply);
    }

    @Override
    public ArrayList<Reply> selectReplyList(int boardNo) {
        return boardMapper.selectReplyList(boardNo);
    }

    @Transactional
    @Override
    public ArrayList<Board> getBoardTopN(String order, int limit) {
        RowBounds rowBounds = new RowBounds(0, limit);
        return boardMapper.getBoardTopN(rowBounds, order);
    }

    @Override
    public int updateBoard(Board board) {
        return boardMapper.updateBoard(board);
    }


}
