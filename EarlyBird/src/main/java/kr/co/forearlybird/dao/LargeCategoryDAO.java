package kr.co.forearlybird.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.forearlybird.domain.LargeCategory;

@Repository
public interface LargeCategoryDAO {

	List<LargeCategory> getLargeCategoryList();

	int setLargeCategory(String largeCategoryName);

}
