package starrail.recommend.domain;

public class HashSearchVO2 {
	private int hs_no;
	private String hs_search;
	private int hs_count;
	
	public HashSearchVO2(){}
	
	public HashSearchVO2(int hs_no, String hs_search, int hs_count) {
		super();
		this.hs_no = hs_no;
		this.hs_search = hs_search;
		this.hs_count = hs_count;
	}
	public int getHs_no() {
		return hs_no;
	}
	public void setHs_no(int hs_no) {
		this.hs_no = hs_no;
	}
	public String getHs_search() {
		return hs_search;
	}
	public void setHs_search(String hs_search) {
		this.hs_search = hs_search;
	}
	public int getHs_count() {
		return hs_count;
	}
	public void setHs_count(int hs_count) {
		this.hs_count = hs_count;
	}
	@Override
	public String toString() {
		return "HashSearchVO [hs_no=" + hs_no + ", hs_search=" + hs_search + ", hs_count=" + hs_count + "]";
	}
	
	
}
