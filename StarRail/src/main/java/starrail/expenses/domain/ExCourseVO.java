package starrail.expenses.domain;


public class ExCourseVO{
	private int c_id;
	private String m_id;
	private String i_name;
	private String c_name;
	private long c_filename;
	
	public ExCourseVO(){}

	
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public long getC_filename() {
		return c_filename;
	}
	public void setC_filename(long c_filename) {
		this.c_filename = c_filename;
	}
	
	@Override
	public String toString() {
		return "ExCourseVO [c_id=" + c_id + ", m_id=" + m_id + ", i_name=" + i_name + ", c_name=" + c_name
				+ ", c_filename=" + c_filename + "]";
	}
}
