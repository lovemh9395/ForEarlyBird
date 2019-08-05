package kr.co.forearlybird.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.domain.LargeCategory;

@Repository
public class LargeCategoryDAOImpl implements LargeCategoryDAO {
	private static final Logger logger = LoggerFactory.getLogger(LargeCategoryDAOImpl.class);
	
	@Autowired
	SqlSession sqlsession;

	@Override
	public List<LargeCategory> getLargeCategoryList() {
		logger.info("getLargeCategoryListDAO");
		return sqlsession.selectList("largecategory.getLargeCategoryList");
	}

	@Override
	public int makeLargeCategory(LargeCategory largeCategory) {
		logger.info("setLargeCategoryDAO");
		int result = sqlsession.insert("largecategory.makeLargeCategory", largeCategory);
		logger.info("setLargeCategoryDAO result :"+result);
		return result;
	}

	@Override
	public String getLargeName(int large_id) {
		logger.info("getLargeNameDAO");
		return sqlsession.selectOne("largecategory.getLargeName",large_id);
	}

	@Override
	public int getMaxNumLargeId() {
		logger.info("getMaxNumLargeId");
		return sqlsession.selectOne("largecategory.getMaxNumLargeId");
	}

	@Override
	public int leaveLargeCategory(int large_id) {
		logger.info("leaveLargeCategory");
		return sqlsession.delete("largecategory.leaveLargeCategory", large_id);
	}

}
