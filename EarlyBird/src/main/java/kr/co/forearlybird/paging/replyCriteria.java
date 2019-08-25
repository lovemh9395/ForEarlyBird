package kr.co.forearlybird.paging;

//게시판 페이징 전용 클래스
//Criteria : 사전적 의미로는 검색기준, 분류기준
public class replyCriteria {

	private int replypage; // 보여줄 페이지 번호
	private int replyperPageNum; // 페이지당 보여줄 게시글의 개수

	public replyCriteria() {
		// 최초 게시판에 진입할 때를 위해서 기본 값을 설정 해야 한다.
		this.replypage = 1;
		this.replyperPageNum = 10;
	}

	// 댓글 개수 지정해서 보기
	public replyCriteria(int replyperPageNum) {
		this.replypage = 1;
		this.replyperPageNum = replyperPageNum;
	}

	public int getreplyPage() {
		return replypage;
	}

	public void setreplyPage(int replypage) {
		if (replypage <= 0) {
			this.replypage = 1;
			return;
		}

		this.replypage = replypage;
	}

	public int getReplyperPageNum() {
		return replyperPageNum;
	}

	public void setReplyperPageNum(int replyperPageNum) {
		
		if (replyperPageNum <= 0 || replyperPageNum > 100) {
			this.replyperPageNum = 10;
			return;
		}
		
		this.replyperPageNum = replyperPageNum;
	}

	/*
	 * limit 구문에서 시작 위치를 지정할 때 사용된다. 예를 들어 10개씩 출력하는 경우 3페이지의 데이터는 linit 20, 10 과 같은
	 * 형태가 되어야 한다.
	 */
	/* this.page 가 1이면 0이 되어야 한다 mysql limit 0, 10 해야 처음부터 10개씩 나온다. */
	/* 마이바티스 조회쿼리의 #{pageStart}에 전달된다. */
	public int getreplypageStart() {
		return (this.replypage - 1) * replyperPageNum;
	}

	@Override
	public String toString() {
		return "replyCriteria [replypage=" + replypage + ", replyperPageNum=" + replyperPageNum + "]";
	}
}