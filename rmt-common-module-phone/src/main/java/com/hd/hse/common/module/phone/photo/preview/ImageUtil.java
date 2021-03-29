package com.hd.hse.common.module.phone.photo.preview;

import java.io.FileDescriptor;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class ImageUtil {

	public static Bitmap compressImageByPixel(byte[] bytes, int reqWidth, int reqHeight) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length, newOpts);
		int width = newOpts.outWidth;
		int height = newOpts.outHeight;
		float defaultWidth = 1000.0f;
		float defaultHeight = 1000.0f;

		if (reqWidth > 0) {
			defaultWidth = reqWidth;
		}

		if (reqHeight > 0) {
			defaultHeight = reqHeight;
		}

		int sampleSize = 1;
		if (width <= defaultWidth && height <= defaultHeight) {
			// TODO 可选
//			int ratioWidth = (int) (width / defaultWidth);
//			int ratioHeight = (int) (height / defaultHeight);
//
//			if (ratioWidth > ratioHeight) {
//				sampleSize = ratioHeight;
//			} else {
//				sampleSize = ratioWidth;
//			}
		} else if (width > defaultWidth && height < defaultHeight) {
			sampleSize = (int) (width / defaultWidth);
		} else if (width <= defaultWidth && height > defaultHeight) {
			sampleSize = (int) (height / defaultHeight);
		} else if (width > defaultWidth && height >= defaultHeight) {
			int ratioWidth = (int) (width / defaultWidth);
			int ratioHeight = (int) (height / defaultHeight);
			if (ratioWidth > ratioHeight) {
				sampleSize = ratioWidth;
			} else {
				sampleSize = ratioHeight;
			}
		}

		newOpts.inJustDecodeBounds = false;
		newOpts.inSampleSize = sampleSize;
		newOpts.inPreferredConfig = Config.RGB_565;
		newOpts.inPurgeable = true;
		newOpts.inInputShareable = true;
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, newOpts);
	}

	public static Bitmap compressImageByPixel(FileDescriptor fd, int reqWidth, int reqHeight) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;
		Rect outPadding = new Rect(0, 0, reqWidth, reqHeight);
		BitmapFactory.decodeFileDescriptor(fd, outPadding, newOpts);
		int width = newOpts.outWidth;
		int height = newOpts.outHeight;
		float defaultWidth = 1000.0f;
		float defaultHeight = 1000.0f;

		if (reqWidth > 0) {
			defaultWidth = reqWidth;
		}

		if (reqHeight > 0) {
			defaultHeight = reqHeight;
		}

		int sampleSize = 1;
		if (width <= defaultWidth && height <= defaultHeight) {
			// TODO 可选
//			int ratioWidth = (int) (width / defaultWidth);
//			int ratioHeight = (int) (height / defaultHeight);
//
//			if (ratioWidth > ratioHeight) {
//				sampleSize = ratioHeight;
//			} else {
//				sampleSize = ratioWidth;
//			}
		} else if (width > defaultWidth && height < defaultHeight) {
			sampleSize = (int) (width / defaultWidth);
		} else if (width <= defaultWidth && height > defaultHeight) {
			sampleSize = (int) (height / defaultHeight);
		} else if (width > defaultWidth && height >= defaultHeight) {
			int ratioWidth = (int) (width / defaultWidth);
			int ratioHeight = (int) (height / defaultHeight);
			if (ratioWidth > ratioHeight) {
				sampleSize = ratioWidth;
			} else {
				sampleSize = ratioHeight;
			}
		}

		newOpts.inJustDecodeBounds = false;
		newOpts.inSampleSize = sampleSize;
		newOpts.inPreferredConfig = Config.RGB_565;
		newOpts.inPurgeable = true;
		newOpts.inInputShareable = true;
		return BitmapFactory.decodeFileDescriptor(fd, outPadding, newOpts);
	}
}
