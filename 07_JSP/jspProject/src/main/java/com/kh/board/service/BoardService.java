package com.kh.board.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.vo.PageInfo;

public class BoardService {
	public int selectListCount() {
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(PageInfo pi){
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Category> selectCategory(){
		Connection conn = getConnection();
		
		ArrayList<Category> list = new BoardDao().selectCategory(conn);
		
		close(conn);
		
		return list;
	}
}
