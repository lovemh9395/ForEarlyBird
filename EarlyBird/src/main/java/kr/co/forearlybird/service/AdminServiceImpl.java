package kr.co.forearlybird.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.forearlybird.dao.CategoryDAO;
import kr.co.forearlybird.dao.LargeCategoryDAO;
import kr.co.forearlybird.domain.Category;
import kr.co.forearlybird.domain.LargeCategory;

@Service
public class AdminServiceImpl implements AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	LargeCategoryDAO largecategoryDAO;
	
	@Autowired
	CategoryDAO categoryDAO;

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
			String tmp = list.get(i).getLarge_id()+"";
			if (tmp.length()==1) {
				large_id += "0";
			}
			large_id += list.get(i).getLarge_id();
			
			//하위 카테고리 갯수
			int childNum = categoryDAO.getNumberOfChildCategory(list.get(i).getLarge_id());

			map.put("id", large_id);
			map.put("name", list.get(i).getLarge_name());
			map.put("childNum",childNum);
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
		largeCategory.setLarge_id(large_id+1);
		largeCategory.setLarge_name(large_name);
		
		int result = largecategoryDAO.makeLargeCategory(largeCategory);
		logger.info("makeLargeCategory result : "+result);
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
			String Category_id = "";
			String large_name = "";
			String tmp = ""+list.get(i).getLarge_id();
			if (tmp.length() == 1) {
				Category_id += "0";
			}
			Category_id += list.get(i).getCategory_id()+"";
			large_name = largecategoryDAO.getLargeName(list.get(i).getLarge_id());

			map.put("id", Category_id);
			map.put("large_name",large_name);
			map.put("name", list.get(i).getCategory_name());
			result.add(map);
		}
		logger.info("CategoryList : " + result.toString());
		return result;
	}

	@Override
	public int makeCategory(int large_id, String category_name) {
		logger.info("makeCategory");
		return categoryDAO.makeCategory(category_name);
	}

	@Override
	public int leaveLargeCategory(int large_id) {
		logger.info("leaveLargeCategory");
		return largecategoryDAO.leaveLargeCategory(large_id);
	}

}
