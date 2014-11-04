package mahbubul.syeed.mystickeybroadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	TextView mText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mText= (TextView) findViewById(R.id.text);
		// instantiate the stickey receiver
		StickeyReceiver mStickyReceiver = new StickeyReceiver();

		// create an intent filter to receive the battery charge change state..
		IntentFilter mIntFilter = new IntentFilter(
			Intent.ACTION_BATTERY_CHANGED);

		// register the receiver with the intended intent filter
		registerReceiver(mStickyReceiver, mIntFilter);
	}

	/**
	 * receiver implementation
	 * 
	 * @author syeed
	 *
	 */
	public class StickeyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			// check if it is a battery state change intent
			if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
				// create a message to display
				String readingTime = "Recently Read State";

				if (isInitialStickyBroadcast()) {
					readingTime = "Old Reading.. Nothing changed since then..";
				}

				mText.setText("Current Battery Level:"
						+ String.valueOf(intent.getIntExtra(
								BatteryManager.EXTRA_LEVEL, -1))
						+ System.getProperty("line.separator") + readingTime);
			}
		}

	}
}
