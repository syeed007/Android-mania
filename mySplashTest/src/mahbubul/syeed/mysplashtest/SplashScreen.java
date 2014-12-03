package mahbubul.syeed.mysplashtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Toast;

public class SplashScreen extends Activity {

	private static Handler mHandler = new Handler();
	private static Runnable mRunnable = null;
	private static Intent newIntent = null;
	private static int mDelay = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen_layout);

		//activity to be loaded after splash
		newIntent = new Intent(SplashScreen.this, MainActivity.class);

		//create a thread to start  main activity
		mRunnable = new Runnable() {
			public void run() {
				startActivity(newIntent);
				finish();
			}
		};
		
		//execute the thread after the dealy..
		mHandler.postDelayed(mRunnable, mDelay * 1000);

	}

	
	
	/**
	 * Processes splash screen touch events
	 */
	@Override
	public boolean onTouchEvent(MotionEvent evt) {
		
		//if splash screen is touched .. then cancel the splash and load the main activity.
		if (evt.getAction() == MotionEvent.ACTION_DOWN) {
			
			mHandler.removeCallbacks(mRunnable);
			startActivity(newIntent);
			finish();
		}
		return true;
	}
}
