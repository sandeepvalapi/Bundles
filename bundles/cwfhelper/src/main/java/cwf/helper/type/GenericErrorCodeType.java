package cwf.helper.type;

import cwf.helper.BusinessErrorCode;

public enum GenericErrorCodeType implements BusinessErrorCode {
	UNKNOWN_HOST_ERROR(90001), GENERIC_ERROR(99999);

	private int value;

	GenericErrorCodeType(int value) {
		this.value = value;
	}

	public Integer getBusinessErrorCode() {
		return value;
	}
}
