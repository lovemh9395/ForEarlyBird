package kr.co.forearlybird.dao;

import java.util.List;

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
	public int makeCategory(String categoryName) {
		logger.info("setCategoryDAO");
		return sqlsession.insert("category.makeCategory", categoryName);
	}

	@Override
	public int getNumberOfChildCategory(int large_id) {
		return sqlsession.selectOne("category.getNumberOfChildCategory",large_id);
	}

}
