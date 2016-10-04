package cwf.helper.print;

public class OrderMeta {

	private String billNumber;
	private String time;
	private String billDate;
	private String tableNumber;
	private String covers;
	private String steward;
	private String srNo;
	private String tinNO;
	private String grandTotal;

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getCovers() {
		return covers;
	}

	public void setCovers(String covers) {
		this.covers = covers;
	}

	public String getSteward() {
		return steward;
	}

	public void setSteward(String steward) {
		this.steward = steward;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getTinNO() {
		return tinNO;
	}

	public void setTinNO(String tinNO) {
		this.tinNO = tinNO;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public OrderMeta(String billNumber, String time, String billDate, String tableNumber, String covers, String steward,
			String srNo, String tinNO, String grandTotal) {
		super();
		this.billNumber = billNumber;
		this.time = time;
		this.billDate = billDate;
		this.tableNumber = tableNumber;
		this.covers = covers;
		this.steward = steward;
		this.srNo = srNo;
		this.tinNO = tinNO;
		this.grandTotal = grandTotal;
	}

}
