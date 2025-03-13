package com.kh.boot.mappers;

import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;

@Mapper
public interface BoardMapper {
    int selectBoardCount();
    //게시글 목록 조회
    ArrayList<Board> selectBoardList(RowBounds rowBounds);
    //게시글 추가
    int insertBoard(Board board);
    int increaseCount(@Param("bno") int boardNo);
    Board selectBoard(@Param("bno") int boardNo);
    //댓글추가
    int insertReply(Reply reply);
    //댓글목록 가져오기
    ArrayList<Reply> selectReplyList(@Param("boardNo") int boardNo);
    ArrayList<Board> getBoardTopN(RowBounds rowBounds,@Param("order") String order);
    //게시글 수정
    int updateBoard(Board board);
}
