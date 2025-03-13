package com.kh.boot.service;

import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.domain.vo.Reply;

import java.util.ArrayList;

public interface BoardService {
    //총게시글 수
    int selectBoardCount();
    //게시글 정보(페이징)
    ArrayList<Board> selectBoardList(PageInfo pi);
    //게시글 추가
    int insertBoard(Board board);
    //게시글 가져오기
    Board selectBoard(int boardNo);
    //게시글 조회수 + 1
    int increaseCount(int boardNo);
    //댓글 추가
    int insertReply(Reply reply);
    //댓글목록 가져오기
    ArrayList<Reply> selectReplyList(int boardNo);
    //게시글 조회수 TOP5
    ArrayList<Board> getBoardTopN(String order, int limit);
    //게시글 수정
    int updateBoard(Board board);
}
