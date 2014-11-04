package mahbubul.syeed.mycompoundbroadcastreceivertest;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	final static String INTENT_FILTER = "mahbubul.syeed.compound.broadcast";
	
	//create the dynamic broadcast receiver
	IntentFilter mFilter = new IntentFilter(INTENT_FILTER);
	myDynamicReceiver mReceiver = new myDynamicReceiver();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//registering the dynamic receiver.
		registerReceiver(mReceiver, mFilter);
		
		Button mBroadcast = (Button) findViewById(R.id.button);

		mBroadcast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// broadcasting the intent
				// will be received by all the receivers who have
				// the given intent filter implemented and 
				// the vibration permission.
				
				sendBroadcast(new Intent(INTENT_FILTER),
						android.Manifest.permission.VIBRATE);
			}
		});
	}

}
