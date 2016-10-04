package cwf.date.format;

public enum DateFormatType
{
	DD_MM_YYYY("dd-MM-yyyy"), DD_MMM_YYYY("dd-MMM-yyyy"), DD_MM("dd/MM"), YYYY_MM_DD("yyyy-MM-dd"), ISO_DATE_FORMAT(
			"yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	private String value;

	private DateFormatType(String value) {
		this.value = value;
	}

	public String getByFormat() {
		return value;
	}
}
