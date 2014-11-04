package mahbubul.syeed.mybroadcastdynamictest;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	//intent filter tag
	private static final String CUSTOM_INTENT = "mahbubul.syeed.dynamic.broadcast";
	//set the intent filter
	IntentFilter mFilter = new IntentFilter(CUSTOM_INTENT);
	
	//get the receiver instance
	MyDynamicBroadcastReceiver mReceiver = new MyDynamicBroadcastReceiver();
	
	LocalBroadcastManager mBroadcastManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//get the local boradcast manager.
		//this manager will register the broadcast receivers that are 
		//only for this application.
		mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
		
		//register the receiver as the local boradcast receiver
		mBroadcastManager.registerReceiver(mReceiver, mFilter);
		
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBroadcastManager.sendBroadcast(new Intent(CUSTOM_INTENT));
			}
		});
	}

	
}
