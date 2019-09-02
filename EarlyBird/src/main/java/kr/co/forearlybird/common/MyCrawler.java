package kr.co.forearlybird.common;

import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		
		// 크롤링 대상인지 판단 
		String href = url.getURL();
		logger.debug("혹시 크롤러 대상 맞니? href: {}", href);
        return href.startsWith("https://www.google.com/");
	}

	@Override
	public void visit(Page page) {
		// 방문한 페이지의 내용을 처리 
		
		int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String domain = page.getWebURL().getDomain();
        String path = page.getWebURL().getPath();
        String subDomain = page.getWebURL().getSubDomain();
        String parentUrl = page.getWebURL().getParentUrl();
        String anchor = page.getWebURL().getAnchor();
        
		logger.debug("Docid: {}", docid);
        logger.info("URL: {}", url);
        logger.debug("Domain: '{}'", domain);
        logger.debug("Sub-domain: '{}'", subDomain);
        logger.debug("Path: '{}'", path);
        logger.debug("Parent page: {}", parentUrl);
        logger.debug("Anchor text: {}", anchor);
        
        if (page.getParseData() instanceof HtmlParseData) { // html 형태의 Data일 경우
        	
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            
            logger.debug("Text 길ㅣ는!!~~~~ {}", text.length());
            logger.debug("HTML 길이는~~ {}", html.length());
            logger.debug("링크는 어디냐~   {}", links.size());
            
            // 페이지의 HTML을 파싱하기
            Document doc = Jsoup.parse(html);

            // 내용만 추출하기
            String content = doc.select("div#content_text").html();
            
            logger.debug("div#content_text : {}", content);
        }
	}	
}