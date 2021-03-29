package com.hd.hse.common.module.phone.photo.preview;

public class BitmapRequest {
	
	public enum RequestMethod{
		GET, POST
	}
	
	public RequestMethod method;
	public String url;
	public boolean isGetDataFromNet = true;
	
	@SuppressWarnings("rawtypes")
	public ICallback callback;         
	public IRequestListener listener;  
	private BitmapRequestTask task;
	
	public BitmapRequest(String url, RequestMethod method){
		this.url = url;
		this.method = method;
	}
	
	public BitmapRequest(String url){
		this.url = url;
		this.method = RequestMethod.GET;
	}

	@SuppressWarnings("rawtypes")
	public void setCallback(ICallback callback){
		this.callback = callback;
	}
	
	public void setRequestListener(IRequestListener listener){
		this.listener = listener;
	}
	
	public void execute(){
		task = new BitmapRequestTask(this);
		task.execute();
	}
	
	public void cancel(boolean force) {
		if(force && task != null){
			task.cancel(true);
		}
		
		if(callback != null){
			callback.cancel(force);
		}
	}
}
