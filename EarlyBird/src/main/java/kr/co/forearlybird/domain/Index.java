package kr.co.forearlybird.domain;

//Lombok을 이용함(getter,setter 등등 한번에 처리 가능)

public class Index {
	private int index_id; //PK
	private int index_separate; //Content = 0, Post = 1
	private int index_key; //컨텐츠, 게시글의 PK 저장공간
	private int index_filenum; //첨부된 파일 개수
	private int rpl_id; //댓글 PK
}
