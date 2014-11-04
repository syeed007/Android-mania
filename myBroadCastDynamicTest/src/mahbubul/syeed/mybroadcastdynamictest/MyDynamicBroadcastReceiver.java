package mahbubul.syeed.mybroadcastdynamictest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class MyDynamicBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Show message and vibrate upon receiving a broadcast.
		
		Toast.makeText(context, "Broadcast received!", Toast.LENGTH_LONG).show();
		Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		
		v.vibrate(500);
	}

}
