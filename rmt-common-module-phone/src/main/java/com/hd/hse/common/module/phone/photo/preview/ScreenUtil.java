package com.hd.hse.common.module.phone.photo.preview;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenUtil {
	
	public static int getScreenWidthPixels(Activity activity){
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	
	public static int getScreenHeightPixels(Activity activity){
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
}
