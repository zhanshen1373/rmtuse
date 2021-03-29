package com.hd.hse.common.module.phone.photo.preview;

public class AppException extends Exception {

	private static final long serialVersionUID = -5771254788248235296L;
	
	public enum ExceptionStatus{
		CancelException, ReadStreamException, IOException, TimeoutException, ServerException
	}
	
	private ExceptionStatus status;
	
	public AppException(ExceptionStatus status, String detailMessage) {
		super(detailMessage);
		this.status = status;
	}

	public ExceptionStatus getStatus() {
		return status;
	}
}
