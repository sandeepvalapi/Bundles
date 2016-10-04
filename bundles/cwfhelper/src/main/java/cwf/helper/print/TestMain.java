package cwf.helper.print;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cwf.helper.exception.BusinessException;

@Component
public class TestMain {

	@Autowired
	LowLevelPrint lowLevelPrint;

	public void main() throws BusinessException {

		RestaurantInfo restaurantInfo = new RestaurantInfo("FIREWATER", "(KITCHEN&BAR)",
				"5th Floor&Terrace, Phoneix Tower A", "Opp Trident Hotel,Hitech City", "Madhapur,Hyderabad-500081",
				"PH No: 040-2001-2001", "Developed by Whitebay Solutions PVT Ltd", "THANK YOU ... VISIT AGAIN");

		OrderMeta orderMeta = new OrderMeta("Bill#", "Time", "Date", "Table", "Covers", "Steward", "SR NO", "Tin No",
				"GRAND TOTAL");
		OrderDetails orderDetails = new OrderDetails("12551", "06-02-16", "09:56", "006", "SSID", "06",
				"AATCS8277MSD001", "36968009069", "19000.00");
		ItemsMeta itemsMetaData = new ItemsMeta("Item", "Qty", "Value");
		// F&B
		List<FnBItem> fnbItem = new ArrayList<>();
		fnbItem.add(new FnBItem("Chicken_65", "2", "250.00"));
		fnbItem.add(new FnBItem("Mutton", "1", "350.00"));
		fnbItem.add(new FnBItem("water bottle", "2", "30.00"));
		fnbItem.add(new FnBItem("fish", "4", "400.00"));
		fnbItem.add(new FnBItem("Hot&SourNonvegsouph21abcda", "3", "35000.00"));
		fnbItem.add(new FnBItem("Roti", "4", "160.00"));
		fnbItem.add(new FnBItem("Tikka", "4", "50.00"));
		fnbItem.add(new FnBItem("Hot&Sour vegetarian soup", "2", "20000.00"));
		fnbItem.add(new FnBItem("Rice", "1", "100.00"));
		fnbItem.add(new FnBItem("Chicken Dum Biryani", "2", "460.00"));
		FnBAmountMeta fnbAmountMeta = new FnBAmountMeta("sub Total", "SCH 7.0 %", "STX 5.80 %", "VAT 14.50%",
				"Total Amt");
		FnBAmount fnbAmount = new FnBAmount("2155.00", "150.85", "133.71", "288.90", "2728.00");

		// Bar Details

		List<BarItem> barItem = new ArrayList<>();
		barItem.add(new BarItem("baritem1", "2", "200"));
		barItem.add(new BarItem("baritem2", "1", "100"));
		barItem.add(new BarItem("baritem3", "3", "2000"));
		barItem.add(new BarItem("baritem4", "1", "450"));
		barItem.add(new BarItem("baritem5", "1", "300"));
		BarAmountMeta barAmountMeta = new BarAmountMeta("Sub Total", "SCH 7.0 %", "Total(Exc.Taxes) 3.07%", "GST 2.89%",
				"Bar Total");
		BarAmount barAmount = new BarAmount("1500.00", "30.00", "330.00", "59.40", "1900.00");

		BillPrint print = new BillPrint(restaurantInfo, orderMeta, orderDetails, itemsMetaData, fnbItem, fnbAmount,
				fnbAmountMeta, barItem, barAmount, barAmountMeta);
		lowLevelPrint.actualPrint(print);
	}

}