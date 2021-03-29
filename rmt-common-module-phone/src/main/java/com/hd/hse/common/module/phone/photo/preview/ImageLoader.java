package com.hd.hse.common.module.phone.photo.preview;

import android.graphics.Bitmap;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.Base64Util;
import com.hd.hse.entity.common.RmtImageDetail;

import java.util.Iterator;
import java.util.Map;

import retrofit2.Call;

/**
 * created by yangning on 2017/7/7 15:32.
 */

public class ImageLoader {
    private static ImageLoader mInstance = null;
    private LruCache<String, Bitmap> mMemoryCache = null;
    private Map<String, Call<ResultData<RmtImageDetail>>> callMap;

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            mInstance = new ImageLoader();
        }

        return mInstance;
    }

    private ImageLoader() {
        callMap = new ArrayMap<String, Call<ResultData<RmtImageDetail>>>();
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
    }


    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            if (key != null && bitmap != null) {
                mMemoryCache.put(key, bitmap);
            }
        }
    }

    public void cancelAllTask() {
        Iterator it = callMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Call<ResultData<RmtImageDetail>> call = (Call<ResultData<RmtImageDetail>>) entry.getValue();
            call.cancel();
        }
    }

    public void getImage(final String url, final ImageView imageView, final View loadingView) {
        Bitmap bitmap = getBitmapFromMemoryCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.GONE);
        } else {
            getImageFromNet(url, imageView, loadingView);
        }


    }

    private void getImageFromNet(final String url, final ImageView imageView, final View loadingView) {
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<RmtImageDetail>> call = rmtInterface.getRecordPic(Long.parseLong(url));
        HttpBusiness.action(null, false, call, new HttpCallBack<RmtImageDetail>() {
            @Override
            public void success(RmtImageDetail rmtImageDetail) {
                if (callMap.containsKey(url)) {
                    callMap.remove(url);
                }
                if (rmtImageDetail.getImgStr() != null) {
                    Bitmap bitmap = Base64Util.base64ToBitmap(rmtImageDetail.getImgStr());
                    addBitmapToMemoryCache(url, bitmap);
                    if (imageView.getTag().equals(url)) {
                        imageView.setImageBitmap(bitmap);
                        imageView.setVisibility(View.VISIBLE);
                    }
                }

                if (loadingView.getTag().equals(CacheUtil.hashKeyForDisk(url))) {
                    loadingView.setVisibility(View.GONE);
                }
            }

            @Override
            public void warning(String msg) {
                if (callMap.containsKey(url)) {
                    callMap.remove(url);
                }
            }

            @Override
            public void error(Throwable t) {
                if (callMap.containsKey(url)) {
                    callMap.remove(url);
                }
            }
        });
        callMap.put(url, call);
    }

}
