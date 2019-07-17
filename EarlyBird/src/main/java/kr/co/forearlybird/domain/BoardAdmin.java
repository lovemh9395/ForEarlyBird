package kr.co.forearlybird.domain;

import lombok.Data;

//Lombok을 이용함(getter,setter 등등 한번에 처리 가능)
@Data
public class BoardAdmin {
	private int bam_id; //PK
	private int brd_id; //게시판 테이블( Board Table )의 PK
	private int mem_id; //회원 테이블( Member Table )의 PK
}
