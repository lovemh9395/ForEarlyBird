package kr.co.forearlybird.domain;

import java.util.Date;				  
import lombok.Data;

//Lombok을 이용함(getter,setter 등등 한번에 처리 가능)
@Data
public class Post {
	private int post_id; //PK
	private int brd_id; //게시판 테이블 ( Board Table )의 PK
	private String mem_userid; //작성자 회원의 PK 
	private String post_title; //게시글 제목
	private String post_content; //게시글 본문 내용
	private Date post_datetime; //게시물 작성 일자
	private Date post_updated_datetime; //최종 수정 시간
	private int post_notice; //공지사항 여부
	private int post_hit; //조회수
	private int post_like; //추천 회수
	private int post_del; //게시물 삭제 여부
}
