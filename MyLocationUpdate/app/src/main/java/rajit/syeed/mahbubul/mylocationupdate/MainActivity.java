package rajit.syeed.mahbubul.mylocationupdate;

import android.location.Location;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    String mLastTimeUpdated;

    TextView mLongitudeStart;
    TextView mLatitudeStart;
    TextView mLongitudeCurrent;
    TextView mLatitudeCurrent;
    TextView mLastUpdateTime;


    // Keys for storing activity state in the Bundle.
    protected final static String LOCATION_KEY = "location-key";
    protected final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        mLongitudeStart = (TextView) findViewById(R.id.longitude_start);
        mLatitudeStart = (TextView) findViewById(R.id.latitude_start);

        mLongitudeCurrent = (TextView) findViewById(R.id.longitude_current);
        mLatitudeCurrent = (TextView) findViewById(R.id.latitude_current);

        mLastUpdateTime = (TextView) findViewById(R.id.lastupdatetime);


        // Update values using data stored in the Bundle.
        updateValuesFromBundle(savedInstanceState);


        //step: 1 create the client
        buildGoogleApiClient();
    }


    //step (optional): persistance data for app configuration changes.

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //save the current location and last location update time in bundle.
        outState.putParcelable(LOCATION_KEY, mLastLocation);
        outState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastTimeUpdated);

        super.onSaveInstanceState(outState);
    }


    //step (optional): update the location information after the orientaion change..
    
    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {


            // Update the value of mCurrentLocation from the Bundle and update the
            // UI to show the correct latitude and longitude.
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                // Since LOCATION_KEY was found in the Bundle, we can be sure that
                // mCurrentLocationis not null.
                mLastLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }

            // Update the value of mLastUpdateTime from the Bundle and update the UI.
            if (savedInstanceState.keySet().contains(LAST_UPDATED_TIME_STRING_KEY)) {
                mLastTimeUpdated = savedInstanceState.getString(
                        LAST_UPDATED_TIME_STRING_KEY);
            }
            updateUI();
        }
    }




    //step: 1 create the client
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    //step 2: start the client connection
    @Override
    protected void onStart() {
        super.onStart();

        //this call invokes the onConnected method while the connection is established successfully
        mGoogleApiClient.connect();

        //set location update request preferences.
        createLocationRequest();
    }

    //Step 2: terminate connection while app terminates
    @Override
    protected void onStop() {
        super.onStop();

        if(mGoogleApiClient.isConnected())
        mGoogleApiClient.disconnect();
    }

    //step 4: suspend location update when application is on background
    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    //step 4: restart when it is active again
    @Override
    protected void onRestart() {
        super.onRestart();

        if(mGoogleApiClient.isConnected() //&& !mRequestingLocationUpdates
        )
            startLocationUpdates();
    }

    //step 3: if connection is made, this method is called for
    // (a) retrieving current known location
    // (b) periodic update of the location while the location of the device changes.

    @Override
    public void onConnected(Bundle bundle) {

        // get the current known location
        getCurrentKnownLocation();

        //update the location info (if location is changed)

        //if (mRequestingLocationUpdates) {
            startLocationUpdates();
        //}


    }


    //step 2: reconnect if connection suspended.
    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "OHH!! Connection suspended!! Reconnecting..", Toast.LENGTH_LONG).show();
        mGoogleApiClient.connect();
    }


    //step 2: if connection failed, notify user with the error code.
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode(), Toast.LENGTH_SHORT).show();
    }





    //step 3(a): retrieve current location
    public void getCurrentKnownLocation(){
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        //if current location is found display it
        if (mLastLocation != null) {
            mLatitudeStart.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeStart.setText(String.valueOf(mLastLocation.getLongitude()));
            Log.i("onConnected Called: ", "location detected!!!!!!");
        }
        //otherwise, show a message
        else {
            Toast.makeText(this, "OHH!! No Location detected. Make sure Location is enabled..", Toast.LENGTH_LONG).show();

        }
    }


    //step 3 (b)-1: Create location request preference

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);  // update interval in ms
        mLocationRequest.setFastestInterval(5000);  //fastest request interval
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // accuracy priority
    }

    //step 3 (b)-2: Start the location update service.
    // it will call the location changed callback method while the location changes.

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    //step 3 (b)-3: implement the call back to get the location and location retrieval time
    //display it in the ui.
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        mLastTimeUpdated = DateFormat.getTimeInstance().format(new Date());
        updateUI();
    }


    private void updateUI() {
        mLatitudeCurrent.setText(String.valueOf(mLastLocation.getLatitude()));
        mLongitudeCurrent.setText(String.valueOf(mLastLocation.getLongitude()));
        mLastUpdateTime.setText(mLastTimeUpdated);
    }






    /**
     * Methods for action bar menu
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
