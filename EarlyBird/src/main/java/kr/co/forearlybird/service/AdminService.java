package kr.co.forearlybird.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.forearlybird.domain.LargeCategory;

@Service
public interface AdminService {

	//대분류 카테고리 리스트 불러오기
	List<LargeCategory> largeCategoryList();

	int makeCategoryList(String largeCategoryName);

}
