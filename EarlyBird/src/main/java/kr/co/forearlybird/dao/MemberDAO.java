package kr.co.forearlybird.dao;

import java.util.Map;

public interface MemberDAO {
	// 게시글 개수
	public int maxNum();
	
	public static Map login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	// 게시판 목록보기
//	public List<Board> list();
	
	// 게시판 상세정보를 처리
//	public int updateReadcnt(int bno);
//	public Board getDetail(int bno);
	
	// 글쓰기 정보처리
//	public int register(Board board);
	
	//글삭제 정보처리
//	public int delete(int bno);
	
	// 글수정 정보처리
//	public int update(int bno);
}
