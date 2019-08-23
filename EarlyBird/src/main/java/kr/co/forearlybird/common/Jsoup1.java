package kr.co.forearlybird.common;

import java.io.IOException;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("deprecation")
public class Jsoup1 {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("https://sports.news.naver.com/kfootball/record/index.nhn");
		try {
			httpClient.execute(httpget, new BasicResponseHandler() {
				@Override
				public String handleResponse(HttpResponse response) throws HttpResponseException, IOException {
					// 웹페이지를 그냥 갖어오면 한글이 깨져요. 인코딩 처리를 해야해요.
					String res = new String(super.handleResponse(response).getBytes("8859_1"), "euc-kr");
					Document doc = Jsoup.parse(res);
					Elements rows = doc.select("table#regularGroup tr th");
					String[] items = new String[] { "경기수", "승점", "승", "무", "패", "득점", "실점", "득실차", "도움", "파울" };
					for (Element row : rows) {
						Iterator<Element> iterElem = row.getElementsByTag("td").iterator();
						StringBuilder builder = new StringBuilder();
						for (String item : items) {
							builder.append(item + ": " + iterElem.next().text() + "   \t");
						}
						System.out.println(builder.toString());
					}

					return res;
				}
			});
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}