package kr.co.forearlybird.paging;

public class replyPageMaker {
	private int replytotalCount;     // 게시판 전체 데이터 개수
	private int replydisplayPageNum = 5;   // 게시판 화면에서 한번에 보여질 페이지 번호의 개수 ( 1,2,3,4,5,6,7,9,10)
	
	private int replystartPage;      // 현재 화면에서 보이는 startPage 번호
	private int replyendPage;        // 현재 화면에 보이는 endPage 번호
	private boolean replyprev;       // 페이징 이전 버튼 활성화 여부
	private boolean replynext;       // 페이징 다음 버튼 활성화 여부
	
	private replyCriteria replycri;       // 앞서 생성한 Criteria를 주입받는다.

	public int getreplyTotalCount() {
		return replytotalCount;
	}

	public void setreplyTotalCount(int replytotalCount) {
		this.replytotalCount = replytotalCount;
		
		calcData();
	}
	
	private void calcData() {
		replyendPage = (int) (Math.ceil(replycri.getreplyPage() / (double) replydisplayPageNum) * replydisplayPageNum);
		
		replystartPage = (replyendPage - replydisplayPageNum) + 1;
		
		int replytempEndPage = (int) (Math.ceil(replytotalCount / (double) replycri.getReplyperPageNum()));
		
		if(replyendPage > replytempEndPage) {
			replyendPage = replytempEndPage;
		}
		
		replyprev = replystartPage == 1 ? false : true;
		
		replynext = replyendPage * replycri.getReplyperPageNum() >= replytotalCount ? false : true;
	}

	

	public int getReplystartPage() {
		return replystartPage;
	}

	public void setReplystartPage(int replystartPage) {
		this.replystartPage = replystartPage;
	}

	public int getReplyendPage() {
		return replyendPage;
	}

	public void setReplyendPage(int replyendPage) {
		this.replyendPage = replyendPage;
	}

	public boolean isPrev() {
		return replyprev;
	}

	public void setreplyPrev(boolean replyprev) {
		this.replyprev = replyprev;
	}

	public boolean isNext() {
		return replynext;
	}

	public void setreplyNext(boolean replynext) {
		this.replynext = replynext;
	}

	public int getreplyDisplayPageNum() {
		return replydisplayPageNum;
	}

	public void setreplyDisplayPageNum(int replydisplayPageNum) {
		this.replydisplayPageNum = replydisplayPageNum;
	}

	public replyCriteria getReplycri() {
		return replycri;
	}

	public void setReplycri(replyCriteria replycri) {
		this.replycri = replycri;
	}

	@Override
	public String toString() {
		return "replyPageMaker [replytotalCount=" + replytotalCount + ", replystartPage=" + replystartPage + ", replyendPage=" + replyendPage + ", replyprev="
				+ replyprev + ", replynext=" + replynext + ", replydisplayPageNum=" + replydisplayPageNum + ", replycri=" + replycri + "]";
	}
}
