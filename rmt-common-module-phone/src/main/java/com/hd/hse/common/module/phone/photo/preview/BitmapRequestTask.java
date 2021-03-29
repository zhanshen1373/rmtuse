package com.hd.hse.common.module.phone.photo.preview;

import java.net.HttpURLConnection;

import com.hd.hse.common.module.phone.photo.preview.AppException.ExceptionStatus;

import android.os.AsyncTask;

public class BitmapRequestTask extends AsyncTask<String, Integer, Object> {

	private BitmapRequest request;
	public BitmapRequestTask(BitmapRequest request){
		this.request = request;
	}
	
	@Override
	protected Object doInBackground(String... params) {
		int retryCount = 0;
		int retry = 0;
		if(request.callback != null){
			retryCount = request.callback.retryCount();
		}
		return request(retry, retryCount);
	}
	
	@SuppressWarnings("unchecked")
	private Object request(int retry, int retryCount){
		try{
			Object result = null;
			if(request.callback != null){
				result = request.callback.preRequest();
				if(!request.isGetDataFromNet){
					return result;
				}else{
					if(result != null){
						return result;
					}
				}
			}
			
			HttpURLConnection conn = HttpURLConnectionUtil.execute(request);
			if(request.callback != null){
				if(request.listener != null){
					return request.callback.handle(conn, new IRequestListener() {
						
						@Override
						public void onProgressUpdate(int currentPosition, int contentLength) {
							publishProgress(currentPosition, contentLength);
						}
					});
				}else{
					result = request.callback.handle(conn);
				}
				return request.callback.postRequest(result);
			}else{
				return null;
			}
		}catch(AppException e){
			if(e.getStatus() == ExceptionStatus.TimeoutException){
				if(retry < retryCount){
					return request(retry ++, retryCount);
				}
			}
			return e;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if(request.callback.isForceCancelled()){
			return ;
		}
		
		if(request.callback != null){
			if(result != null && result instanceof Exception){
				request.callback.onFailure((Exception)result);
			}else{
				request.callback.onSuccess(result);
			}
		}
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		if(request.listener != null){
			request.listener.onProgressUpdate(values[0], values[1]);
		}
	}
}
