package com.kh.mybatis.board.service;

import java.util.ArrayList;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.PageInfo;

public interface BoardService {
	int selectListCount();
	ArrayList<Board> selectList(PageInfo pi);
}
