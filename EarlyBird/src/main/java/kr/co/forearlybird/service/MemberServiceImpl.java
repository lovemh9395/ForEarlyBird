package kr.co.forearlybird.service;

import java.io.FileOutputStream;
import java.io.IOException;
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
import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.domain.Reply;
import kr.co.forearlybird.email.MailHandler;
import kr.co.forearlybird.email.TempKey;

@SuppressWarnings({ "rawtypes", "unchecked" })
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
		String id = request.getParameter("login_mem_userid");
		String pw = request.getParameter("login_mem_password");

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
			result.put("id", temp.get("mem_userid"));
			result.put("pw", temp.get("mem_password"));
			result.put("name", temp.get("mem_username"));
			result.put("profilephoto", temp.get("mem_photo"));
			result.put("level", temp.get("mem_level"));

			memberDAO.mem_logintime(id);

		} else {
			logger.info("비밀번호 불일치");
			result = null;
			return result;
		}

		logger.info(result.toString());
		return result;
	}

	@Override
	public List<Member> list() {
		return null;
	}

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
		String key = new TempKey().getKey(10, false); // 인증키 생성
		memberDAO.searchPWD(vo, key); // 인증키 DB저장 비밀번호로
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[ForEarlyBird 서비스 이메일 인증]");
		sendMail.setText(
				new StringBuffer().append("<h1>메일인증</h1>").append("회원님의 임시 비밀번호는 " + key + " 입니다.").toString());
		sendMail.setFrom("lovemh9395", "ForEarlyBird");
		sendMail.setTo(vo.getMem_userid());
		sendMail.send();
	}

	@Override
	public List<Post> listCriteria(Map map) throws Exception {
		logger.info("내 글 보기 Post Service");
		return memberDAO.listCriteria(map);
	}

	@Override
	public int listCountCriteria(Map map) throws Exception {
		logger.info("내 글 보기 Count Paging Service");
		return memberDAO.countPaging(map);
	}

	@Override
	public List<Reply> myreplylistCriteria(Map map) throws Exception {
		logger.info("내 댓글 보기 Post Service");
		return memberDAO.myreplylistCriteria(map);
	}

	@Override
	public int myreplylistCountCriteria(Map map) throws Exception {
		logger.info("내 댓글 보기 Count Paging Service");
		return memberDAO.myreplycountPaging(map);
	}

	@Override
	public int CheckId(String formId) throws Exception {
		logger.info("로그인 아이디 체크 Service");
		return memberDAO.CheckId(formId);
	}

	@Override
	public int CheckPass(Map map) {
		logger.info("로그인 비밀번호 체크 Service");
		String login_pass = (String) map.get("login_pass");

		String rawPw = memberDAO.getrawPw(map);
		logger.info("암호화 비밀번호" + rawPw);
		logger.info("비밀번호" + login_pass);

		if (passwordEncoder.matches(login_pass, rawPw)) {
			logger.info("비밀번호 일치");
			int result = memberDAO.CheckLevel(map);
			if(result == 4) {
				return 4;
			} 
			return 3;
		} else {
			return 2;
		}
	}

	@Override
	public int CheckLevel(String formId) {
		return 0;
	}

	@Override
	public int searchPWDcheck(Map map) {
		return memberDAO.searchPWDcheck(map);
	}
	
}
