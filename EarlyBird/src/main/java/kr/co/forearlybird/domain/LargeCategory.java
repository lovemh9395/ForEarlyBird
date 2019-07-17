package kr.co.forearlybird.domain;

import lombok.Data;

//Lombok을 이용함(getter,setter 등등 한번에 처리 가능)
@Data
public class LargeCategory {
	private String large_id; //대분류코드 ( 2자리 숫자 )
	private String large_name; //대분류명
}
