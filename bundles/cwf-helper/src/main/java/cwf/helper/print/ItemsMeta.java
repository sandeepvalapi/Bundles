package cwf.helper.print;

public class ItemsMeta {
	private String heading1;
	private String heading2;
	private String heading3;

	public String getHeading1() {
		return heading1;
	}

	public void setHeading1(String heading1) {
		this.heading1 = heading1;
	}

	public String getHeading2() {
		return heading2;
	}

	public void setHeading2(String heading2) {
		this.heading2 = heading2;
	}

	public String getHeading3() {
		return heading3;
	}

	public void setHeading3(String heading3) {
		this.heading3 = heading3;
	}

	public ItemsMeta(String heading1, String heading2, String heading3) {
		super();
		this.heading1 = heading1;
		this.heading2 = heading2;
		this.heading3 = heading3;
	}

}