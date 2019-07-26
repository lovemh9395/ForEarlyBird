package kr.co.forearlybird.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.forearlybird.dao.CategoryDAO;
import kr.co.forearlybird.dao.LargeCategoryDAO;
import kr.co.forearlybird.domain.LargeCategory;

public class AdminServiceImpl implements AdminService{
	
	@Autowired
	LargeCategoryDAO largecategoryDAO;
	CategoryDAO categoryDAO;
	

	@Override
	public List<LargeCategory> largeCategoryList() {
		return largecategoryDAO.getLargeCategoryList();
	}


	@Override
	public int makeCategoryList(String largeCategoryName) {
		return largecategoryDAO.setLargeCategory(largeCategoryName);
	}

}
