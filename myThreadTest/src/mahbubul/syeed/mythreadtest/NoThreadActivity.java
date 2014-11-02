package mahbubul.syeed.mythreadtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * this tutorial is to demonstrate how the back end long running process could stall the 
 * main ui thread if that process is not carried out in a separate thread.
 * 
 * @author rajit
 *
 */
public class NoThreadActivity extends ActionBarActivity {

	Bitmap mBitmap;
	Button mLoad;
	Button mToast;
	ImageView mImage;
	int mDelay = 5000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_no_thread);
		
		mLoad = (Button) findViewById(R.id.loadButton);
		mToast = (Button) findViewById(R.id.otherButton);
		mImage = (ImageView) findViewById(R.id.imageView);
		
		mLoad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loadImage();
				
				mImage.setImageBitmap(mBitmap);
			}
		});
		
		
		mToast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(NoThreadActivity.this, "I'm Working",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	
	private void loadImage(){
		try {
			Thread.sleep(mDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.painter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.no_thread, menu);
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
