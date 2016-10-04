package cwf.helper.print;

public class FnBAmount {
	private String subTotal;
	private String sch;
	private String stx;
	private String vat;
	private String totalAmount;

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getSch() {
		return sch;
	}

	public void setSch(String sch) {
		this.sch = sch;
	}

	public String getStx() {
		return stx;
	}

	public void setStx(String stx) {
		this.stx = stx;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public FnBAmount(String subTotal, String sch, String stx, String vat, String totalAmount) {
		super();
		this.subTotal = subTotal;
		this.sch = sch;
		this.stx = stx;
		this.vat = vat;
		this.totalAmount = totalAmount;
	}

}
