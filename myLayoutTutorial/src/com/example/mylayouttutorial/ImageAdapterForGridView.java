package com.example.mylayouttutorial;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapterForGridView extends BaseAdapter {

	private static final int PADDING = 8;
	private static final int WIDTH = 250;
	private static final int HEIGHT = 250;
	
	private Context mContext;
	private List <Integer> mImageList;
	
	ImageAdapterForGridView(Context context, List<Integer> imageIds){
		mContext = context;
		mImageList = imageIds;
	}
	
	// return the size of the image array list
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImageList.size();
	}

	//return item from a given position
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mImageList.get(position);
	}

	//retrun id of the given position
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mImageList.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		//get the image view if it is already created.
		ImageView mImageView = (ImageView) convertView;
		
		//if the image view is not already created in the previous line
		//then create a new image view with appropriate arrtibute setting.
		if(mImageView == null){
			mImageView = new ImageView(mContext);
			mImageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT));
			mImageView.setPadding(PADDING, PADDING, PADDING, PADDING);
			mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		}
		
		//set the current image to the current image view position on the grid view
		mImageView.setImageResource(mImageList.get(position));
		
		//return the image view.
		return mImageView;
	}

}
