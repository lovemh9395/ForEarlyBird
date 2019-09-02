package kr.co.forearlybird.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DramaCrawiling {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean done = false;
		int index = 1;
		int brd_id = index + 300;
		try {
			String mainURL = "https://search.daum.net/search";
			String siteURL = "https://search.daum.net/search?w=tot&q=%EB%8F%85%EC%A0%90%EC%83%81%EC%98%81%EA%B4%80&DA=TVS&rtmaxcoll=TVS";
			System.out.println("=====================================================================");
			System.out.println("URL :" + siteURL);
			// URL에 접속해 Document를 얻어내기
			// 간략화 된 GET,POST
			Document doc1 = Jsoup.connect(siteURL).get();

			Elements title = doc1.select(".coll_cont");
			Elements title2 = title.select(".mg_cont").select(".cont_program");
			Elements title3 = title2.select("li");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/earlybird?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
			String id = "root";
			String pass = "12345";
			conn = DriverManager.getConnection(url, id, pass);

			System.out.println("=====================================================================");
			for (Element e : title3) {
				String cnt_title = e.text();
				String cnt_link = e.getElementsByAttribute("href").attr("href");
				String cnt_thumbnail = e.getElementsByAttribute("src").attr("src");
				System.out.println("titleeeeeeeeeeeeeeee = "+cnt_title);
				System.out.println("imgggggggggggggggggg = "+cnt_thumbnail);
				System.out.println("linkkkkkkkkkkkkkkkkk = "+cnt_link);

				String sql = "select count(cnt_title) from content where brd_id = "+ brd_id;
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				int cnt = 0;
				System.out.println("1");
				while (rs.next()) {
					cnt = rs.getInt(1);
				}
				if (cnt == 0) { // DB가 아예 없을 때
					System.out.println("2");
					sql = "insert into content(brd_id,cnt_title,cnt_thumbnail,cnt_connectlink,cnt_datetime,cnt_updated_datetime,cnt_comment_updated_datetime)"
							+ " values('"+brd_id+"',?,?,?,now(),now(),now())";
						System.out.println("2");	
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cnt_title);
					pstmt.setString(2, cnt_thumbnail);
					pstmt.setString(3, mainURL+cnt_link);
					pstmt.executeUpdate();
				} else {
					sql = "select cnt_title from content where brd_id="+brd_id;
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next() && !done) {
						System.out.println(rs.getString(1));
						if (rs.getString(1).contains(e.select(".subject").select("a").text())) {
							System.out.println("1");
							done=true;
							break;
						} else {
							System.out.println("혹시 안들어오니?");
							sql = "insert into content(brd_id,cnt_title,cnt_thumbnail,cnt_connectlink,cnt_datetime,cnt_updated_datetime,cnt_comment_updated_datetime)"
									+ " values('"+brd_id+"',?,?,?,now(),now(),now())";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, cnt_title);
							pstmt.setString(2, cnt_thumbnail);
							pstmt.setString(3, mainURL + cnt_link);
							pstmt.executeUpdate();
						}
						System.out.println("여기는?");
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	}

//					Document rawData = Jsoup.connect("http://www.thisisgame.com/webzine/game/nboard/225/?category=1")
//	                        .timeout(5000)
//	                        .get();
//					
//					Elements articles = rawData.select(".news-list"); // 공지사항을 제외한 tr의 a 태그들을 얻어온다.
//
//					for(Element article : articles) {
//
//					  String href = article.attr("abs:href"); // a태그 href의 절대주소를 얻어낸다.
//					  
//					  // a 태그 안에 포함된 div들
//					  Elements articleDiv = article.select("div");
//
//					  String thumbUrl = href
//					                    + articleDiv.first() // 첫 번째 div에서 썸네일 url을 얻어온다.
//					                                .attr("style")
//					                                .replace("background-image:url(", "")
//					                                .replace(")", "");
//
//					  String title = articleDiv.get(1).ownText(); // 두 번째 div에서 제목을 얻어낸다.
//
//					  String date = articleDiv.get(1).select("small").text()
//					                                  .split("\\|")[0];
//
//					  System.out.println(href); // http://ma../b/mangup/00000
//					  System.out.println(thumbUrl); // http://ma../quickimage/...
//					  System.out.println(title); // 제목
//					  System.out.println(date); // 날짜
//					}
//				} catch (IOException e) {e.printStackTrace();}
//	}

//					Document doc2 = Jsoup.connect("http://www.google.com").post();
	// Response로부터 Document 얻어오기
//					Connection.Response response = Jsoup.connect("http://www.google.com").method(Connection.Method.GET).execute();
//					Document doc3 = response.parse();

	// 얻어낸 Document는 두 가지 방법으롷 출력 .html() & .text()
//					Document document = response.parse();

//					String html = document.html();
//					String text = document.text();

	// 얻어온 결과에서 특정 값 뽑아내기
	// 특정 값, 특정한 html 요소를 얻으려면 select("css query") 메소드를 사용 구글 메인 페이지의 검색 버튼의 value를
	// 얻자

//					Connection.Response response = Jsoup.connect("http://www.google.com")
//							.method(Connection.Method.GET)
//							.execute();
//					Document googleDocument = response.parse();
//					Element btnK = googleDocument.select("input[name=btnK]").first();
//					String btnKValue = btnK.attr("value");
//					
//					System.out.println(btnKValue);
//					
//					Document rawData = Jsoup.connect("http://www.google.com/search?q=게임+모바일")
//							.timeout(5000)
//							.get();
//					
//					Elements articles = rawData.select("tr:not('')");

}
