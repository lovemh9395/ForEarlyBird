package kr.co.forearlybird.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.forearlybird.dao.BoardAdminDAO;
import kr.co.forearlybird.dao.BoardDAO;
import kr.co.forearlybird.dao.CategoryDAO;
import kr.co.forearlybird.dao.LargeCategoryDAO;
import kr.co.forearlybird.dao.MemberDAO;
import kr.co.forearlybird.domain.Board;
import kr.co.forearlybird.domain.Category;
import kr.co.forearlybird.domain.LargeCategory;

@Service
public class AdminServiceImpl implements AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	LargeCategoryDAO largecategoryDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	BoardDAO boardDAO;
	@Autowired
	BoardAdminDAO boardAdminDAO;
	@Autowired
	MemberDAO memberDAO;

	// ------------------------------------------
	private static final String[] levelname = { "차단회원", "비회원", "미인증 회원", "인증 회원", "탈퇴 회원", "장기 미접속 회원", "기간 차단 회원",
			"(공석)", "스태프", "관리자" };
	private static final String[] keyword = { "회원", "스태프", "관리자" };
	private static final int[] keywordToLevel = { 3, 8, 9 }; // 3 8 9
	private static final String[] Authname = { "비회원", "회원", "관리자" };
	private static final int[] Authlvl = { 1, 2, 8 };

	private String changeLevelToName(int mem_level) {
		String result = null;
		if (0 <= mem_level && mem_level <= 9) {
			result = levelname[mem_level];
		} else {
			result = "판별 불가";
		}
		return result;
	}

	private int changeKeywordToLevel(String mem_levelname) {
		for (int i = 0; i < keyword.length; i++) {
			if (mem_levelname.contains(keyword[i])) {
				return keywordToLevel[i];
			}
		}
		return -1;
	}

	@SuppressWarnings("unused")
	private int changeAuthlvlTOAuthName(String authname) {
		for (int i = 0; i < Authname.length; i++) {
			if (authname.equals(Authname[i])) {
				return Authlvl[i];
			}
		}
		return -1;
	}
	// ------------------------------------------

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> largeCategoryList() throws Exception {
		logger.info("largeCategoryList");
		List<Map> result = new ArrayList<>();
		List<LargeCategory> list = largecategoryDAO.getLargeCategoryList();
		logger.info(list.toString());
		// DTO->Map 변환로직
		for (int i = 0; i < list.size(); i++) {
			// (ex)large_id = 1 -> id = "01"
			Integer childNum = 0;
			String id = "";
			Integer large_id = list.get(i).getLarge_id();
			if (large_id < 10) {
				id += "0";
			}
			id += large_id;

			// 하위 카테고리 갯수
			childNum += categoryDAO.getNumberOfChildCategory(large_id);
			logger.info("1");
			// DTO->Map 변환용 tmp var
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("name", list.get(i).getLarge_name());
			map.put("childNum", childNum);
			result.add(map);
		}

		logger.info("largeCategoryList : " + result.toString());
		return result;
	}

	@Override
	public int makeLargeCategory(String large_name) {
		logger.info("makeLargeCategory");
		LargeCategory largeCategory = new LargeCategory();

		int large_id = largecategoryDAO.getMaxNumLargeId();
		largeCategory.setLarge_id(large_id + 1);
		largeCategory.setLarge_name(large_name);

		int result = largecategoryDAO.makeLargeCategory(largeCategory);
		logger.info("makeLargeCategory result : " + result);
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> CategoryList() {
		logger.info("CategoryList");
		List<Map> result = new ArrayList<>();
		List<Category> list = categoryDAO.getCategoryList();
		// DTO->Map 변환로직
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<>();

			// (ex)getLarge_id()=1 -> large_id = 01
			String category_id = "";
			String large_name = "";
			String tmp = "" + list.get(i).getLarge_id();
			if (tmp.length() == 1) {
				category_id += "0";
			}
			category_id += list.get(i).getCategory_id() + "";
			large_name = largecategoryDAO.getLargeName(list.get(i).getLarge_id());

			int boardNum = boardDAO.getNumberOfBoardUnderCategory(Integer.parseInt(category_id));

			map.put("id", category_id);
			map.put("large_name", large_name);
			map.put("boardNum", boardNum);
			map.put("index", i + 1);
			map.put("name", list.get(i).getCategory_name());
			result.add(map);
		}
		logger.info("CategoryList : " + result.toString());
		return result;
	}

	@Override
	public int makeCategory(int large_id, String category_name) {
		logger.info("makeCategory");
		// category_id 만들기
		int category_id = categoryDAO.getLastNumberOfCategoryUnderLargeCategory(large_id) + 1;
		Map<String, Object> map = new HashMap<>();
		map.put("category_id", category_id);
		map.put("large_id", large_id);
		map.put("category_name", category_name);
		return categoryDAO.makeCategory(map);
	}

	@Override
	public int leaveLargeCategory(int large_id) {
		logger.info("leaveLargeCategory");
		return largecategoryDAO.leaveLargeCategory(large_id);
	}

	@Override
	public int leaveCategory(int category_id) {
		logger.info("leaveCategory");
		return categoryDAO.leaveCategory(category_id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getBoardList() {
		logger.info("getBoardList");
		List<Board> boards = boardDAO.getBoardList();
		List<Map> result = new ArrayList<>();

		for (int i = 0; i < boards.size(); i++) {
			Map<String, Object> map = new HashMap<>();

			map.put("brd_id", boards.get(i).getBrd_id());
			map.put("brd_name", boards.get(i).getBrd_name());
			map.put("large_id", boards.get(i).getLarge_id());
			map.put("category_id", boards.get(i).getCategory_id());
			map.put("brd_readauth", boards.get(i).getBrd_readauth());
			map.put("brd_writeauth", boards.get(i).getBrd_writeauth());
			map.put("brd_exposure", boards.get(i).getBrd_exposure());

			String large_name = largecategoryDAO.getLargeName((Integer) map.get("large_id"));
			String category_name = categoryDAO.getCategoryName((Integer) map.get("category_id"));
			String brd_readauthname = changeLevelToName((Integer) map.get("brd_readauth"));
			String brd_writeauthname = changeLevelToName((Integer) map.get("brd_writeauth"));
			String brd_exposurename;
			if ((Integer) map.get("brd_exposure") == 1) {
				brd_exposurename = "표시";
			} else {
				brd_exposurename = "미 표시";
			}

			map.put("large_name", large_name);
			map.put("category_name", category_name);
			map.put("brd_readauthname", brd_readauthname);
			map.put("brd_writeauthname", brd_writeauthname);
			map.put("brd_exposurename", brd_exposurename);

			result.add(map);
		}
		return result;
	}

	@Override
	public List<String> getAdminNickname(int brd_id) {
		logger.info("getAdminNickname");
		List<String> result = new ArrayList<>();
		List<String> adminlist = boardAdminDAO.getAdminID(brd_id);
		for (int i = 0; i < adminlist.size(); i++) {
			result.add(memberDAO.getMemberNickName(adminlist.get(i)));
		}
		return result;
	}

	// 검색없이 기본
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getMemberListForBoardAdmin() {
		logger.info("getMemberListForBoardAdmin");
		int minlevel = 8;
		return convMemberLeveltoMemberLevelName(memberDAO.getMemberListByMinLevel(minlevel));

	}

	@SuppressWarnings("rawtypes")
	@Override
	public int updateAdmin(Map map) {
		logger.info("updateAdmin");
		return boardAdminDAO.makeAdmin(map);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int checkAdminId(int brd_id, String mem_userid) {
		logger.info("checkAdminId");
		Map map = new HashMap();
		map.put("brd_id", brd_id);
		map.put("mem_userid", mem_userid);
		return boardAdminDAO.checkAdminId(map);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> searchMembersForBoardAdmin(Map map) {
		logger.info("searchMembersForBoardAdmin");
		String searchkeyword = map.get("searchkeyword").toString();
		Map toDAO = new HashMap();
		switch (map.get("searchFilter").toString()) {
		case "mem_userid":
			toDAO.put("index", "mem_userid");
			toDAO.put("mem_userid", searchkeyword);
			break;
		case "mem_nickname":
			toDAO.put("index", "mem_nickname");
			toDAO.put("mem_nickname", searchkeyword);
			break;
		case "mem_levelname":
			int tmp = changeKeywordToLevel(searchkeyword);
			toDAO.put("index", "mem_level");
			if (tmp > -1) {
				toDAO.put("mem_level", tmp);
			} else {
				toDAO.put("mem_level", 99);
			}
			break;
		}
		List<Map> result = convMemberLeveltoMemberLevelName(memberDAO.getMemberList(toDAO));

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> convMemberLeveltoMemberLevelName(List<Map> list) {
		logger.info("convMemberLeveltoMemberLevelName");
		List<Map> result = new ArrayList<Map>();
		for (int i = 0; i < list.size(); i++) {
			Map tempMap = list.get(i);
			tempMap.put("mem_levelname", changeLevelToName((int) tempMap.get("mem_level")));
			tempMap.remove("mem_level");
			result.add(i, tempMap);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void makeBoard(Map map) {
		logger.info("makeBoard");
		Map newMap = new HashMap();
		Map tmp = new HashMap();

		int large_id = (int) map.get("large_id");
		int category_id = (int) map.get("category_id");
		String brd_name = (String) map.get("brd_name");
		int brd_readauth = (int) map.get("brd_readauth");
		int brd_writeauth = (int) map.get("brd_writeauth");

		tmp.put("brd_name", brd_name);
		tmp.put("category_id", category_id);
		int checkbrd_id = boardDAO.getBoardIdNumToName(tmp);

		if (checkbrd_id < 1) {
			newMap.put("brd_id", ((int) boardDAO.getBoardMAXID()) + 1);
			newMap.put("brd_name", brd_name);
			newMap.put("large_id", large_id);
			newMap.put("category_id", category_id);
			newMap.put("brd_readauth", brd_readauth);
			newMap.put("brd_writeauth", brd_writeauth);
			boardDAO.makeBoard(newMap);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> CategoryList(int large_id) {
		logger.info("CategoryList");
		return categoryDAO.getCategoryList(large_id);
	}
}
