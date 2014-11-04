package mahbubul.syeed.mycompoundorderedbroadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

public class MyStaticReceiver2 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//Toast.makeText(context, "Static receiver 2", Toast.LENGTH_SHORT).show();
		Vibrator v = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(500);
		
		//get the data (if any) broadcasted by the previous receivers..
		String ret = getResultData() == null ? "": getResultData();
		
		//append this receiver data and broadcast it..
		setResultData(ret + " Hi From Rec2");
	}

}
