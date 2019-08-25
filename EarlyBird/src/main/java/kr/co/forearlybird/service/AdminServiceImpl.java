package kr.co.forearlybird.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import kr.co.forearlybird.dao.PostDAO;
import kr.co.forearlybird.domain.A_postListDTO;
import kr.co.forearlybird.domain.Board;
import kr.co.forearlybird.domain.Category;
import kr.co.forearlybird.domain.LargeCategory;
import kr.co.forearlybird.domain.Post;						  

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
	@Autowired
	PostDAO postDAO;

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

			map.put("large_id", list.get(i).getLarge_id());
			map.put("large_name", large_name);
			map.put("id", category_id);
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
		int category_id = 0;
		int category_maxid = categoryDAO.getLastNumberOfCategoryUnderLargeCategory(large_id) + 1;

		if (category_maxid < 101) { // large_id 아래 카테고리가 없다면
			category_id = 100 * large_id + category_maxid;
		} else {
			category_id = category_maxid;
		}
		// DB에 입력
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

			int newPostNum = postDAO.getNewPostNumUnderBoard((int) boards.get(i).getBrd_id());
			int allPostNum = postDAO.getAllPostNumUnderBoard((int) boards.get(i).getBrd_id());
			map.put("large_name", large_name);
			map.put("category_name", category_name);
			map.put("brd_readauthname", brd_readauthname);
			map.put("brd_writeauthname", brd_writeauthname);
			map.put("brd_exposure", boards.get(i).getBrd_exposure());
			map.put("brd_exposurename", brd_exposurename);
			map.put("brd_newPostNum", newPostNum);
			map.put("brd_allPostNum", allPostNum);			 
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

	@Override
	public int leaveBoard(int brd_id) {
		logger.info("leaveBoard");
		return boardDAO.leaveBoard(brd_id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int changeBoardVisibility(int brd_id, int brd_exposure) {
		logger.info("changeBoardVisibility");
		Map map = new HashMap();
		map.put("brd_id", brd_id);
		map.put("brd_exposure", brd_exposure);
		return boardDAO.changeBoardVisibility(map);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List searchPostToBoard(Map map) throws Exception {
		logger.info("searchPostToBoard");
		List<Post> result = getSearchPostListByParameters(map);
		List<A_postListDTO> end = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) {
			int brd_id = (int) result.get(i).getBrd_id(); // brd_id 추출
			Map temp = boardDAO.getLargeAndCategoryid(brd_id); // large_id, category_id 추출
			String large_name = largecategoryDAO.getLargeName((int) temp.get("large_id"));
			String category_name = categoryDAO.getCategoryName((int) temp.get("category_id"));
			A_postListDTO dto = castVOtoDTO(result.get(i), large_name, category_name);
			end.add(dto);
		}
		Collections.sort(end);
		return end;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<A_postListDTO> ListPostToBoard(Map map) throws ParseException {
		logger.info("ListPostToBoard");
		List<Post> list = postDAO.getPostList(map);
		List<A_postListDTO> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int brd_id = (int) list.get(i).getBrd_id(); // brd_id 추출
			Map tmp = boardDAO.getLargeAndCategoryid(brd_id); // large_id, category_id 추출
			String large_name = largecategoryDAO.getLargeName((int) tmp.get("large_id"));
			String category_name = categoryDAO.getCategoryName((int) tmp.get("category_id"));
			A_postListDTO dto = castVOtoDTO(list.get(i), large_name, category_name);
			result.add(dto);
		}
		System.out.println("list : " + list.toString());
		return result;
	}

	private A_postListDTO castVOtoDTO(Post post, String large_name, String category_name) throws ParseException {
		A_postListDTO result = new A_postListDTO();
		result.setBrd_id(post.getBrd_id());
		result.setCategory_name(large_name);
		result.setLarge_name(category_name);
		result.setMem_userid(post.getMem_userid());
		result.setPost_content(post.getPost_content());
		result.setPost_simpletime(simpledate(post.getPost_datetime()));
		result.setPost_datetime(post.getPost_datetime());
		result.setPost_del(post.getPost_del());
		result.setPost_hit(post.getPost_hit());
		result.setPost_id(post.getPost_id());
		result.setPost_like(post.getPost_like());
		result.setPost_notice(post.getPost_notice());
		result.setPost_title(post.getPost_title());
		result.setMem_nickname(memberDAO.getMemberNickName(post.getMem_userid()));
		return result;
	}

	private String DateToString(Date from) throws ParseException {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String to = transFormat.format(from);
		return to;
	}

	private LocalDate simpledate(Date date) throws ParseException {
		String dateString = DateToString(date);
		LocalDate local = LocalDate.parse(dateString);
		return local;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Post> getSearchPostListByParameters(Map map) throws Exception {
		logger.info("getSearchPostListByParameters");
		List<Post> result = new ArrayList<>();
		Map tmp = new HashMap();
		tmp.put("dateFrom", map.get("dateFrom"));
		tmp.put("dateTo", map.get("dateTo"));

		// notice 처리
		if ((int) map.get("post_notice") != -1) {
			tmp.put("post_notice", map.get("post_notice"));
		}

		// del 처리
		if ((int) map.get("post_del") != -1) {
			tmp.put("post_del", map.get("post_del"));
		}

		// brd_id 유무에 따라 분기 처리 -> brd_id or brd_idList
		if ((int) map.get("brd_id") == 0) {
			tmp.put("brd_idList", boardDAO.getBrd_idList(map));
		} else {
			tmp.put("brd_id", (int) map.get("brd_id"));
		}

		// 키워드 타입이 mem_username일 경우 mem_useridList로 변환
		if (map.get("keywordType").toString().equals("mem_nickname")) {
			tmp.put("keywordType", "mem_userid");
			tmp.put("keywords", memberDAO.getMemberListLikesThisName(map.get("keyword").toString()));
		} else {
			tmp.put("keywordType", (String) map.get("keywordType"));
			tmp.put("keyword", (String) map.get("keyword"));
		}

		if (tmp.containsKey("brd_id")) {// brd_id인 경우
			if (tmp.containsKey("brd_id") && !tmp.containsKey("keyword")) {// 키워드가 mem_username인 경우
				List keywords = (List) tmp.get("keywords");
				for (int i = 0; i < keywords.size(); i++) {
					tmp.remove("keyword");
					tmp.put("keyword", keywords.get(i));
					result.addAll(postDAO.getPostList2(tmp));
				}
			}
			result.addAll(postDAO.getPostList2(tmp));
		} else { // brd_idList인 경우
			List list = (List) tmp.get("brd_idList");
			for (int i = 0; i < list.size(); i++) {
				tmp.remove("brd_id");
				tmp.put("brd_id", (int) list.get(i));
				if (tmp.containsKey("brd_id") && !tmp.containsKey("keyword")) {// 키워드가 mem_username인 경우
					List keywords = (List) tmp.get("keywords");
					for (int j = 0; j < keywords.size(); j++) {
						tmp.remove("keyword");
						tmp.put("keyword", keywords.get(j));
						result.addAll(postDAO.getPostList2(tmp));
					}
				}
				result.addAll(postDAO.getPostList2(tmp));
			}
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void deletePostToBoard(Map map) {
		logger.info("DeletePostToBoard");
		List<String> checklist = (List<String>) map.get("checklist");
		for (String str : checklist) {
			postDAO.P_delete(Integer.parseInt(str));
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void reViewPostToBoard(Map map) {
		logger.info("reViewPostToBoard");
		List<String> checklist = (List<String>) map.get("checklist");
		for (String str : checklist) {
			postDAO.P_reView(Integer.parseInt(str));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void updatePostToBoard(Map map) {
		logger.info("updatePostToBoard");
		List<String> checklist = (List<String>) map.get("checklist");
		List<String> NoticeOrNot = (List<String>) map.get("NoticeOrNot");
		for (int i = 0; i < checklist.size(); i++) {
			postDAO.changeParamNotice(Integer.parseInt(checklist.get(i)),Integer.parseInt(NoticeOrNot.get(i)));
		}
	}
}
