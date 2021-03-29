package com.hd.hse.common.module.phone.photo.preview;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import com.hd.hse.common.module.phone.photo.preview.AppException.ExceptionStatus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class BitmapRequestCallback implements ICallback<Bitmap> {

	private boolean isCancelled;
	public boolean isForceCancel;
	
	private int reqImageWidth;
	private int reqImageHeight;
	
	public BitmapRequestCallback() {
		super();
	}
	
	public BitmapRequestCallback(int reqImageWidth, int reqImageHeight) {
		this.reqImageWidth = reqImageWidth;
		this.reqImageHeight = reqImageHeight;
	}

	public void checkIfCancelled() throws AppException {
		if (isCancelled) {
			throw new AppException(ExceptionStatus.CancelException,"the request has been cancelled");
		}
	}
	
	@Override
	public Bitmap handle(HttpURLConnection conn) throws AppException{
		return this.handle(conn, null);
	}
	
	@Override
	public Bitmap handle(HttpURLConnection conn, IRequestListener listener) throws AppException{
		try {
			checkIfCancelled();
			int statusCode = conn.getResponseCode();
			switch (statusCode) {
			case HttpURLConnection.HTTP_OK:
				InputStream is = conn.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				writeToOutputStream(is, baos, conn.getContentLength(), listener);
				byte[] buffer = baos.toByteArray();
				if(reqImageWidth != 0 && reqImageHeight != 0){
					return ImageUtil.compressImageByPixel(buffer, reqImageWidth, reqImageHeight);
				}else{
					return BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
				}
			default:
				throw new AppException(ExceptionStatus.IOException,"404");
			}
		} catch (IOException e) {
			throw new AppException(ExceptionStatus.IOException, e.getMessage());
		}
	}

	private void writeToOutputStream(InputStream is, OutputStream os, long contentLength, IRequestListener listener) throws AppException{
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] buffer = new byte[1024];
		int len = -1;
		long currentLength = 0;
		try {
			while ((len = bis.read(buffer)) != -1) {
				checkIfCancelled();
				if (listener != null) {
					currentLength += len;
					listener.onProgressUpdate((int)(currentLength / 1024), (int)(contentLength / 1024));
				}
				os.write(buffer, 0, len);
			}
			is.close();
			os.flush();
			os.close();
		} catch (IOException e) {
			throw new AppException(ExceptionStatus.ReadStreamException, "读取网络写入流出错！");
		}
	}
	
	public void cancel(boolean force) {
		this.isForceCancel = force;
		this.isCancelled = true;
	}
	
	@Override
	public boolean isForceCancelled() {
		return isForceCancel;
	}
	
	@Override
	public int retryCount() {
		return 0;
	}
	
	@Override
	public Bitmap preRequest() {
		return null;
	}
	
	@Override
	public Bitmap postRequest(Bitmap t) {
		return t;
	}
}
