package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Category;

public interface CategoryDAO {

	List<Category> getCategoryList();

	int makeCategory(Map<String, Object> map);

	int getNumberOfChildCategory(int large_id);

	int leaveCategory(int category_id);

	int getLastNumberOfCategoryUnderLargeCategory(int large_id);

	String getCategoryName(Integer category_id);

	@SuppressWarnings("rawtypes")
	int getCategoryID(Map tmp);

	@SuppressWarnings("rawtypes")
	List<Map> getCategoryList(int large_id);

}
