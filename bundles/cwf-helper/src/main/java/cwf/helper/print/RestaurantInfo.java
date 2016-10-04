package cwf.helper.print;

public class RestaurantInfo {
	private String name;
	private String title;
	private String AddressLine1;
	private String AddressLine2;
	private String AddressLine3;
	private String contactNo;
	private String companyName;
	private String wishMessage;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddressLine1() {
		return AddressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return AddressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		AddressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return AddressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		AddressLine3 = addressLine3;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getWishMessage() {
		return wishMessage;
	}
	public void setWishMessage(String wishMessage) {
		this.wishMessage = wishMessage;
	}
	public RestaurantInfo(String name, String title, String addressLine1, String addressLine2, String addressLine3,
			String contactNo, String companyName, String wishMessage) {
		super();
		this.name = name;
		this.title = title;
		AddressLine1 = addressLine1;
		AddressLine2 = addressLine2;
		AddressLine3 = addressLine3;
		this.contactNo = contactNo;
		this.companyName = companyName;
		this.wishMessage = wishMessage;
	}

}
