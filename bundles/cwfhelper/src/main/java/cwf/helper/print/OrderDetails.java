package cwf.helper.print;

public class OrderDetails {

	private String billId;
	private String billDate;
	private String billTime;
	private String tableNo;
	private String steward;
	private String covers;
	private String srNo;
	private String tinNo;
	private String grandTotal;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getBillTime() {
		return billTime;
	}

	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public String getSteward() {
		return steward;
	}

	public void setSteward(String steward) {
		this.steward = steward;
	}

	public String getCovers() {
		return covers;
	}

	public void setCovers(String covers) {
		this.covers = covers;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getTinNo() {
		return tinNo;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public OrderDetails(String billId, String billDate, String billTime, String tableNo, String steward, String covers,
			String srNo, String tinNo, String grandTotal) {
		super();
		this.billId = billId;
		this.billDate = billDate;
		this.billTime = billTime;
		this.tableNo = tableNo;
		this.steward = steward;
		this.covers = covers;
		this.srNo = srNo;
		this.tinNo = tinNo;
		this.grandTotal = grandTotal;
	}

}
