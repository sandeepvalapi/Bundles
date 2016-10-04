package cwf.helper.print;

public class BarAmount {

	private String subTotal;
	private String serviceCharge;
	private String totalExcTaxes;
	private String gst;
	private String barTotal;
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getTotalExcTaxes() {
		return totalExcTaxes;
	}
	public void setTotalExcTaxes(String totalExcTaxes) {
		this.totalExcTaxes = totalExcTaxes;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getBarTotal() {
		return barTotal;
	}
	public void setBarTotal(String barTotal) {
		this.barTotal = barTotal;
	}
	public BarAmount(String subTotal, String serviceCharge, String totalExcTaxes, String gst, String barTotal) {
		super();
		this.subTotal = subTotal;
		this.serviceCharge = serviceCharge;
		this.totalExcTaxes = totalExcTaxes;
		this.gst = gst;
		this.barTotal = barTotal;
	}
	
}
