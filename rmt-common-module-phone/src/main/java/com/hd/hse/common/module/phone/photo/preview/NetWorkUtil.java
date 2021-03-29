/**
 * Project Name:woa-obr-app
 * File Name:NetWorkUtil.java
 * Package Name:com.hd.woa.obr.app.utils
 * Date:2015年4月24日
 * Copyright (c) 2015, fulibo@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.phone.photo.preview;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ClassName:NetWorkUtil ().<br/>
 * Date: 2015年4月24日 <br/>
 * 
 * @author flb
 * @version
 * @see
 */
public class NetWorkUtil {
	
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
}
