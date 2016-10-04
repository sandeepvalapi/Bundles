package cwf.helper.print;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cwf.helper.exception.BusinessException;

@Component
public class LowLevelPrint {

	double PAGE_WIDTH = 7.0;
	int FONT_SIZE = 10;
	int FONT_STYLE_PLAIN = Font.PLAIN;
	int FONT_STYLE_BOLD = Font.BOLD;
	String FONT_FAMILY = Font.SANS_SERIF;
	int ORIENTATION = PageFormat.PORTRAIT;
	Color PEN_COLOR = Color.BLACK;
	int START_POS = 3;
	Font FONT_PLAIN = new Font(FONT_FAMILY, FONT_STYLE_PLAIN, FONT_SIZE);
	Font FONT_BOLD = new Font(FONT_FAMILY, FONT_STYLE_BOLD, FONT_SIZE);
	Font TITLE_FONT = new Font(FONT_FAMILY, FONT_STYLE_BOLD, FONT_SIZE + 1);
	Font TAGLINE_FONT = new Font(FONT_FAMILY, FONT_STYLE_PLAIN, FONT_SIZE - 3);
	Font ADDRESS_FONT = new Font(FONT_FAMILY, FONT_STYLE_PLAIN, FONT_SIZE - 1);
	int pointer;

	BillPrint printObj;

	/**
	 * TODO : Get Items list size
	 *
	 * @param print
	 *
	 * @param args
	 * @throws BusinessException
	 */
	public void actualPrint(BillPrint print) throws BusinessException {

		PrinterJob printJob = PrinterJob.getPrinterJob();
		PageFormat pageFormat = printJob.defaultPage();
		pageFormat.setOrientation(ORIENTATION);
		Paper paper = pageFormat.getPaper();
		double width = fromCmToPpi(PAGE_WIDTH);
		double height = paperHeight(print);
		paper.setSize(width, height);
		paper.setImageableArea(fromCmToPpi(0.25), fromCmToPpi(0.5), width - fromCmToPpi(0.35), height - fromCmToPpi(1));
		pageFormat.setPaper(paper);
		dumpPageFormat(pageFormat);
		printJob.setPrintable(new MyPrintable(), pageFormat);
		printObj = print;
		try {
			printJob.print();
		} catch (PrinterException ex) {
			throw new BusinessException(ex.getMessage());
		}
	}

	/**
	 * Calculates number of lines required for setting paper, considers only
	 * header and footer lines
	 *
	 * @param print
	 * @return double
	 */
	private double paperHeight(BillPrint print) {
		double pHeight = (10 * FONT_SIZE);
		if (print.getFnbItem() != null) {
			pHeight = pHeight + print.getFnbItem().size() * FONT_SIZE + (13 * FONT_SIZE);
		}
		if (print.getBarItem() != null) {
			pHeight = pHeight + print.getBarItem().size() * FONT_SIZE + (13 * FONT_SIZE);
		}
		double headFoot = checkNulls(print);
		pHeight = pHeight + headFoot * FONT_SIZE;
		return pHeight;
	}

	/**
	 * Checks and returns number of lines present in header and footer
	 *
	 * @param print
	 * @return double
	 */
	private double checkNulls(BillPrint print) {

		double count = 0;
		RestaurantInfo info = print.getRestaurantInfo();
		if (StringUtils.isNotEmpty(info.getName())) {
			count++;
		}
		if (StringUtils.isNotEmpty(info.getTitle())) {
			count++;
		}
		if (StringUtils.isNotEmpty(info.getAddressLine1())) {
			count++;
		}
		if (StringUtils.isNotEmpty(info.getAddressLine2())) {
			count++;
		}
		if (StringUtils.isNotEmpty(info.getAddressLine3())) {
			count++;
		}
		if (StringUtils.isNotEmpty(info.getContactNo())) {
			count++;
		}
		if (StringUtils.isNotEmpty(info.getCompanyName())) {
			count++;
		}
		if (StringUtils.isNotEmpty(info.getWishMessage())) {
			count++;
		}
		return count;
	}

	/*
	 * Convert CM to PPI
	 */
	private double fromCmToPpi(double cm) {
		return toPPI(cm * 0.393700787);
	}

	private double toPPI(double inch) {
		return inch * 72d;
	}

	String dumpPageFormat(PageFormat pf) {
		Paper paper = pf.getPaper();
		return dumpPaper(paper);
	}

	private String dumpPaper(Paper paper) {
		StringBuilder sb = new StringBuilder(64);
		sb.append(paper.getWidth()).append("x").append(paper.getHeight()).append("/").append(paper.getImageableX())
				.append("x").append(paper.getImageableY()).append(" - ").append(paper.getImageableWidth()).append("x")
				.append(paper.getImageableHeight());
		return sb.toString();
	}

