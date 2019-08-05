package kr.co.forearlybird.dao;

import java.util.List;

import kr.co.forearlybird.domain.LargeCategory;

public interface LargeCategoryDAO {

	List<LargeCategory> getLargeCategoryList();

	String getLargeName(int large_id);

	int getMaxNumLargeId();

	int makeLargeCategory(LargeCategory largeCategory);

	int leaveLargeCategory(int large_id);

}
