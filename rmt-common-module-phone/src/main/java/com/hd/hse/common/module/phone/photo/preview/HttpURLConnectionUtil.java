package com.hd.hse.common.module.phone.photo.preview;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.conn.ConnectTimeoutException;

import com.hd.hse.common.module.phone.photo.preview.AppException.ExceptionStatus;

public class HttpURLConnectionUtil {
	
	private static final int CONNECT_TIME = 5 * 1000;
	private static final int READ_TIME = 5 * 1000;
	
	public static HttpURLConnection execute(BitmapRequest request) throws AppException {
		switch (request.method) {
		case GET:
			return get(request);
			
		case POST:
			return post(request);
			
		default:
			throw new IllegalStateException("the request method"+request.method.name()+" can't be supported");
		}
	}

	private static HttpURLConnection get(BitmapRequest request) throws AppException{
		try{
			URL url = new URL(request.url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(CONNECT_TIME);
			conn.setReadTimeout(READ_TIME);
			return conn;
		} catch(ConnectTimeoutException e){
			throw new AppException(ExceptionStatus.TimeoutException, e.getMessage());
		} catch(MalformedURLException e){
			throw new AppException(ExceptionStatus.ServerException, e.getMessage());
		} catch (IOException e) {
			throw new AppException(ExceptionStatus.ServerException, e.getMessage());
		}
	}

	private static HttpURLConnection post(BitmapRequest request) throws AppException{
		try{
			URL url = new URL(request.url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(CONNECT_TIME);
			conn.setReadTimeout(READ_TIME);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			return conn;
		} catch(ConnectTimeoutException e){
			throw new AppException(ExceptionStatus.TimeoutException, e.getMessage());
		} catch(MalformedURLException e){
			throw new AppException(ExceptionStatus.ServerException, e.getMessage());
		} catch (IOException e) {
			throw new AppException(ExceptionStatus.ServerException, e.getMessage());
		}

	}
}
