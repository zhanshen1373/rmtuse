/**
 * Project Name:woa-obr-app
 * File Name:AlbumPreviewActivity.java
 * Package Name:com.hd.woa.obr.app.activities
 * Date:2015年4月24日
 * Copyright (c) 2015, fulibo@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.phone.photo.preview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hd.hse.common.module.phone.R;
import com.hd.hse.entity.common.Image;

import java.util.ArrayList;

/**
 * ClassName:AlbumPreviewActivity ().<br/>
 * Date:     2015年4月24日  <br/>
 * @author   flb
 * @version 
 * @see     
 */
public class AlbumPreviewActivity extends Activity {
	
	public static final String IMAGE_FLAG = "image_flag";
	private static final String ISLOCKED_ARG = "isLocked";
	private HackyViewPager mViewPager;
	private ArrayList<Image> datas;
	private SparseArray<FrameLayout> itemContainer = new SparseArray<FrameLayout>();
	private int screenWidthPixels = 0;
	private int screenHeightPixels = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hd_woa_obr_app_photo_wall_page_fragment);
		
		Intent intent = getIntent();
		datas = (ArrayList<Image>) intent.getSerializableExtra(IMAGE_FLAG);
		screenWidthPixels = ScreenUtil.getScreenWidthPixels(this);
		screenHeightPixels = ScreenUtil.getScreenHeightPixels(this);
		
		mViewPager = (HackyViewPager)findViewById(R.id.photot_preview_vp);
		mViewPager.setAdapter(new ViewPagerAdapter());
		
		if(intent != null){
			mViewPager.setCurrentItem(intent.getIntExtra(Constants.IMAGE_CURRENT_ITEM, 0));
		}
		mViewPager.setPageTransformer(true, new DepthPageTransformer());
	}
	
	private class ViewPagerAdapter extends PagerAdapter{

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			FrameLayout view = (FrameLayout) LayoutInflater.from(AlbumPreviewActivity.this).inflate(R.layout.hd_woa_obr_app_photo_wall_item_img_page, container, false);
			Image image = datas.get(position);
			String url = image.getImagepath();
			RelativeLayout loadingView = (RelativeLayout) view.findViewById(R.id.rlyt_pgb_container);
			ZoomImageView photoView = (ZoomImageView) view.findViewById(R.id.iv_img);
			photoView.setTag(url);
			loadingView.setTag(CacheUtil.hashKeyForDisk(url));
			ImageLoader imageLoader = ImageLoader.getInstance();
			Bitmap bitmap = imageLoader.getBitmapFromMemoryCache(url);
			if(bitmap != null){
				photoView.setImageBitmap(bitmap);
				photoView.setVisibility(View.VISIBLE);
				loadingView.setVisibility(View.GONE);
			}else{
				imageLoader.getImage(url, photoView, loadingView);
			}
			container.addView(view, screenWidthPixels, screenHeightPixels);
			itemContainer.put(position, view);
			
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(itemContainer.get(position));
		}
		
		@Override
		public int getCount() {
			return datas.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}
	
    private boolean isViewPagerActive() {
    	return (mViewPager != null && mViewPager instanceof HackyViewPager);
    }
    
	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (isViewPagerActive()) {
			outState.putBoolean(ISLOCKED_ARG, ((HackyViewPager) mViewPager).isLocked());
    	}
		super.onSaveInstanceState(outState);
	}
}

