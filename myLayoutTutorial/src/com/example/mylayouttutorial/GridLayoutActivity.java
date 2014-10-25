package com.example.mylayouttutorial;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class GridLayoutActivity extends ActionBarActivity {

	static final String EXTRA_RES_ID = "POS";
	
	// Create a list of image ids from the list of images in drawable.
	private ArrayList<Integer> mThumbIdsFlowers = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image1, R.drawable.image2,
					R.drawable.image3, R.drawable.image4, R.drawable.image5,
					R.drawable.image6, R.drawable.image7, R.drawable.image8,
					R.drawable.image9, R.drawable.image10, R.drawable.image11,
					R.drawable.image12));

	GridView mGridView;
	ImageAdapterForGridView mImageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_layout);

		mGridView = (GridView) findViewById(R.id.gridview);

		mImageAdapter = new ImageAdapterForGridView(GridLayoutActivity.this,
				mThumbIdsFlowers);
		mGridView.setAdapter(mImageAdapter);

		this.setSelectionListener();

	}

	private void setSelectionListener() {
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(GridLayoutActivity.this,
						"Image position: " + position, Toast.LENGTH_SHORT)
						.show();
				
				Intent showImageIntent = new Intent(GridLayoutActivity.this, ShowImageActivity.class);
				
				//id = is the image id.
				showImageIntent.putExtra(EXTRA_RES_ID, (int)id);
				
				startActivity(showImageIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid_layout, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
