package com.hd.hse.common.module.phone.photo.preview;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;

public final class CacheUtil {
	
	public static File getDiskCacheDir(Context context, String uniqueName) {
	    final String cachePath =
	            Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
	                    !Environment.isExternalStorageRemovable() ? context.getExternalCacheDir().getPath() :
	                            context.getCacheDir().getPath();
		return new File(cachePath + File.separator + uniqueName);
	}
	
	public static int getAppVersion(Context context){
		try{
			PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		}catch(NameNotFoundException e){
			e.printStackTrace();
		}
		return 1;
	}
	
	public static String hashKeyForDisk(String key){
		String cacheKey = null;
		try{
			final MessageDigest msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(key.getBytes());
			cacheKey = bytesToHexString(msgDigest.digest());
		}catch(NoSuchAlgorithmException e){
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}
	
	private static String bytesToHexString(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if(hex.length() == 1){
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}
