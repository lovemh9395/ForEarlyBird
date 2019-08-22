package kr.co.forearlybird.domain;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("rawtypes")
@Data
@EqualsAndHashCode(callSuper = true)
public class A_postListDTO extends Post implements Comparable{
	private String large_name;
	private String category_name;
	private LocalDate Post_simpletime;
	private String mem_nickname;
	
	@Override
	public int compareTo(Object arg0) {
		Post post = (Post) arg0;
		Integer thisPostid = this.getPost_id();
		Integer thatPostid = post.getPost_id();
		return thisPostid.compareTo(thatPostid);
	}
}
