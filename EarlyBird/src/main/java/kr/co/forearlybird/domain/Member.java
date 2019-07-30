package kr.co.forearlybird.domain;

import java.util.Date;

import lombok.Data;

//Lombok을 이용함(getter,setter 등등 한번에 처리 가능)
@Data
public class Member {
	private String mem_userid; //회원 아이디 PK
	private String mem_password; //회원 패스워드
	private String mem_nickname; //회원 닉네임
	private String mem_phone; //연락처
	private String mem_birthday; //생일
	private String mem_username; //회원 실명
	private int mem_level; //레벨
	//private int mem_gender; //성별
	//private String mem_zipcode; //우편번호
	//private String mem_address1; //집주소1
	//private String mem_address2; //집주소2
	//private int mem_receive_email; //이메일 수신여부
	//private int mem_open_profile; //정보 공개 여부
	private String mem_photo; //회원 이미지 경로
	private String mem_profile_content; //자기소개, 프로필 페이지에 나타남
	//private Date mem_register_datetime; //회원 등록일
	//private String mem_register_ip; //회원 등록 IP
	//private Date mem_lastlogin_datetime; //최종 로그인 시간
	//private String mem_lastlogin_ip; //최종 로그인 IP
	//private String mem_adminmemo; //관리자용 메모
}
