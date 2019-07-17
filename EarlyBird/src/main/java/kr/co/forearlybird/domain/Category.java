package kr.co.forearlybird.domain;

import lombok.Data;

//Lombok을 이용함(getter,setter 등등 한번에 처리 가능)
@Data
public class Category {
	private String category_id;   //분류코드( Large_ID+2자리 숫자)
	private String category_name; //분류명
}
