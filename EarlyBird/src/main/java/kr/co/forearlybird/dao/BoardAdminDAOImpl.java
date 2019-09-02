package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardAdminDAOImpl implements BoardAdminDAO{
	private static final Logger logger = LoggerFactory.getLogger(BoardAdminDAOImpl.class);

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<String> getAdminID(int brd_id) {
		logger.info("getAdminIDDAO");
		return sqlsession.selectList("boardAdmin.getAdminID", brd_id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int checkAdminId(Map map) {
		logger.info("checkAdminIdDAO");
		int result = sqlsession.selectOne("boardAdmin.checkAdminId", map);
		logger.info("checkadminIdDAO result : "+result);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int makeAdmin(Map map) {
		logger.info("makeAdminDAO");
		return sqlsession.insert("boardAdmin.makeAdmin", map);
	}
}
