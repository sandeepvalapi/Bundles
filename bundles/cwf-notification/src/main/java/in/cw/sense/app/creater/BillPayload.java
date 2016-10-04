package in.cw.sense.app.creater;

import java.util.List;

import cwf.notification.creator.Payload;

public class BillPayload extends Payload {

	OrderInfo orderInfo;
	RestaurantInformation restaurantInformation;
	List<Item> items;

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public RestaurantInformation getRestaurantInformation() {
		return restaurantInformation;
	}

	public void setRestaurantInformation(RestaurantInformation restaurantInformation) {
		this.restaurantInformation = restaurantInformation;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public BillPayload(Integer templateId, OrderInfo orderInfo, RestaurantInformation restaurantInformation,
			List<Item> items) {
		super(templateId);
		this.orderInfo = orderInfo;
		this.restaurantInformation = restaurantInformation;
		this.items = items;
	}

	public BillPayload() {
	}

}
