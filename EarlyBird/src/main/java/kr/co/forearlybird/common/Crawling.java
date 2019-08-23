package kr.co.forearlybird.common;

//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;

public class Crawling {
//	public static void main(String[] args) {
//		Connection conn = null;
//		Statement stmt = null; // 질의문
//		ResultSet rs = null;
//		PreparedStatement pstmt = null;
//		boolean done = false;
//		int index = 1;
//		int brd_id = index + 100;
//		try {
//			String mainURL = "http://www.inven.co.kr";
//			String siteURL = "http://www.inven.co.kr/webzine/news/?hotnews=1";
//			System.out.println("=====================================================================");
//			System.out.println("URL :" + siteURL);
//			// URL에 접속해 Document를 얻어내기
//			// 간략화 된 GET,POST
//			Document doc1 = Jsoup.connect(siteURL).get();
//
//			Elements title = doc1.select(".webzineNewsList");
//			Elements title2 = title.select("tbody");
//			Elements title3 = title2.select(".left");
//
//			System.out.println("=====================================================================");
//			for (Element e : title3) {
//				String cnt_title = e.select(".title").text();
//				String cnt_thumbnail = e.getElementsByAttribute("src").attr("src");
//				String cnt_link = e.getElementsByAttribute("href").attr("href");
//				System.out.println("titleeeeeeeeeeeeeeee = "+cnt_title);
//				System.out.println("imgggggggggggggggggg = "+cnt_thumbnail);
//				System.out.println("linkkkkkkkkkkkkkkkkk = "+cnt_link);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}