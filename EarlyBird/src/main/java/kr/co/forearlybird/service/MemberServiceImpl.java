package kr.co.forearlybird.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.forearlybird.dao.MemberDAO;
import kr.co.forearlybird.domain.Member;
import kr.co.forearlybird.email.MailHandler;
import kr.co.forearlybird.email.TempKey;

@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	@Inject
	PasswordEncoder passwordEncoder;

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public Map login(HttpServletRequest request) throws Exception {
		logger.info("로그인 service");
		String id = request.getParameter("email");
		String pw = request.getParameter("password");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		String rawPw = memberDAO.getrawPw(map);
		logger.info("암호화 비밀번호" + rawPw);
		logger.info("비밀번호" + pw);
		
		
		Map temp = memberDAO.login(map);
		
		Map result = new HashMap();
		
		if (passwordEncoder.matches(pw, rawPw)) {
			logger.info("비밀번호 일치");
			result.put("pw", temp.get("mem_password"));
		} else {
			logger.info("비밀번호 불일치");
		}

		result.put("id", temp.get("mem_userid"));
//		result.put("pw", temp.get("mem_password"));
		result.put("name", temp.get("mem_username"));
		result.put("profilephoto", temp.get("mem_photo"));
		result.put("level", temp.get("mem_level"));

		return result;
	}

	@Override
	public List<Member> list() {
		return null;
	}

//	@Override
//	public int make(HttpServletRequest request) {
//		logger.info("회원가입 service");
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		String encPassword = passwordEncoder.encode(request.getParameter("makepassword"));
//		logger.info("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"+encPassword);
//		Member member = new Member();
//		member.setMem_userid(request.getParameter("makeemail"));
//		member.setMem_password(encPassword);
//		member.setMem_nickname(request.getParameter("makenickname"));
//		member.setMem_phone(request.getParameter("maketel"));
//		member.setMem_birthday(request.getParameter("makebirth"));
//		member.setMem_username(request.getParameter("makename"));
//		// 클라이언트 아이피 주소 받기
//		return memberDAO.make(member);
//	}

	@Override
	public Member detail(String id) {
		logger.info("내 정보 보기 service");

		return memberDAO.detail(id);
	}

	@Override
	public int delete(String id) {
		logger.info("회원탈퇴 service");
		return memberDAO.delete(id);
	}

	@Override
	public Map update(Map<String, Object> map) {
		logger.info("정보수정 service");
		return memberDAO.update(map);
	}

	private static final String SAVE_PATH = "D:/Project/ForEarlyBird/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/EarlyBird/resources/uploadimage/";

	@Override
	public Map restore(Map postmap, HttpSession session) {
		logger.info("프로필 업로드 service");
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

			// String useriding = postmap.get("userid").toString();

			String iddddd = (String) session.getAttribute("useridd");
			System.out.println("fdsafdsafdsafdsafdsafdsa" + iddddd);
			Map middlemap = new HashMap<>();
			middlemap.put("url", saveFileName);
			middlemap.put("user", iddddd);
			Integer result = memberDAO.profile(middlemap);

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
		fileName += "profile";
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
		FileOutputStream fos = new FileOutputStream(
				"D:/Project/ForEarlyBird/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/EarlyBird/resources/uploadimage/"
						+ saveFileName);
		fos.write(data);
		fos.close();

		return result;
	}

	@Override
	public String searchID(HttpServletRequest request) {
		logger.info("ID 찾기 service");
		Member member = new Member();
		member.setMem_username(request.getParameter("mem_username"));
		member.setMem_birthday(request.getParameter("mem_birthday"));
		member.setMem_phone(request.getParameter("mem_phone"));
		return memberDAO.searchID(member);
	}

	@Inject
	private JavaMailSender mailSender;

	@Transactional
	@Override
	public void create(Member vo) throws Exception {
		String encPassword = passwordEncoder.encode(vo.getMem_password());
		logger.info("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" + encPassword);
		vo.setMem_password(encPassword);

		memberDAO.insertUser(vo); // 회원가입 DAO

		String key = new TempKey().getKey(50, false); // 인증키 생성

		memberDAO.createAuthKey(vo.getMem_userid(), key); // 인증키 DB저장

		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[ForEarlyBird 서비스 이메일 인증]");
		sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
				.append("<a href='http://localhost:9002/member/M_newJoin?user_email=").append(vo.getMem_userid())
				.append("&key=").append(key).append("' target='_blenk'>이메일 인증 확인</a>").toString());
		sendMail.setFrom("lovemh9395", "ForEarlyBird");
		sendMail.setTo(vo.getMem_userid());
		sendMail.send();
	}

	@Override
	public void userAuth(String userEmail) throws Exception {
		memberDAO.userAuth(userEmail);
	}

	@Override
	public void serachPWD(Member vo) throws Exception {
		logger.info("비밀번호 찾기 service");
		System.out.println("+++++++++++++++++++++++++++++++++" + vo);
		String key = new TempKey().getKey(10, false); // 인증키 생성

		System.out.println("---------------------vo :" + vo + "---------key" + key);
		memberDAO.searchPWD(vo, key); // 인증키 DB저장 비밀번호로
		logger.info("2");
		MailHandler sendMail = new MailHandler(mailSender);
		logger.info("3");
		sendMail.setSubject("[ForEarlyBird 서비스 이메일 인증]");
		logger.info("4");
		sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
				.append("회원님의 임시 비밀번호는 " + vo.getMem_password() + " 입니다.")
				.append("<a href='http://localhost:9002/member/M_newJoin?user_email=").append(vo.getUser_authcode())
				.append("&key=").append(key).append("' target='_blenk'>이메일 인증 확인</a>").toString());
		logger.info("5");
		sendMail.setFrom("lovemh9395", "ForEarlyBird");
		logger.info("6");
		sendMail.setTo(vo.getMem_userid());
		logger.info("7");
		sendMail.send();
		logger.info("8");
	}
}
