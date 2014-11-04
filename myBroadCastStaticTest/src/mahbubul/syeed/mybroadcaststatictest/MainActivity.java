package mahbubul.syeed.mybroadcaststatictest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	// intent filter tag.
	private static final String CUSTOM_INTENT = "mahbubul.syeed.static.boradcast";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bCast = (Button) findViewById(R.id.button);

		bCast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// send a broadcast with the intent filter and
				// the required permission.
				// any broadcast receiver having this permission and intent
				// filter
				// will be eligible to response to this boradcast.

				sendBroadcast(new Intent(CUSTOM_INTENT),
						android.Manifest.permission.VIBRATE);
				Toast.makeText(MainActivity.this, "BroadCasting..",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
