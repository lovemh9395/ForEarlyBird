package kr.co.forearlybird.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DramaCrawilingKim {
	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	static String mainURL = "https://search.daum.net/search";
	static String siteURL = "https://search.daum.net/search?w=tot&q=%EB%8F%85%EC%A0%90%EC%83%81%EC%98%81%EA%B4%80&DA=TVS&rtmaxcoll=TVS";

	public static void main(String[] args) {
		int index = 1;
		int brd_id = index + 300;
		try {
			connOpen();
			Elements title = getTitle();
			for (Element e : title) {
				int cnt = 0;
				String cnt_title = e.text();
				String cnt_link = e.getElementsByAttribute("href").attr("href");
				String cnt_thumbnail = e.getElementsByAttribute("src").attr("src");
				String subject = e.select(".subject").select("a").text();
				
				rs = getCntNumFromDB(brd_id, cnt_title, cnt_thumbnail, cnt_link);
				while (rs.next()) {
					cnt = rs.getInt(1);
				}
				if (cnt == 0) { // DB가 아예 없을 때
					putCrawlingData(brd_id, cnt_title, cnt_thumbnail, cnt_link);
				} else { //만일 DB에 데이터가 있다면
					String sql = "select cnt_title from content where brd_id=" + brd_id;
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						if (rs.getString(1).contains(subject)) {
							break;
						} else {
							putCrawlingData(brd_id, cnt_title, cnt_thumbnail, cnt_link);
						}
						break;
					}
				}
			}
			connClose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void connOpen() {
		String url = "jdbc:mysql://localhost/earlybird?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
		String id = "root";
		String pass = "12345";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void connClose() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException sqle) {
			System.out.println("DB 통신 실패(connclose)");
		}
	}

	private static Elements getTitle() throws Exception {
		System.out.println("=====================================================================");
		System.out.println("URL :" + siteURL);
		// URL에 접속해 Document를 얻어내기
		// 간략화 된 GET,POST
		Document doc1 = Jsoup.connect(siteURL).get();
		Elements title = doc1.select(".coll_cont");
		Elements title2 = title.select(".mg_cont").select(".cont_program");
		Elements title3 = title2.select("li");
		System.out.println("=====================================================================");
		return title3;
	}

	private static void putCrawlingData(int brd_id, String cnt_title, String cnt_thumbnail, String cnt_link) throws Exception {
		String sql = "insert into content(brd_id,cnt_title,cnt_thumbnail,cnt_connectlink,cnt_datetime,cnt_updated_datetime,cnt_comment_updated_datetime)"
				+ " values('" + brd_id + "',?,?,?,now(),now(),now())";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cnt_title);
		pstmt.setString(2, cnt_thumbnail);
		pstmt.setString(3, mainURL + cnt_link);
		pstmt.executeUpdate();
	}
	private static ResultSet getCntNumFromDB(int brd_id, String cnt_title, String cnt_thumbnail, String cnt_link) throws Exception {
		String sql = "select count(cnt_title) from content where brd_id = " + brd_id;
		pstmt = conn.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
}
