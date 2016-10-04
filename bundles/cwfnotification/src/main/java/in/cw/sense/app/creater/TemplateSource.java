package in.cw.sense.app.creater;

public class TemplateSource {

	// collection of strings

	private String outer;
	private String inner;

	public String getOuter() {
		return outer;
	}

	public void setOuter(String outer) {
		this.outer = outer;
	}

	public String getInner() {
		return inner;
	}

	public void setInner(String inner) {
		this.inner = inner;
	}

	public TemplateSource(String outer, String inner) {
		super();
		this.outer = outer;
		this.inner = inner;
	}

	public TemplateSource() {
		// TODO Auto-generated constructor stub
	}

}
