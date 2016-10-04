package cwf.helper.print;

import java.util.List;

public class BillPrint {
	private RestaurantInfo restaurantInfo;
	private OrderMeta orderMeta;
	private OrderDetails orderDetails;
	private ItemsMeta itemsMetaData;
	private List<FnBItem> fnbItem;
	private FnBAmount fnbAmount;
	private FnBAmountMeta fnbAmountMeta;
	private List<BarItem> barItem;
	private BarAmount barAmount;
	private BarAmountMeta barAmountMeta;

	public RestaurantInfo getRestaurantInfo() {
		return restaurantInfo;
	}

	public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
		this.restaurantInfo = restaurantInfo;
	}

	public OrderMeta getOrderMeta() {
		return orderMeta;
	}

	public void setOrderMeta(OrderMeta orderMeta) {
		this.orderMeta = orderMeta;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	public ItemsMeta getItemsMetaData() {
		return itemsMetaData;
	}

	public void setItemsMetaData(ItemsMeta itemsMetaData) {
		this.itemsMetaData = itemsMetaData;
	}

	public List<FnBItem> getFnbItem() {
		return fnbItem;
	}

	public void setFnbItem(List<FnBItem> fnbItem) {
		this.fnbItem = fnbItem;
	}

	public FnBAmount getFnbAmount() {
		return fnbAmount;
	}

	public void setFnbAmount(FnBAmount fnbAmount) {
		this.fnbAmount = fnbAmount;
	}

	public FnBAmountMeta getFnbAmountMeta() {
		return fnbAmountMeta;
	}

	public void setFnbAmountMeta(FnBAmountMeta fnbAmountMeta) {
		this.fnbAmountMeta = fnbAmountMeta;
	}

	public List<BarItem> getBarItem() {
		return barItem;
	}

	public void setBarItem(List<BarItem> barItem) {
		this.barItem = barItem;
	}

	public BarAmount getBarAmount() {
		return barAmount;
	}

	public void setBarAmount(BarAmount barAmount) {
		this.barAmount = barAmount;
	}

	public BarAmountMeta getBarAmountMeta() {
		return barAmountMeta;
	}

	public void setBarAmountMeta(BarAmountMeta barAmountMeta) {
		this.barAmountMeta = barAmountMeta;
	}

	public BillPrint() {
		super();
	}

	public BillPrint(RestaurantInfo restaurantInfo, OrderMeta orderMeta, OrderDetails orderDetails,
			ItemsMeta itemsMetaData, List<FnBItem> fnbItem, FnBAmount fnbAmount, FnBAmountMeta fnbAmountMeta,
			List<BarItem> barItem, BarAmount barAmount, BarAmountMeta barAmountMeta) {
		super();
		this.restaurantInfo = restaurantInfo;
		this.orderMeta = orderMeta;
		this.orderDetails = orderDetails;
		this.itemsMetaData = itemsMetaData;
		this.fnbItem = fnbItem;
		this.fnbAmount = fnbAmount;
		this.fnbAmountMeta = fnbAmountMeta;
		this.barItem = barItem;
		this.barAmount = barAmount;
		this.barAmountMeta = barAmountMeta;
	}

}
