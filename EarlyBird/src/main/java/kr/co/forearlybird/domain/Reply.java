package kr.co.forearlybird.domain;

import java.util.Date;

//Lombok을 이용함(getter,setter 등등 한번에 처리 가능)

public class Reply {
	private int rpl_id; //Pk
	private int mem_id; //댓글 입력한 회원의 PK
	private int rpl_num; //댓글 정렬을 위한 필드
	private String rpl_reply; //답변 댓글 여부
	private int rpl_html; //댓글 내용의 HTML 타입
	private String rpl_content; //댓글 내용
	private Date rpl_datetime; //댓글 입력 일시
	private Date rpl_updated_datetime; //댓글 최근 수정 일시
	private String rpl_ip; //댓글 등록한 IP
	private int rpl_like; //추천 회수
	private int rpl_del; //댓글 삭제 여부
}
