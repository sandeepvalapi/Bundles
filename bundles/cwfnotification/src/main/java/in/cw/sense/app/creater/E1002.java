package in.cw.sense.app.creater;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import cwf.notification.creator.Payload;

public class E1002 extends CreateEmail {

	private OrderPayload payload;
	private TemplateSource source;

	public OrderPayload getPayload() {
		return payload;
	}

	public void setPayload(OrderPayload payload) {
		this.payload = payload;
	}

	public TemplateSource getSource() {
		return source;
	}

	public void setSource(TemplateSource source) {
		this.source = source;
	}

	public E1002(Payload payload, TemplateSource source) {
		super();
		this.payload = (OrderPayload) payload;
		this.source = source;
	}

	public String getEmailContent() {
		// Do mapping

		StringBuffer subTemplateString = new StringBuffer();
		StrSubstitutor strSubstitutor;

		for (Item item : payload.getItems()) {
			Map<String, String> mapForSubTemplate = new HashMap<>();
			mapForSubTemplate.put("name", item.getName());
			mapForSubTemplate.put("quantity", item.getQuantity().toString());
			mapForSubTemplate.put("price", item.getPrice().toString());
			strSubstitutor = new StrSubstitutor(mapForSubTemplate);
			subTemplateString.append(strSubstitutor.replace(source.getInner()));
		}
		RestaurantInformation restInfo = payload.getRestaurantInformation();
		OrderInfo orderInfo = payload.getOrderInfo();
		Map<String, String> mapOfMainTemplate = new HashMap<>();
		mapOfMainTemplate.put("invoice", orderInfo.getInvoice().toString());
		mapOfMainTemplate.put("username", orderInfo.getUserName());
		mapOfMainTemplate.put("orderId", orderInfo.getOrderId().toString());
		mapOfMainTemplate.put("address", restInfo.getAddress());
		mapOfMainTemplate.put("contact", restInfo.getPhoneNumber());
		mapOfMainTemplate.put("repeat-values", subTemplateString.toString());
		strSubstitutor = new StrSubstitutor(mapOfMainTemplate);
		return strSubstitutor.replace(source.getOuter());

	}

}
