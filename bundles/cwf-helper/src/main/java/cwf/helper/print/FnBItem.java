package cwf.helper.print;

public class FnBItem {
	private String itemName;
	private String quantity;
	private String price;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public FnBItem(String itemName, String quantity, String price) {

		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}

}