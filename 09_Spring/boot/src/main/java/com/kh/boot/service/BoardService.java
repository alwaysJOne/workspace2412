package com.kh.boot.service;

import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.PageInfo;

import java.util.ArrayList;

public interface BoardService {
    //총게시글 수
    int selectBoardCount();
    //게시글 정보(페이징)
    ArrayList<Board> selectBoardList(PageInfo pi);
}
