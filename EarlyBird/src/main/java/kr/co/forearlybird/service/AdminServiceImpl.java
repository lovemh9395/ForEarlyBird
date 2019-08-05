package kr.co.forearlybird.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.forearlybird.dao.BoardDAO;
import kr.co.forearlybird.dao.CategoryDAO;
import kr.co.forearlybird.dao.LargeCategoryDAO;
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

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> largeCategoryList() {
		List<Map> result = new ArrayList<>();
		List<LargeCategory> list = largecategoryDAO.getLargeCategoryList();

		// DTO->Map 변환로직
		for (int i = 0; i < list.size(); i++) {
			// DTO->Map 변환용 tmp var
			Map<String, Object> map = new HashMap<>();

			// (ex)getLarge_id()=1 -> large_id = 01
			String large_id = "";
			String tmp = list.get(i).getLarge_id() + "";
			if (tmp.length() == 1) {
				large_id += "0";
			}
			large_id += list.get(i).getLarge_id();

			// 하위 카테고리 갯수
			int childNum = categoryDAO.getNumberOfChildCategory(list.get(i).getLarge_id());

			map.put("id", large_id);
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
			String brd_readauthname = changeIdToName((Integer) map.get("brd_readauth"));
			String brd_writeauthname = changeIdToName((Integer) map.get("brd_writeauth"));
			String brd_exposurename;
			if ((Integer)map.get("brd_exposure")==1) {
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

	public String changeIdToName(int id) {
		String result = null;
		switch (id) {
		case 0:
			result = "차단회원";
			break;
		case 1:
			result = "비회원";
			break;
		case 2:
			result = "미인증 회원";
			break;
		case 3:
			result = "인증 회원";
			break;
		case 4:
			result = "탈퇴 회원";
			break;
		case 5:
			result = "장기 미접속자";
			break;
		case 6:
			result = "기간 차단 이용자";
			break;
		case 7:
			result = "(공석)";
			break;
		case 8:
			result = "스태프";
			break;
		case 9:
			result = "관리자";
			break;
		default:
			result = "판별불가";
			break;
		}
		return result;
	}
}
