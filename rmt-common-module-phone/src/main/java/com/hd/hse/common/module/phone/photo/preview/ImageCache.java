package com.hd.hse.common.module.phone.photo.preview;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.photo.preview.DiskLruCache.Editor;
import com.hd.hse.common.module.phone.photo.preview.DiskLruCache.Snapshot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.ImageView;

/**
 * @author ZINK
 * ImageView-->url<--Bitmap
 */
public class ImageCache{
	
	private static String DISK_CACHE_SUBDIR = "thumbnails";
	private static int DISK_CACHE_SIZE   = 50 * 1024 * 1024;
	
	private static ImageCache mInstance          = null;
	private LruCache<String, Bitmap> mMemoryCache = null;
	private DiskLruCache mDiskLruCache            = null;
	
	private static int errorDefaultImage   = R.drawable.ic_launcher;
	
	private final Object mDiskCacheLock = new Object();
	private boolean mDiskCacheStarting = true;
	
	private HashMap<String, BitmapRequest> mRequestCache = null;
	
	private int defaultImageWidth = 0;
	private int defaultImageHeight = 0;
	
	public static ImageCache getInstance(Context context){
		if(mInstance == null){
			mInstance = new ImageCache(context);
		}
		
		return mInstance;
	}
	
	private ImageCache(Context context){
		mRequestCache = new HashMap<String, BitmapRequest>();
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 8;
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount();
			}
		};
		new InitDiskLruCache().execute(context);
	}
	
	public void setImage(String url, ImageView imageView, View loadingView) {
		this.setImage(url, -1, imageView, loadingView);
	}
	
	public void setImage(final String url, int errorImg, final ImageView imageView, View loadingView) {
		if(errorImg != -1){
			errorDefaultImage = errorImg;
		}
		
		Bitmap bitmap = getBitmapFromMemoryCache(url);
		if(bitmap != null){
			imageView.setImageBitmap(bitmap);
			imageView.setVisibility(View.VISIBLE);
			loadingView.setVisibility(View.GONE);
		}else{
			BitmapRequest request = new BitmapRequest(url);
			request.isGetDataFromNet = false;
			addTaskExecute(url, request, imageView, loadingView);
		}
	}
	
	public void addBitmapToMemoryCache(String key, Bitmap bitmap){
		if(getBitmapFromMemoryCache(key) == null){
			if(key != null && bitmap != null){
				mMemoryCache.put(key, bitmap);
			}
		}
	}
	
	public Bitmap getBitmapFromMemoryCache(String key){
		return mMemoryCache.get(key);
	}
	
	public void addBitmapToDiskCache(String key, Bitmap bitmap){
		addBitmapToMemoryCache(key, bitmap);
		if(key != null && bitmap != null){
			synchronized (mDiskCacheLock) {
				if(mDiskLruCache != null && getBitmapFromDiskCache(key) == null){
					final String keycode = CacheUtil.hashKeyForDisk(key);
					Editor edit;
					try {
						edit = mDiskLruCache.edit(keycode);
						if(bitmap.compress(CompressFormat.JPEG, 100, edit.newOutputStream(0))){
							edit.commit();
						}else{
							edit.abort();
						}
						mDiskLruCache.flush();
					} catch (IOException e) {
						
					}
				}
			}	
		}
	}
	
	public Bitmap getBitmapFromDiskCache(String key){
		synchronized (mDiskCacheLock) {
			while(mDiskCacheStarting){
				try{
					mDiskCacheLock.wait();
				}catch(InterruptedException e){
					
				}
			}
			
			if(mDiskLruCache != null){
				try {
					final String keycode = CacheUtil.hashKeyForDisk(key);
					Snapshot snapshot = mDiskLruCache.get(keycode);
					Bitmap bitmap = null;
					if(snapshot != null){
						FileInputStream is = (FileInputStream) snapshot.getInputStream(0);
						FileDescriptor fileDescriptor = is.getFD();
						if(Constants.cloumnWidth != 0 && Constants.cloumnHeight != 0){
							bitmap = ImageUtil.compressImageByPixel(fileDescriptor, Constants.cloumnWidth, Constants.cloumnHeight);
						}else{
							bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
						}
					}
					return bitmap;
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
	
	class InitDiskLruCache extends AsyncTask<Context, Void, Void>{

		@Override
		protected Void doInBackground(Context... params) {
			synchronized (mDiskCacheLock) {
				Context context = params[0];
				File cacheDir = CacheUtil.getDiskCacheDir(context, DISK_CACHE_SUBDIR);
				if(!cacheDir.exists()){
					cacheDir.mkdirs();
				}
				try {
					mDiskLruCache = DiskLruCache.open(cacheDir, CacheUtil.getAppVersion(context), 1, DISK_CACHE_SIZE);
					mDiskCacheStarting = false;
					mDiskCacheLock.notifyAll();
				} catch (IOException e) {
					
				}
			}
			return null;
		}
	}
	
	public void addTaskExecute(final String url, BitmapRequest request, final ImageView imageView, final View loadingView){
		mRequestCache.put(url, request);
		request.setCallback(new BitmapRequestCallback(defaultImageWidth, defaultImageHeight) {
			@Override
			public Bitmap preRequest() {
				Bitmap bitmap = getBitmapFromDiskCache(url);
				if(bitmap != null){
					addBitmapToMemoryCache(url, bitmap);
					return bitmap;
				}
				return super.preRequest();
			}
			
			@Override
			public Bitmap postRequest(Bitmap t) {
				addBitmapToDiskCache(url, t);
				Bitmap bitmap = getBitmapFromDiskCache(url);
				addBitmapToMemoryCache(url, bitmap);
				return bitmap;
			}
			
			@Override
			public void onSuccess(Bitmap t) {
				String key = (String) imageView.getTag();
				if(t!= null && key != null && key.equals(url)){
					imageView.setImageBitmap(t);
					imageView.setVisibility(View.VISIBLE);
					loadingView.setVisibility(View.GONE);
				}
				mRequestCache.remove(url);
			}
			
			@Override
			public void onFailure(Exception exception) {
				imageView.setImageResource(errorDefaultImage);
				imageView.setVisibility(View.VISIBLE);
				loadingView.setVisibility(View.GONE);
				mRequestCache.remove(url);
			}
		});
		
		for(Entry<String, BitmapRequest> entry : mRequestCache.entrySet()){
			BitmapRequest req = entry.getValue();
			if(req != null){
				req.execute();
			}
		}
	}
	
	public void cancelTask(String key) {
		if(mRequestCache != null && mRequestCache.size() > 0){
			BitmapRequest request = mRequestCache.get(key);
			request.cancel(true);
			mRequestCache.remove(key);
		}
	}
	
	public void cancelAllTask() {
		if(mRequestCache != null && mRequestCache.size() > 0){
			for (Entry<String, BitmapRequest> entrySet : mRequestCache.entrySet()) {
				BitmapRequest request = entrySet.getValue();
				request.cancel(true);
			}
			mRequestCache.clear();
		}
	}

	public void close() {
		if(mMemoryCache != null){
			mMemoryCache.evictAll();
		}
		
		if(mDiskLruCache != null && !mDiskLruCache.isClosed()){
			try {
				mDiskLruCache.close();
				mDiskLruCache = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		mInstance = null;
	}

	public ImageCache setMaxImageSize(int reqImageWidth, int reqImageHeight) {
		this.defaultImageWidth = reqImageWidth;
		this.defaultImageHeight = reqImageHeight;
		return this;
	}

	public ImageCache setDiskCacheSize(Context context, int diskSize) {
		if(mDiskLruCache != null){
			try {
				mDiskLruCache.delete();
				mDiskLruCache = null;
				DISK_CACHE_SIZE = diskSize;
				new InitDiskLruCache().execute(context);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this;
	}
}
