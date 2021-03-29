package com.hd.hse.common.module.phone.photo.preview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.adapter.SuperListBasicAdapter;
import com.hd.hse.common.module.phone.adapter.SuperViewHolder;
import com.hd.hse.entity.common.Image;

import java.util.List;

public class PhotoWallAdapter extends SuperListBasicAdapter<Image> implements OnScrollListener{

	private Activity activity;
	private AbsListView listView;
	
	private int firstVisibleItem   = 0;
	private int visibleItemCount   = 0;
	private boolean isFirstEnter   = true;
	private static final int cloumnNum = 3;
	
	public PhotoWallAdapter(Activity activity, List<Image> data, AbsListView listView) {
		super(activity, data);
		
		this.activity = activity;
		this.listView = listView;
		this.listView.setOnScrollListener(this);
		
		Constants.cloumnWidth = ScreenUtil.getScreenWidthPixels(activity) / cloumnNum;
	}

	@Override
	public int getItemResource() {
		return R.layout.hd_woa_obr_app_photo_wall_item_img_list;
	}

	@Override
	public void handleViews(int position, Image image, SuperViewHolder holder) {
		String url = image.getImagepath();
		String title = image.getImageName();
		
		FrameLayout container = holder.getView(R.id.flyt_container);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Constants.cloumnWidth, Constants.cloumnHeight);
		container.setLayoutParams(layoutParams);
		
		ImageView imageView = holder.getView(R.id.iv_img);
		RelativeLayout loadingView = holder.getView(R.id.rlyt_pgb_container);
		loadingView.setVisibility(View.VISIBLE);
		imageView.setVisibility(View.INVISIBLE);
		
		imageView.setTag(url);  //防止错位
		loadingView.setTag(CacheUtil.hashKeyForDisk(url));
		//ImageCache.getInstance(activity).setImage(url, imageView, loadingView);
		ImageLoader.getInstance().getImage(url, imageView, loadingView);
		TextView textView = holder.getView(R.id.tv_name);
		textView.setText(title);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		this.firstVisibleItem = firstVisibleItem;
		this.visibleItemCount = visibleItemCount;
		if(isFirstEnter && visibleItemCount > 0){
			loadBitmaps(firstVisibleItem, visibleItemCount);
			isFirstEnter = false;
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(scrollState != SCROLL_STATE_FLING){
			//ImageCache.getInstance(activity).cancelAllTask();
			ImageLoader.getInstance().cancelAllTask();
		}else{
			loadBitmaps(firstVisibleItem, visibleItemCount);
		}
	}
	
	private void loadBitmaps(int firstVisibleItem, int visibleItemCount) {
		for(int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++){
			Image image = sourceData.get(i);
			String url =  image.getImagepath();
			ImageView imageView = (ImageView) listView.findViewWithTag(url);
			RelativeLayout loadingView = (RelativeLayout) listView.findViewWithTag(CacheUtil.hashKeyForDisk(url));
			
			//ImageCache imageCache = ImageCache.getInstance(activity);
			ImageLoader imageLoader=ImageLoader.getInstance();
			//Bitmap bitmap = imageCache.getBitmapFromMemoryCache(url);
			Bitmap bitmap = imageLoader.getBitmapFromMemoryCache(url);
			if(bitmap != null){
				imageView.setImageBitmap(bitmap);
				imageView.setVisibility(View.VISIBLE);
				loadingView.setVisibility(View.GONE);
			}else{
				loadingView.setVisibility(View.VISIBLE);
				imageView.setVisibility(View.INVISIBLE);
				BitmapRequest request = new BitmapRequest(url);
				//imageCache.addTaskExecute(url, request, imageView, loadingView);
				imageLoader.getImage(url, imageView, loadingView);
			}
		}
	}
}
