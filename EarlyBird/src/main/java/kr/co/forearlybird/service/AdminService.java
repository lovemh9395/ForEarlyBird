package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	// 대분류 카테고리 리스트 불러오기
	@SuppressWarnings("rawtypes")
	List<Map> largeCategoryList();

	// 소분류 카테고리 리스트 불러오기
	@SuppressWarnings("rawtypes")
	List<Map> CategoryList();

	// 대분류 카테고리 만들기
	int makeLargeCategory(String large_name);

	// 소분류 카테고리 만들기
	int makeCategory(int large_id, String category_name);

	int leaveLargeCategory(int large_id);

	int leaveCategory(int category_id);

	@SuppressWarnings("rawtypes")
	List<Map> getBoardList();

}
