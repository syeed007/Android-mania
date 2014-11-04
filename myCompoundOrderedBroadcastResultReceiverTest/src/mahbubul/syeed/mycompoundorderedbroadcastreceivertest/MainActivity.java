package mahbubul.syeed.mycompoundorderedbroadcastreceivertest;

import mahbubul.syeed.mycompoundbroadcastreceivertest.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * This example test the ordering of the receivers who have registered to
 * receive the same broadcast. The ordering is done by setting the intent filter
 * priority associated with each receiver.
 * 
 * It also receives the messages send by the receivers.. for this it sets it
 * self as a broadcast receiver and receives the messages and toast it
 * 
 * Set priority: Static receiver2: priority 10 Static receiver1: priority 6
 * Dynamic receiver: priority 3
 * 
 * @author syeed
 *
 */
public class MainActivity extends ActionBarActivity {

	final static String INTENT_FILTER = "mahbubul.syeed.compound.broadcast";

	private final myDynamicReceiver mReceiver = new myDynamicReceiver();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// create the dynamic broadcast receiver
		IntentFilter mFilter = new IntentFilter(INTENT_FILTER);
		// set the receiver priority
		mFilter.setPriority(3);

		// registering the dynamic receiver.
		registerReceiver(mReceiver, mFilter);

		Button mBroadcast = (Button) findViewById(R.id.button);

		mBroadcast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// broadcasting the intent
				// will be received by all the receivers who have
				// the given intent filter implemented and
				// the vibration permission.

				// upon receiving the result display the it as toast
				/**
				 * NB: You need to send ordered broadcast to oreder the recever
				 */
				sendOrderedBroadcast(new Intent(INTENT_FILTER), null,
						new BroadcastReceiver() {

							@Override
							public void onReceive(Context context, Intent intent) {
								// TODO Auto-generated method stub
								Toast.makeText(context,
										"Final Result is " + getResultData(),
										Toast.LENGTH_LONG).show();
							}
						}, null, 0, null, null);
			}
		});
	}
}
