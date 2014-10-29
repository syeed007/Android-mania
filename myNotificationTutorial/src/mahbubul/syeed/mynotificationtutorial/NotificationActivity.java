package mahbubul.syeed.mynotificationtutorial;



import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class NotificationActivity extends Activity {

	// Notification ID to allow for future updates
	private static final int MY_NOTIFICATION_ID = 1;

	// Notification Count
	private int mNotificationCount;

	// Notification Text Elements
	private final CharSequence tickerText = "U r notified BABY!";
	private final CharSequence contentTitle = "Huom!";
	private final CharSequence contentText = "Click to survive!";

	// Notification Action Elements
	private Intent mNotificationIntent;
	private PendingIntent mContentIntent;

	// Notification Sound and Vibration on Arrival
	private Uri soundURI = Uri
			.parse("android.resource://course.examples.Notification.StatusBar/"
					+ R.raw.alarm_rooster);
	private long[] mVibratePattern = { 0, 200, 200, 300 };

	private Button mBtnNtice;
	private Button mBtnNticeCust;
	
	/**
	 * for custom layout notification!
	 */
	
	RemoteViews mContentView = new RemoteViews("mahbubul.syeed.mynotificationtutorial",
			R.layout.custom_notificaiton);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);

		mNotificationIntent = new Intent(getApplicationContext(),
				NotificationReceiverActivity.class);
		mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
				mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

		mBtnNtice = (Button) findViewById(R.id.btn_notification);
		mBtnNticeCust = (Button) findViewById(R.id.btn_notification_cust);

		this.setClickListener();

	}

	private void setClickListener() {

		mBtnNtice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Define the Notification's expanded message and Intent:

				Notification.Builder notificationBuilder = new Notification.Builder(
						getApplicationContext())
						.setTicker(tickerText)
						.setSmallIcon(android.R.drawable.stat_sys_warning)
						.setAutoCancel(true)
						.setContentTitle(contentTitle)
						.setContentText(
								contentText + " (" + ++mNotificationCount + ")")
						.setContentIntent(mContentIntent).setSound(soundURI)
						.setVibrate(mVibratePattern);

				// Pass the Notification to the NotificationManager:
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(MY_NOTIFICATION_ID,
						notificationBuilder.build());

			}
		});

		
		
		mBtnNticeCust.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Define the Notification's expanded message and Intent:

				mContentView.setTextViewText(R.id.text_notification, contentText + " ("
						+ ++mNotificationCount + ")");

				// Build the Notification

				Notification.Builder notificationBuilder = new Notification.Builder(
						getApplicationContext())
					.setTicker(tickerText)
					.setSmallIcon(android.R.drawable.stat_sys_warning)
					.setAutoCancel(true)
					.setContentIntent(mContentIntent)
					.setSound(soundURI)
					.setVibrate(mVibratePattern)
					.setContent(mContentView);

				// Pass the Notification to the NotificationManager:
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(MY_NOTIFICATION_ID,
						notificationBuilder.build());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
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
