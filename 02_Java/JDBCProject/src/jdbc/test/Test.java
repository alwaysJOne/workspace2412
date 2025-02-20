package jdbc.test;

import java.sql.Date;
import java.util.Objects;

public class Test {
	private int tno;
	private String tname;
	private Date tdate;
	
	public Test() {
		super();
	}

	public Test(int tno, String tname, Date tdate) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.tdate = tdate;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Date getTdate() {
		return tdate;
	}

	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tno);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Test) {
			Test t = (Test)obj;
			return t.getTno() == this.tno;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Test [tno=" + tno + ", tname=" + tname + ", tdate=" + tdate + "]";
	}
}
