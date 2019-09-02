package kr.co.forearlybird.common;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	public static void main(String[] args) {
		try {
			String siteURL = "http://www.inews24.com/list/game";
			System.out.println("=====================================================================");
			System.out.println("URL :" + siteURL);
			// URL에 접속해 Document를 얻어내기
			// 간략화 된 GET,POST
			Document doc1 = Jsoup.connect(siteURL).get();

			Elements title = doc1.select(".list");
			Elements title3 = title.select("li");

			System.out.println("=====================================================================");
			for (Element e : title3) {
				String cnt_title = e.select("a").text();
				String cnt_thumbnail = e.getElementsByAttribute("src").attr("src");
				String cnt_link = e.getElementsByAttribute("href").attr("href");
				System.out.println("titleeeeeeeeeeeeeeee = "+cnt_title);
				System.out.println("imgggggggggggggggggg = "+cnt_thumbnail);
				System.out.println("linkkkkkkkkkkkkkkkkk = "+cnt_link);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}