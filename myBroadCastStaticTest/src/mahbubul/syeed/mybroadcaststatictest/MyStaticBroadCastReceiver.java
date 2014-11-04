package mahbubul.syeed.mybroadcaststatictest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class MyStaticBroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		// Vibrate the device for 500 ms upon receiving the
		// broadcast
		Vibrator v = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(500);

		// also display a toast.
		Toast.makeText(context, "Boradcast received successfully",
				Toast.LENGTH_LONG).show();
	}

}
