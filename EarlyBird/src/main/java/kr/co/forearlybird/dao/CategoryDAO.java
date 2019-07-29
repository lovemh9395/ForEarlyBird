package kr.co.forearlybird.dao;

import java.util.List;

import kr.co.forearlybird.domain.Category;

public interface CategoryDAO {

	List<Category> getCategoryList();

	int makeCategory(String categoryName);

	int getNumberOfChildCategory(int large_id);

}
