package cwf.helper.print;

public class FnBAmountMeta {
	private String subTotalMeta;
	private String schMeta;
	private String stxMeta;
	private String vatMeta;
	private String totalAmountMeta;

	public String getSubTotalMeta() {
		return subTotalMeta;
	}

	public void setSubTotalMeta(String subTotalMeta) {
		this.subTotalMeta = subTotalMeta;
	}

	public String getSchMeta() {
		return schMeta;
	}

	public void setSchMeta(String schMeta) {
		this.schMeta = schMeta;
	}

	public String getStxMeta() {
		return stxMeta;
	}

	public void setStxMeta(String stxMeta) {
		this.stxMeta = stxMeta;
	}

	public String getVatMeta() {
		return vatMeta;
	}

	public void setVatMeta(String vatMeta) {
		this.vatMeta = vatMeta;
	}

	public String getTotalAmountMeta() {
		return totalAmountMeta;
	}

	public void setTotalAmountMeta(String totalAmountMeta) {
		this.totalAmountMeta = totalAmountMeta;
	}

	public FnBAmountMeta(String subTotalMeta, String schMeta, String stxMeta, String vatMeta, String totalAmountMeta) {
		super();
		this.subTotalMeta = subTotalMeta;
		this.schMeta = schMeta;
		this.stxMeta = stxMeta;
		this.vatMeta = vatMeta;
		this.totalAmountMeta = totalAmountMeta;
	}

}
