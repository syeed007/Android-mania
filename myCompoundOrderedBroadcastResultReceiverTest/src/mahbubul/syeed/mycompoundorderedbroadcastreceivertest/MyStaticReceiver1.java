package mahbubul.syeed.mycompoundorderedbroadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

public class MyStaticReceiver1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		//Toast.makeText(context, "Static receiver 1", Toast.LENGTH_SHORT).show();
		Vibrator v = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(500);
		
		String tmp = getResultData() == null ? "" : getResultData();
		setResultData(tmp +" Hi from Res1");
	}

}
