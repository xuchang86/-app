package com.group.core.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -3520789356146146317L;
	
	private String errorCode;
	private String errorDesc;
	
	public ServiceException(){
		super();
	}
	
	public ServiceException(String errorCode, String errorDesc) {
		super();
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
}
