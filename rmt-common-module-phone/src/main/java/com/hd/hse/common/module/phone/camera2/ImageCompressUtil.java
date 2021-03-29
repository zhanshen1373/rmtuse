package com.hd.hse.common.module.phone.camera2;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

public class ImageCompressUtil {

	/**
	 * 降质压缩
	 */
	public static byte[] compressImageByQuality(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int quality = 100;
		bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断，如果压缩后图片仍然大于100kb，继续压缩
			baos.reset();
			quality -= 10;
			if (quality < 0) {
				quality = 0;
			}
			
			bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
			if (quality == 0) {
				break;
			}
		}
		return baos.toByteArray();
	}

	/**
	 * 比例压缩
	 */
	@SuppressWarnings("deprecation")
	public static Bitmap compressImageByPixel(Context context, byte[] bitmapBytes) {
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

		int reqWidth, reqHeight;
		Point point = new Point();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			display.getSize(point);
			reqWidth = point.x;
			reqHeight = point.y;
		} else {
			reqWidth = display.getWidth();
			reqHeight = display.getHeight();
		}

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		options.inMutable = true;
		options.inBitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0,
				bitmapBytes.length, options);

		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		options.inJustDecodeBounds = false;
		options.inPurgeable = true;
		options.inInputShareable = true;
		return BitmapFactory.decodeByteArray(bitmapBytes, 0,
				bitmapBytes.length, options);
	}

	private static int calculateInSampleSize(BitmapFactory.Options opts,
			int reqWidth, int reqHeight) {
		int initialInSampleSize = computeInitialSampleSize(opts, reqWidth,
				reqHeight);

		int roundedInSampleSize;
		if (initialInSampleSize <= 8) {
			roundedInSampleSize = 1;
			while (roundedInSampleSize < initialInSampleSize) {
				roundedInSampleSize <<= 1;
			}
		} else {
			roundedInSampleSize = (initialInSampleSize + 7) / 8 * 8;
		}

		return roundedInSampleSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final double height = options.outHeight;
		final double width = options.outWidth;

		final long maxNumOfPixels = reqWidth * reqHeight;
		final int minSideLength = Math.min(reqHeight, reqWidth);

		int lowerBound = (maxNumOfPixels < 0) ? 1 : (int) Math.ceil(Math
				.sqrt(width * height / maxNumOfPixels));
		int upperBound = (minSideLength < 0) ? 128 : (int) Math.min(
				Math.floor(width / minSideLength),
				Math.floor(height / minSideLength));

		if (upperBound < lowerBound) {
			return lowerBound;
		}

		if (maxNumOfPixels < 0 && minSideLength < 0) {
			return 1;
		} else if (minSideLength < 0) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}
}
