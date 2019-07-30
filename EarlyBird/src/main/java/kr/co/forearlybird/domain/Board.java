package kr.co.forearlybird.domain;

import lombok.Data;

//Lombok을 이용함(getter,setter 등등 한번에 처리 가능)
@Data
public class Board {
	private int brd_id;  		//PK
	private int bam_id;			//게시판 관리자 ID PK
	private String brd_name; 	//게시판 명
	private int large_id; 	//대분류코드(2자리숫자)PK
	private int category_id; //분류코드(Large_ID+2자리 숫자)PK
	private int brd_readauth; 	//읽기권한
	private int brd_writeauth;	//쓰기권한
	private int brd_exposure;	//게시판 노출 여부
}
