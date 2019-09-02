package kr.co.forearlybird.domain;

import java.util.Date;

import lombok.Data;

//Lombok을 이용함(getter,setter 등등 한번에 처리 가능)
@Data
public class Content {
	private int cnt_id; //PK
	private int brd_id; //게시판 테이블의 ( Board Table )의 PK
	private String cnt_title; //게시글 제목
	private String cnt_thumbnail; //첨부된 섬네일 링크
	private String cnt_connectLink; //게시글 본문 내용
	private int cnt_hit; //조회수 
	private int cnt_like; //추천 회수
	private Date cnt_datetime; //게시물 작성 일자
	private int cnt_del; //게시물 삭제 여부
}
