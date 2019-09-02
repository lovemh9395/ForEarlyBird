package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("rawtypes")
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

	@Override
	public int checkAdminId(Map map) {
		logger.info("checkAdminIdDAO");
		int result = sqlsession.selectOne("boardAdmin.checkAdminId", map);
		logger.info("checkadminIdDAO result : "+result);
		return result;
	}

	@Override
	public int makeAdmin(Map map) {
		logger.info("makeAdminDAO");
		return sqlsession.insert("boardAdmin.makeAdmin", map);
	}

	@Override
	public void deleteAdmin(Map map) {
		logger.info("deleteAdminDAO");
		sqlsession.delete("boardAdmin.deleteAdmin", map);
	}
}
