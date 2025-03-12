package com.kh.boot.mappers;

import com.kh.boot.domain.vo.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;

@Mapper
public interface BoardMapper {
    int selectBoardCount();
    //게시글 목록 조회
    ArrayList<Board> selectBoardList(RowBounds rowBounds);
    //게시글 추가
    int insertBoard(Board board);
}
