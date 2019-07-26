package kr.co.forearlybird.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.forearlybird.domain.LargeCategory;

public class LargeCategoryDAOImpl implements LargeCategoryDAO{

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<LargeCategory> getLargeCategoryList() {
		return sqlsession.selectList("largecategory.getLargeCategoryList");
	}

	@Override
	public int setLargeCategory(String largeCategoryName) {
		return sqlsession.update("largecategory.setLargeCategory", largeCategoryName);
	}

}
