package rajit.syeed.mahbubul.mylocationaddress;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class provides the address lookup service. The intent service handles an intent asynchronously
 * on a worker thread, and stops itself when it runs out of work. The intent extras provide the data
 * needed by the service, including a Location object for conversion to an address, and a ResultReceiver
 * object to handle the results of the address lookup. The service uses a Geocoder to fetch the address
 * for the location, and sends the results to the ResultReceiver.
 */
public class FetchAddressIntentService extends IntentService {

    protected Geocoder mGeocoder;
    Location mLocationToResolve;
    protected ResultReceiver mReceiver;




    public FetchAddressIntentService() {
        super("");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        mGeocoder = new Geocoder(this, Locale.getDefault()); // object to handle the reverse geocoding.

        // Get the location passed to this service through an extra.
        mLocationToResolve = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
        //get the receiver of the address
        //mReceiver = (ResultReceiver)intent.getExtras().get(Constants.RECEIVER);
        mReceiver = intent.getParcelableExtra(Constants.RECEIVER);

        String errorMessage = "";          //holds the error message if occurs
        List<Address> addresses = null;    //holds the addresses that is found in reverse geocoding


        try
        {
            //reverse geocoding the location
            addresses = mGeocoder.getFromLocation(
                    mLocationToResolve.getLatitude(),
                    mLocationToResolve.getLongitude(),
                    // In this sample, get just a single address.
                    1);

        }
        // Catch network or other I/O problems.
        catch (IOException ioException) {

            errorMessage = "The background geocoding service is not available, due to a network error or IO exception.";

        }
        // Catch invalid latitude or longitude values.
        catch (IllegalArgumentException illegalArgumentException) {

            errorMessage = "The latitude and/or longitude values provided in the Location object are invalid.";
            /*
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + mLocationToResolve.getLatitude() +
                    ", Longitude = " +
                    mLocationToResolve.getLongitude(), illegalArgumentException);
                    */
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = "The geocoder could not find an address for the given latitude/longitude";
                //Log.e(TAG, errorMessage);
            }

            //return the error message with the failure error code.
            deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage);
        }

        //if the location is successfully reverse geocoded to address.
        else {

            Address address = addresses.get(0);                           //get the only address
            ArrayList<String> addressFragments = new ArrayList<String>(); //list hold each line of the address

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }

           // Log.i(TAG, getString(R.string.address_found));

            //return the address with the success code
            deliverResultToReceiver(Constants.SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments));
        }


    }


    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY, message);
        mReceiver.send(resultCode, bundle);
    }
}
