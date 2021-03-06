/**
 * Project Name:hse-common-component
 * File Name:ThumbBitmapUtils.java
 * Package Name:com.hd.hse.common.component.utitl
 * Date:2014年10月20日
 * Copyright (c) 2014, fulibo@ushayden.com All Rights Reserved.
 *
*/

package com.hd.hse.common.component.phone.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

/**
 * ClassName:ThumbBitmapUtils ().<br/>
 * Date:     2014年10月20日  <br/>
 * @author   flb
 * @version  
 * @see 	 
 */
public class ThumbBitmapUtils {
	
	public static Bitmap extractMiniThumb(Bitmap source, int width, int height){
		return extractMiniThumb(source, width, height, true);
	}

	private static Bitmap extractMiniThumb(Bitmap source, int width, int height, boolean recycle) {
		if(source == null){
			return null;
		}
		
		float scale;
		
		if(source.getWidth() < source.getHeight()){
			scale = width / (float) source.getWidth();
		}else {
			scale = height / (float) source.getHeight();
		}
		
		Matrix matrix = new Matrix();
		matrix.setScale(scale, scale);
		
		Bitmap miniThumbnail = transform(matrix, source, width, height, false);
		
		if(recycle && miniThumbnail != source){
			source.recycle();
		}
		return miniThumbnail;
	}

	private static Bitmap transform(Matrix scaler, Bitmap source, int targetWidth, int targetHeight, boolean scaleUp) {
		int deltaX = source.getWidth() - targetWidth;
		int deltaY = source.getHeight() - targetHeight;
		
		if(! scaleUp && (deltaX < 0 || deltaY < 0)){
			Bitmap b2 = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
			
			Canvas canvas = new Canvas(b2);
			
			int deltaXHalf = Math.max(0, deltaX/2);
			int deltaYHalf = Math.max(0, deltaY/2);
			
			Rect src = new Rect(deltaXHalf, deltaYHalf, deltaXHalf + Math.min(targetWidth, source.getWidth()), deltaYHalf + Math.min(targetHeight, source.getHeight()));
			int dstX = (targetWidth - src.width())/2;
			int dstY = (targetHeight - src.height())/2;
			
			Rect dst = new Rect(dstX, dstY, targetWidth-dstX, targetHeight-dstY);
			canvas.drawBitmap(source, src, dst, null);
			return b2;
		}
		
		
		float bitmapWidthF = source.getWidth();
		float bitmapHeightF = source.getHeight();
		
		float bitmapAspect = bitmapWidthF / bitmapHeightF;
		float viewAspect = (float) targetWidth / targetHeight;
		
		if(bitmapAspect > viewAspect){
			float scale = targetHeight / bitmapHeightF;
			if(scale < .9F || scale > 1F){
				scaler.setScale(scale, scale);
			} else {
				scaler = null;
			}
		} else {
			float scale = targetWidth / bitmapWidthF;
			if(scale < .9F || scale > 1F){
				scaler.setScale(scale, scale);
			}else {
				scaler = null;
			}
		}
		
		Bitmap b1;
		if(scaler != null){
			b1 = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), scaler, true);
		}else{
			b1 = source;
		}
		
		int dx1 = Math.max(0, b1.getWidth() - targetWidth);
		int dy1 = Math.max(0, b1.getHeight() - targetHeight);
		
		Bitmap b2 = Bitmap.createBitmap(b1, dx1/2, dy1/2, targetWidth, targetHeight);
		
		if(b1 != source){
			b1.recycle();
		}
		return b2;
	}
}

