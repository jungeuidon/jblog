package kr.co.itcen.jblog3.vo;

public class PostVo {

	private int no;
	private Long categoryNo;
	private String title, contents, postdate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", categoryNo=" + categoryNo + ", title=" + title + ", contents=" + contents
				+ ", postdate=" + postdate + "]";
	}
	
	
}