	public class MyPrintable implements Printable {

		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			int result = NO_SUCH_PAGE;
			if (pageIndex < 1) {
				graphics.setColor(PEN_COLOR);
				graphics.setFont(FONT_PLAIN);
				Graphics2D pen = (Graphics2D) graphics;
				int width = (int) pageFormat.getImageableWidth();
				int height = (int) pageFormat.getImageableHeight();
				pen.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

				Rectangle border = new Rectangle(1, 1, width - 1, height - 1);
				// TODO: Change this line for printing borders
				pen.draw(border);
				FontMetrics metrics = pen.getFontMetrics();
				pointer = metrics.getAscent();
				drawHeader(pen, border);
				pen.setFont(FONT_PLAIN);
				lineSeperator(pen);
				drawOrderDetails(pen, border);
				lineSeperator(pen);
				drawItemsDescription(printObj, pen, border);
				lineSeperator(pen);

				if (printObj.getFnbItem() != null) {
					alignToCenter(pen, "FOOD & BEVERAGE", border, FONT_BOLD);
					pen.setFont(FONT_PLAIN);
					lineSeperator(pen);
					drawFandBItems(pen, border, metrics);
					drawFandBAmountdetails(pen, border, metrics);
				}

				if (printObj.getFnbItem() != null && printObj.getBarItem() != null) {
					pen.drawString("= = = = = = = = = = = = = = = = = = = = = = = = = = = = ", START_POS, pointer);
					getPointerPosition();
				}

				if (printObj.getBarItem() != null) {
					alignToCenter(pen, "BAR", border, FONT_BOLD);
					pen.setFont(FONT_PLAIN);
					lineSeperator(pen);
					drawBarItems(printObj, pen, border, metrics);
					drawBarAmountdetails(printObj, pen, border, metrics);
				}

				lineSeperator(pen);
				drawSrandTin(printObj, pen, border);
				lineSeperator(pen);

				if (printObj.getFnbItem() != null && printObj.getBarItem() != null) {
					drawGrandTotal(printObj, pen, border);
					lineSeperator(pen);
				}

				pointer = drawFooter(printObj, pen, border);
				result = PAGE_EXISTS;
			}
			return result;
		}

		/**
		 * TIN And SR
		 *
		 * @param printObj
		 * @param pen
		 * @param rect
		 * @return
		 */
		private void drawSrandTin(BillPrint printObj, Graphics2D pen, Rectangle rect) {
			OrderMeta orderMeta = printObj.getOrderMeta();
			OrderDetails orderDetails = printObj.getOrderDetails();
			pen.drawString(orderMeta.getSrNo(), START_POS, pointer);
			pen.drawString(":", (rect.width / 4) - 5, pointer);
			pen.drawString(orderDetails.getSrNo(), rect.width / 4, pointer);
			getPointerPosition();
			pen.drawString(orderMeta.getTinNO(), START_POS, pointer);
			pen.drawString(":", (rect.width / 4) - 5, pointer);
			pen.drawString(orderDetails.getTinNo(), rect.width / 4, pointer);
			getPointerPosition();
		}

		/**
		 * Prints Header Values, Sets max length to 33(Based on Font size)
		 *
		 * @param info
		 * @param pen
		 * @param pointer
		 * @param border
		 * @return
		 */
		private int drawHeader(Graphics2D pen, Rectangle border) {
			RestaurantInfo info = printObj.getRestaurantInfo();
			if (StringUtils.isNotEmpty(info.getName())) {
				alignToCenter(pen, info.getName(), border, TITLE_FONT);
			}

			if (StringUtils.isNotEmpty(info.getTitle())) {
				alignToCenter(pen, info.getTitle(), border, TAGLINE_FONT);
			}

			if (StringUtils.isNotEmpty(info.getAddressLine1())) {
				alignToCenter(pen, info.getAddressLine1(), border, ADDRESS_FONT);
			}

			if (StringUtils.isNotEmpty(info.getAddressLine2())) {
				alignToCenter(pen, info.getAddressLine2(), border, ADDRESS_FONT);
			}

			if (StringUtils.isNotEmpty(info.getAddressLine3())) {
				alignToCenter(pen, info.getAddressLine3(), border, ADDRESS_FONT);
			}

			if (StringUtils.isNotEmpty(info.getContactNo())) {
				alignToCenter(pen, info.getContactNo(), border, ADDRESS_FONT);
			}
			return pointer;
		}

		/**
		 * Draws a line with length adjusted to 33 characters
		 *
		 * @param pen
		 * @param pointer
		 * @return
		 */
		private void lineSeperator(Graphics2D pen) {
			pen.drawString("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -", START_POS, pointer);
			getPointerPosition();
		}

