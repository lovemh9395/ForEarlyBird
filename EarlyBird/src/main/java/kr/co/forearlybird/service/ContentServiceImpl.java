package kr.co.forearlybird.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
	
	private static final String SAVE_PATH = "D:/Project/ForEarlyBird/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/EarlyBird/resources/shareimage/";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map C_share_make(Map postmap) {
		logger.info("컨텐츠 글쓰기 service");
		String url = null;
		try {
			MultipartFile multipartFile = (MultipartFile) postmap.get("file");
			// 파일 정보
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			Long size = multipartFile.getSize();
			System.out.println("1");
			// 서버에서 저장 할 파일 이름
			String saveFileName = genSaveFileName(extName);
			System.out.println("2");
			System.out.println("originFilename : " + originFilename);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("saveFileName : " + saveFileName);

			logger.info(saveFileName);

			writeFile(multipartFile, saveFileName);
			url = SAVE_PATH + saveFileName;
			Map middlemap = new HashMap<>();
			middlemap.put("share_mem_userid", postmap.get("share_mem_userid"));
			middlemap.put("share_image", saveFileName);
			middlemap.put("share_title", postmap.get("share_title"));
			middlemap.put("share_link", postmap.get("share_link"));
			middlemap.put("share_brd_id", postmap.get("share_brd_id"));
			Integer result = contentDAO.C_share_make(middlemap);

			Map<String, String> map = new HashMap<>();
			map.put("result", result.toString());
			map.put("url", url);
			map.put("ProfileName", saveFileName);
			logger.info("???????????????????????????" + map.get("url"));
			return map;
		} catch (IOException e) {
			// 원래라면 RuntimeException 을 상속받은 예외가 처리되어야 하지만
			// 편의상 RuntimeException을 던진다.
			// throw new FileUploadException();
			throw new RuntimeException(e);
		}
	}

	// 현재 시간을 기준으로 파일 이름 생성
	private String genSaveFileName(String extName) {
		String fileName = "";

		Calendar calendar = Calendar.getInstance();
		fileName += "share";
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}

	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		boolean result = false;

		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + saveFileName);
		fos.write(data);
		fos.close();

		return result;
	}

	@Override
	public List<Content> C_share_list(int brd_id) {
		logger.info("컨텐츠 리스트 가져오기 Service");
		return contentDAO.C_share_list(brd_id);
	}
}
