package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.domain.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

	@Autowired
	SqlSession sqlsession;

	@Override
	public List<Category> getCategoryList() {
		logger.info("getCategoryListDAO");
		return sqlsession.selectList("category.getCategoryList");
	}

	@Override
	public int makeCategory(@SuppressWarnings("rawtypes") Map map) {
		logger.info("makeCategoryDAO");
		return sqlsession.insert("category.makeCategory", map);
	}

	@Override
	public int getNumberOfChildCategory(int large_id) {
		logger.info("getNumberOfChildCategoryDAO");
		Integer result = sqlsession.selectOne("category.getNumberOfChildCategory",large_id);
		logger.info(result+"");
		if (result!=null) {
			return result;
		}
		else {
			return 0;
		}
	}

	@Override
	public int leaveCategory(int category_id) {
		logger.info("leaveCategoryDAO");
		return sqlsession.delete("category.leaveCategory",category_id);
	}

	@Override
	public int getLastNumberOfCategoryUnderLargeCategory(int large_id) {
		logger.info("getLastNumberOfCategoryUnderLargeCategoryDAO");
		return sqlsession.selectOne("category.getLastNumberOfCategoryUnderLargeCategory", large_id);
	}

	@Override
	public String getCategoryName(Integer category_id) {
		logger.info("getCategoryNameDAO");
		return sqlsession.selectOne("category.getCategoryName", category_id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int getCategoryID(Map map) {
		logger.info("getCategoryIDDAO");
		return sqlsession.selectOne("category.getCategoryID",map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getCategoryList(int large_id) {
		logger.info("getCategoryListDAO");
		return sqlsession.selectList("category.getCategoryListUnderLargeID", large_id);
	}
}