		/**
		 * Draws Order details object
		 *
		 * @param printObj
		 * @param pen
		 * @param pointer
		 * @param rect
		 * @return
		 */
		private void drawOrderDetails(Graphics2D pen, Rectangle rect) {
			OrderMeta meta = printObj.getOrderMeta();
			OrderDetails detail = printObj.getOrderDetails();
			int idealspace = 15;
			pen.drawString(meta.getBillNumber(), START_POS, pointer);
			pen.drawString(":", (rect.width / 4) - 5, pointer);
			pen.drawString(detail.getBillId(), rect.width / 4, pointer);
			pen.drawString(meta.getTableNumber(), ((rect.width / 4) * 2) + idealspace, pointer);
			pen.drawString(":", ((rect.width / 4) * 3) + idealspace - 5, pointer);
			pen.drawString(detail.getTableNo(), ((rect.width / 4) * 3) + idealspace, pointer);
			getPointerPosition();

			pen.drawString(meta.getBillDate(), START_POS, pointer);
			pen.drawString(":", (rect.width / 4) - 5, pointer);
			pen.drawString(detail.getBillDate(), rect.width / 4, pointer);
			pen.drawString(meta.getSteward(), ((rect.width / 4) * 2) + idealspace, pointer);
			pen.drawString(":", ((rect.width / 4) * 3) + idealspace - 5, pointer);
			pen.drawString(detail.getSteward(), ((rect.width / 4) * 3) + idealspace, pointer);
			getPointerPosition();

			pen.drawString(meta.getTime(), START_POS, pointer);
			pen.drawString(":", (rect.width / 4) - 5, pointer);
			pen.drawString(detail.getBillTime(), rect.width / 4, pointer);
			pen.drawString(meta.getCovers(), ((rect.width / 4) * 2) + idealspace, pointer);
			pen.drawString(":", ((rect.width / 4) * 3) + idealspace - 5, pointer);
			pen.drawString(detail.getCovers(), ((rect.width / 4) * 3) + idealspace, pointer);

			getPointerPosition();
		}

		/**
		 * Draws Item description
		 *
		 * @param printObj
		 * @param pen
		 * @param rect
		 */
		private void drawItemsDescription(BillPrint printObj, Graphics2D pen, Rectangle rect) {

			ItemsMeta meta = printObj.getItemsMetaData();
			int area = rect.width / 10;
			pen.drawString(meta.getHeading1(), area * 2, pointer);
			pen.drawString(meta.getHeading2(), (int) (area * 6.5), pointer);
			pen.drawString(meta.getHeading3(), area * 8, pointer);
			getPointerPosition();
		}

		/**
		 * Draws F&B Items
		 *
		 * @param pen
		 * @param rect
		 * @param metrics
		 */
		private void drawFandBItems(Graphics2D pen, Rectangle rect, FontMetrics metrics) {
			List<FnBItem> items = printObj.getFnbItem();
			for (FnBItem fb : items) {
				if (fb.getItemName().length() <= 21) {
					pen.drawString(fb.getItemName(), START_POS, pointer);
				} else {
					pen.drawString(fb.getItemName().substring(0, 18) + " . . . ", START_POS, pointer);
				}
				pen.drawString(fb.getQuantity(), (rect.width / 10) * 7, pointer);
				pen.drawString(fb.getPrice(), rect.width - metrics.stringWidth(fb.getPrice()), pointer);
				getPointerPosition();
			}
			getPointerPosition();
		}

		/**
		 * Prints Bar Items on Bill
		 *
		 * @param printObj
		 * @param pen
		 * @param rect
		 * @param metrics
		 */
		private void drawBarItems(BillPrint printObj, Graphics2D pen, Rectangle rect, FontMetrics metrics) {
			List<BarItem> items = printObj.getBarItem();
			for (BarItem barItem : items) {
				if (barItem.getItemName().length() <= 21) {
					pen.drawString(barItem.getItemName(), START_POS, pointer);
				} else {
					pen.drawString(barItem.getItemName().substring(0, 18) + " . . . .", START_POS, pointer);
				}
				pen.drawString(barItem.getQuantity(), (rect.width / 10) * 7, pointer);
				pen.drawString(barItem.getPrice(), rect.width - metrics.stringWidth(barItem.getPrice()), pointer);
				getPointerPosition();
			}
			getPointerPosition();
		}

