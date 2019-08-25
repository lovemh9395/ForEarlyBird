package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.MainController;
import kr.co.forearlybird.dao.ContentDAO;
import kr.co.forearlybird.domain.Content;

@Repository
public class ContentServiceImpl implements ContentService {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private ContentDAO contentDAO;
	
	@Override
	public List<Content> Main_C_list() {
		logger.info("Main Content List Service");
		logger.info("혹시 여기는 들어오니?");
		return contentDAO.Main_C_list();
	}

	@Override
	public List<Content> C_list_M(int idx) {
		logger.info("C_list_M Service");
		return contentDAO.C_list_M(idx);
	}
	
	@Override
	public int C_recommand(int cnt_id) {
		logger.info("추천하기 service");
		return contentDAO.C_recommand(cnt_id);
	}
	
	@Override
	public int C_view(int cnt_id) {
		logger.info("컨텐츠 조회하기~ service");
		return contentDAO.C_view(cnt_id);
	}

	@Override
	public String C_connectlink(int connectlink) {
		return contentDAO.C_connectlink(connectlink);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Content> menu_btn(Map map) {
		return contentDAO.menu_btn(map);
	}

}
