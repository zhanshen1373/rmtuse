package com.hd.hse.common.module.phone.photo.preview;

import java.net.HttpURLConnection;

public interface ICallback<T> {
	
	public T preRequest();
	public T postRequest(T t);
	
	public T handle(HttpURLConnection conn, IRequestListener listener) throws AppException;
	public T handle(HttpURLConnection conn) throws AppException;
	
	public int retryCount();
	public void cancel(boolean force);
	
	public void onFailure(Exception exception);
	public void onSuccess(T result);
	public boolean isForceCancelled();
}
