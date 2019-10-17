package kr.co.itcen.jblog3.vo;

public class CategoryVo {
	
	private int no;
	private String name, id, info, date;
	private long count;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", id=" + id + ", info=" + info + ", date=" + date
				+ ", count=" + count + "]";
	}
	
	

	
}
