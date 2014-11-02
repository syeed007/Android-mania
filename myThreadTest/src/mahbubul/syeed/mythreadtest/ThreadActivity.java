package mahbubul.syeed.mythreadtest;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ThreadActivity extends ActionBarActivity {

	Bitmap mBitmap;
	Button mLoad;
	Button mToast;
	ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread);

		mLoad = (Button) findViewById(R.id.loadButton);
		mToast = (Button) findViewById(R.id.otherButton);
		mImageView = (ImageView) findViewById(R.id.imageView);

		mLoad.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadImage();

			}
		});

		mToast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ThreadActivity.this, "I'm Working",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	
	private void loadImage(){
		
		/*
		 * Start a new thread...start its runnable whcih in turn starts the run method to execute the thread.
		 */
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				try{
					//Just for demonstration purpose: a delay is set before loading the actual image :)
					Thread.sleep(5000);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				// actually loading the image :)
				mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.painter);
				
				/**
				 * Very important part:
				 * The loaded resource, in this case the bitmap must be loaded by the main ui thread.
				 * Otherwise the application will crash, as android restricts any thread except the main Ui thread
				 * to modifiy the ui.
				 */
				mImageView.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mImageView.setImageBitmap(mBitmap);
					}
				});
				
			}
		}).start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thread, menu);
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
