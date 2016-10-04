package cwf.helper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cwf.helper.exception.BusinessException;
import cwf.helper.exception.BusinessNoRollbackException;
import cwf.helper.exception.ErrorCodesGettable;

@Component
public class ResponseHelperImpl implements ResponseHelper {
	private static final boolean DEAFULT_ROLLBACK_FAILURE = true;
	private static final Integer GENERIC_ERROR = 99999;
	private ResourceBundle resource = ResourceBundle.getBundle("cw-error-code");

	@Override
	public <T> T success(T response) {
		setResult(response, true);
		return response;
	}

	/*
	 * public <T> T failure(T response) { setResult(response, false); return
	 * response; }
	 */

	@Override
	public <T> T failure(T response, boolean rollback) {
		return failure(response, Collections.<BusinessErrorCode> emptyList(), rollback);
	}

	@Override
	public <T> T failure(T response, BusinessErrorCode businessErrorCode) {
		return failure(response, businessErrorCode, DEAFULT_ROLLBACK_FAILURE);
	}

	@Override
	public <T> T failure(T response, BusinessErrorCode businessErrorCode, boolean rollback) {
		// TODO Auto-generated method stub
		return failure(response, Collections.singleton(businessErrorCode), rollback);
	}

	@Override
	public <T> T failure(T response, Iterable<BusinessErrorCode> businessErrorCodes) {
		return failure(response, businessErrorCodes, DEAFULT_ROLLBACK_FAILURE);
	}

	@Override
	public <T> T failure(T response, Iterable<BusinessErrorCode> businessErrorCodes, boolean rollback) {
		List<Integer> errorCodes = getErrorCodes(businessErrorCodes);
		setResponse(response, false, errorCodes, rollback);
		return response;
	}

	@Override
	public <T> T failure(T response, ErrorCodesGettable exception) {
		List<Integer> errorCodes = getErrorCodes(exception);
		boolean rollback = !(exception instanceof BusinessNoRollbackException);
		setResponse(response, false, errorCodes, rollback);
		return response;
	}

	List<Integer> getErrorCodes(ErrorCodesGettable exception) {
		List<Integer> errorCodes = exception.getErrorCodes();
		if (errorCodes == null) {
			errorCodes = Collections.emptyList();
		}
		if (errorCodes.isEmpty() && (exception instanceof BusinessException)) {
			errorCodes = ((BusinessException) exception).getChainedErrorCodes();
		}
		return (errorCodes.isEmpty() ? new ArrayList<>(Arrays.asList(GENERIC_ERROR)) : errorCodes);
	}

	void setResponse(Object response, boolean result, List<Integer> errorCodes, boolean rollback) {
		if (rollback) {
			setRollback();
		}
		setResult(response, result);
		setStatusCodes(response, errorCodes);
	}

	void setRollback() {
	}

	/*
	 * private void setResponse(Object response, boolean result, List<Integer>
	 * errorCodes) { setResult(response, result); setStatusCodes(response,
	 * errorCodes); }
	 */

	private void setStatusCodes(Object response, List<Integer> errorCodes) {
		if (errorCodes.isEmpty()) {
			return;
		}
		try {
			Field wrapperField = getParentField(response, FieldName.STATUS_CODES);
			Class<?> wrapperType = wrapperField.getType();
			Object wrapper = wrapperType.newInstance();
			wrapperField.set(response, wrapper);
			Field listField = getField(wrapperType, FieldName.STATUS_CODE);
			Class<?> statusCodeType = getListElementType(listField);
			List<?> statusCodes = getStatusCodes(statusCodeType, errorCodes);
			listField.set(wrapper, statusCodes);
		} catch (ReflectiveOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<?> getStatusCodes(Class<?> statusCodeType, List<Integer> errorCodes)
			throws ReflectiveOperationException {
		Field codeField = getField(statusCodeType, FieldName.CODE);
		Field descField = getField(statusCodeType, FieldName.DESCRIPTION);
		List<Object> statusCodes = new ArrayList<>(errorCodes.size());
		for (Integer errorCode : errorCodes) {
			Object statusCode = statusCodeType.newInstance();
			codeField.set(statusCode, errorCode);
			String errorDescription = resource.getString(errorCode.toString());
			descField.set(statusCode, (StringUtils.isNotBlank(errorDescription)) ? errorDescription : errorCode);
			statusCodes.add(statusCode);
		}
		return statusCodes;
	}

	private Class<?> getListElementType(Field listField) {
		ParameterizedType parameterizedType = (ParameterizedType) listField.getGenericType();
		return (Class<?>) parameterizedType.getActualTypeArguments()[0];
	}

	private List<Integer> getErrorCodes(Iterable<BusinessErrorCode> businessErrorCodes) {
		List<Integer> errorCodes = new ArrayList<>();
		for (BusinessErrorCode businessErrorCode : businessErrorCodes) {
			errorCodes.add(businessErrorCode.getBusinessErrorCode());
		}
		return errorCodes;
	}

	void setResult(Object response, boolean result) {
		try {
			Field resultField = getParentField(response, FieldName.RESULT);
			resultField.set(response, Boolean.valueOf(result));
		} catch (ReflectiveOperationException e) {
			String msg = "Failed to set result";
			throw new IllegalArgumentException(msg, e);
		}
	}

	Field getParentField(Object object, FieldName fieldName) throws ReflectiveOperationException {
		return getParentField(object.getClass(), fieldName);
	}

	Field getField(Object object, FieldName fieldName) throws ReflectiveOperationException {
		return getField(object.getClass(), fieldName);
	}

	Field getField(Class<?> clazz, FieldName fieldName) throws ReflectiveOperationException {
		Field field = clazz.getDeclaredField(fieldName.getName());
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		return field;
	}

	Field getParentField(Class<?> clazz, FieldName fieldName) throws ReflectiveOperationException {
		Field field = clazz.getSuperclass().getDeclaredField(fieldName.getName());
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		return field;
	}
}
