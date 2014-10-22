package com.example.internetconnectivitycheck;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DetectConnectivity {

	private Context mContext;

	public DetectConnectivity(Context context) {
		this.mContext = context;
	}

	/**
	 * check internet connection status and returns true of false.
	 * 
	 * @return
	 */
	public boolean isConnectingToInternet() {
		ConnectivityManager mConnectivity = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (mConnectivity != null) {
			NetworkInfo[] mInfo = mConnectivity.getAllNetworkInfo();

			if (mInfo != null)
				for (int i = 0; i < mInfo.length; i++)
					if (mInfo[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}
} // end class
