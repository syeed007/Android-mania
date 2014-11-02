package com.example.myasynctasktest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Button mAsync;
	Button mToast;
	ImageView mImageView;
	ProgressBar mProgress;
	Bitmap mBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAsync = (Button) findViewById(R.id.loadButton);
		mToast = (Button) findViewById(R.id.otherButton);
		mImageView = (ImageView) findViewById(R.id.imageView);
		mProgress = (ProgressBar) findViewById(R.id.progressBar);

		mToast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "I m working",
						Toast.LENGTH_SHORT).show();
			}
		});

		mAsync.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new loadImageAsync().execute(R.drawable.painter);
			}
		});
	}

	private class loadImageAsync extends AsyncTask<Integer, Integer, Bitmap> {

		// executed before the back ground thread starts..
		@Override
		protected void onPreExecute() {
			mProgress.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected Bitmap doInBackground(Integer... params) {

			// dummy code for showing the progress of image loading .. haha
			for (int i = 0; i < 11; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				publishProgress(i * 10);
			}

			Bitmap mBit = BitmapFactory.decodeResource(getResources(),
					params[0]);
			
			
			return mBit;
		}

		// show the intermediate progress of the backend execution
		// in this case dummy progress sent by background thread.
		@Override
		protected void onProgressUpdate(Integer... values) {
			mProgress.setProgress(values[0]);
		}

		//show or process the result after background thread completes..
		
		@Override
		protected void onPostExecute(Bitmap result) {
			mProgress.setVisibility(ProgressBar.INVISIBLE);
			mImageView.setImageBitmap(result);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
