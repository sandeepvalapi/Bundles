package cwf.file;

public enum FileType
{
	PDF("pdf"), PNG("png");

	private String value;

	private FileType(String value) {
		this.value = value;
	}

	public String getByFormat() {
		return value;
	}
}