		/**
		 * Draws F&B Object details
		 *
		 * @param pen
		 * @param rect
		 * @param metrics
		 * @return
		 */
		private void drawFandBAmountdetails(Graphics2D pen, Rectangle rect, FontMetrics metrics) {
			FnBAmountMeta fbAmountMeta = printObj.getFnbAmountMeta();
			FnBAmount fbAmount = printObj.getFnbAmount();

			int idealspace = 15;

			pen.drawString(fbAmountMeta.getSubTotalMeta(), START_POS, pointer);
			pen.drawString(fbAmount.getSubTotal(), rect.width - metrics.stringWidth(fbAmount.getSubTotal()), pointer);
			getPointerPosition();

			pen.drawString(fbAmountMeta.getSchMeta(), START_POS, pointer);
			pen.drawString(fbAmount.getSch(), rect.width - metrics.stringWidth(fbAmount.getSch()), pointer);
			getPointerPosition();

			pen.drawString(fbAmountMeta.getStxMeta(), START_POS, pointer);
			pen.drawString(fbAmount.getStx(), rect.width - metrics.stringWidth(fbAmount.getStx()), pointer);
			getPointerPosition();
			pen.drawString(fbAmountMeta.getVatMeta(), START_POS, pointer);
			pen.drawString(fbAmount.getVat(), rect.width - metrics.stringWidth(fbAmount.getVat()), pointer);
			getPointerPosition();
			lineSeperator(pen);
			pen.setFont(FONT_BOLD);
			pen.drawString(fbAmountMeta.getTotalAmountMeta(), START_POS + idealspace, pointer);
			pen.drawString(fbAmount.getTotalAmount(), rect.width - metrics.stringWidth(fbAmount.getTotalAmount()),
					pointer);

			pen.setFont(FONT_PLAIN);

			getPointerPosition();
		}

		private void drawBarAmountdetails(BillPrint printObj, Graphics2D pen, Rectangle rect, FontMetrics metrics) {
			int idealspace = 15;
			BarAmountMeta barAmountMeta = printObj.getBarAmountMeta();
			BarAmount barAmount = printObj.getBarAmount();

			pen.drawString(barAmountMeta.getSubTotal(), START_POS, pointer);
			pen.drawString(barAmount.getSubTotal(), rect.width - metrics.stringWidth(barAmount.getSubTotal()), pointer);
			getPointerPosition();

			pen.drawString(barAmountMeta.getServiceCharge(), START_POS, pointer);
			pen.drawString(barAmount.getServiceCharge(), rect.width - metrics.stringWidth(barAmount.getServiceCharge()),
					pointer);
			getPointerPosition();

			pen.drawString(barAmountMeta.getTotalExcTaxes(), START_POS, pointer);
			pen.drawString(barAmount.getTotalExcTaxes(), rect.width - metrics.stringWidth(barAmount.getTotalExcTaxes()),
					pointer);
			getPointerPosition();

			pen.drawString(barAmountMeta.getGst(), START_POS, pointer);
			pen.drawString(barAmount.getGst(), rect.width - metrics.stringWidth(barAmount.getGst()), pointer);
			getPointerPosition();

			lineSeperator(pen);
			pen.setFont(FONT_BOLD);
			pen.drawString(barAmountMeta.getBarTotal(), START_POS + idealspace, pointer);
			pen.drawString(barAmount.getBarTotal(), rect.width - metrics.stringWidth(barAmount.getBarTotal()), pointer);
			pen.setFont(FONT_PLAIN);
			// pointer = drawStar(pen, pointer);
			getPointerPosition();
		}

		private void drawGrandTotal(BillPrint printObj, Graphics2D pen, Rectangle rect) {
			OrderMeta orderMeta = printObj.getOrderMeta();
			OrderDetails orderDetails = printObj.getOrderDetails();
			int idealspace = 20;
			pen.setFont(FONT_BOLD);

			pen.drawString(orderMeta.getGrandTotal(), START_POS + idealspace, pointer);
			pen.setFont(TITLE_FONT);
			pen.drawString(orderDetails.getGrandTotal(), rect.width / 2 + idealspace, pointer);
			pen.setFont(FONT_PLAIN);

			getPointerPosition();
		}

		private int drawFooter(BillPrint printObj, Graphics2D pen, Rectangle rect) {
			RestaurantInfo restaurantInfo = printObj.getRestaurantInfo();
			if (StringUtils.isNotEmpty(printObj.getRestaurantInfo().getCompanyName())) {
				alignToCenter(pen, restaurantInfo.getCompanyName(), rect, TAGLINE_FONT);
			}
			if (StringUtils.isNotEmpty(printObj.getRestaurantInfo().getWishMessage())) {
				alignToCenter(pen, restaurantInfo.getWishMessage(), rect, FONT_PLAIN);
			}
			return pointer;
		}

		/**
		 * Returns pointer location
		 *
		 * @param pointerVal
		 * @return
		 */
		private void getPointerPosition() {
			pointer = pointer + FONT_SIZE;
		}

		public void alignToCenter(Graphics pen, String text, Rectangle rect, Font font) {
			FontMetrics metrics = pen.getFontMetrics(font);
			int x = (rect.width - metrics.stringWidth(text)) / 2;
			pen.setFont(font);
			pen.drawString(text, x, pointer);
			getPointerPosition();
		}

	}

}
