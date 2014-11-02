package mahbubul.syeed.myhandlertest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RunnableActivity extends ActionBarActivity {

	Button mLoad;
	Button mToast;
	ImageView mImage;
	Bitmap mBitmap;
	ProgressBar mProgress;

	private final Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_runnable);

		mLoad = (Button) findViewById(R.id.loadButton);
		mToast = (Button) findViewById(R.id.otherButton);
		mImage = (ImageView) findViewById(R.id.imageView);
		mProgress = (ProgressBar) findViewById(R.id.progressBar);

		mLoad.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new Thread(new LoadIconTask(R.drawable.painter)).start();
			}
		});

		mToast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(RunnableActivity.this, "I'm Working",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private class LoadIconTask implements Runnable {

		int resID;

		public LoadIconTask(int resID) {
			// TODO Auto-generated constructor stub
			this.resID = resID;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			/**
			 * send runnable to set the progress bar visible
			 */
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					mProgress.setVisibility(ProgressBar.VISIBLE);
				}

			});

			mBitmap = BitmapFactory.decodeResource(getResources(), resID);
			
			for(int i = 0; i < 11; i++){
				final int step = i;
				try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mProgress.setProgress(step*10);
					}
				});
				
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mImage.setImageBitmap(mBitmap);
					}
				});
				
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mProgress.setVisibility(ProgressBar.INVISIBLE);
					}
				});
				
			}
		}// end of run..

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.runnable, menu);
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
