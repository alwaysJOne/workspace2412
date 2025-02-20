package com.kh.jdbc.service;

//static으로 import시 static메서드를 직접 가져올 수 있음
import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jdbc.model.dao.MemberDao;
import com.kh.jdbc.model.vo.Member;

public class MemberService {
	//1) JDBC driver등록
	//2) Connection객체 생성
	//3) Connection객체 처리
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(m, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public ArrayList<Member> selectList() {
		Connection conn = getConnection();
		ArrayList<Member> list = new MemberDao().selectList(conn);
		close(conn);
		
		return list;
	}

	public int deleteMember(String userId) {
		Connection conn = getConnection();
		int result = new MemberDao().deleteMember(userId, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int updateMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(m, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public ArrayList<Member> selectByUserName(String keyword){
		Connection conn = getConnection();
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword, conn);
		
		close(conn);
		return list;
	}
}
