package rajit.syeed.mahbubul.mylocationaddress;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{

    // variables
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected LocationRequest mLocationRequest;
    protected String mLastTimeUpdated;
    protected Boolean mAddressRequested = false;

    protected AddressResultReceiver mResultReceiver; //Receiver registered with this activity to get the response from FetchAddressIntentService.
    protected String mAddressOutput;

    //ui views
    TextView mLongitudeStart;
    TextView mLatitudeStart;
    TextView mLongitudeCurrent;
    TextView mLatitudeCurrent;
    TextView mLastUpdateTime;
    Button mAddressButton;
    EditText mAddressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        //get the UI view references
        prepareUIViews();


        // Update values using data stored in the Bundle.
        updateValuesFromBundle(savedInstanceState);


        //prepare the Google service api
        buildGoogleApiClient();

        mResultReceiver = new AddressResultReceiver(new Handler(), getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect();  // invokes onConnected callback method.

        createLocationRequest(); //set location update request preferences.
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if(mGoogleApiClient.isConnected())
            startLocationUpdates();
    }

    @Override
    protected void onStop() {
        super.onStop();

        //disconnect the client on activity stop
        if(mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        stopLocationUpdates();  // stop location update on pause.
    }

    /**
     * --------------------------
     * User Defined methods
     * --------------------------
     */

    //get the views.
    private void prepareUIViews(){

        mLongitudeStart = (TextView) findViewById(R.id.long_start);
        mLatitudeStart= (TextView) findViewById(R.id.lat_start);
        mLongitudeCurrent= (TextView) findViewById(R.id.long_current);
        mLatitudeCurrent= (TextView) findViewById(R.id.lat_current);
        mLastUpdateTime= (TextView) findViewById(R.id.update_time);
        mAddressButton= (Button) findViewById(R.id.address_button);
        mAddressText = (EditText) findViewById(R.id.editText);

        //starts the geocoder service for address resolving.

        mAddressButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // start the address resolving (reverse geocoding) service

                // Only start the service to fetch the address if GoogleApiClient is
                // connected.
                if (mGoogleApiClient.isConnected() && mLastLocation != null) {
                    startIntentService();
                }
                // If GoogleApiClient isn't connected, process the user's request by
                // setting mAddressRequested to true. Later, when GoogleApiClient connects,
                // launch the service to fetch the address. As far as the user is
                // concerned, pressing the Fetch Address button
                // immediately kicks off the process of getting the address.
                mAddressRequested = true;

                mAddressButton.setEnabled(false); //turn off the address button
            }
        });
    }

    //create the google api client
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    //retrieve the current known location and update the ui
    //invoked from onConnected callback method
    public void getCurrentKnownLocation(){
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        //if current location is found display it
        if (mLastLocation != null) {
            mLatitudeStart.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeStart.setText(String.valueOf(mLastLocation.getLongitude()));
            }
        //otherwise, show a message
        else {
            Toast.makeText(this, "OHH!! No Location detected. Make sure Location is enabled..", Toast.LENGTH_LONG).show();

        }
    }

    //create location request object and set the preference for location update.
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);  // update interval in ms
        mLocationRequest.setFastestInterval(5000);  //fastest request interval
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // accuracy priority
    }


    //Start the location update service.
    // it will call the location changed callback method while the location changes.
    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    //suspends location update
    //called from onpause() method.
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }


    /**
     * ---------------------
     * Callback methods
     * ---------------------
     */


    @Override
    public void onConnected(Bundle bundle) {

        getCurrentKnownLocation();

        startLocationUpdates();

        //if the location is available and geocoder is available and the address resover button is pressed, then
        //start the address resolver service.

        if (mLastLocation != null) {
            // Determine whether a Geocoder is available.
            if (!Geocoder.isPresent()) {
                Toast.makeText(this, "OHH! No geocoder available", Toast.LENGTH_LONG).show();
                return;
            }

            if (mAddressRequested) {
                startIntentService();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "OHH!! Connection suspended!! Reconnecting..", Toast.LENGTH_LONG).show();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode(), Toast.LENGTH_SHORT).show();
    }

    //this callback method is called when a locaiton change occurs.
    //called from startloationupdate() method.
    @Override
    public void onLocationChanged(Location location) {

        //update the last known location and set the update time
        mLastLocation = location;
        mLastTimeUpdated = DateFormat.getTimeInstance().format(new Date());

        //update the ui accordingly.
        mLatitudeCurrent.setText(String.valueOf(mLastLocation.getLatitude()));
        mLongitudeCurrent.setText(String.valueOf(mLastLocation.getLongitude()));
        mLastUpdateTime.setText(mLastTimeUpdated);
    }


    /**
     * ------------------------------
     * Methods to store and retrieve persistent data for activity state change (e.g., rotation of the screen)
     * ----------------------------------
     */


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //save the latest location readings and time for retrieval
        outState.putParcelable(Constants.LOCATION_KEY, mLastLocation);
        outState.putString(Constants.LAST_UPDATED_TIME_STRING_KEY, mLastTimeUpdated);

        super.onSaveInstanceState(outState);
    }


    //restore the saved values after configuration / state change.
    protected void updateValuesFromBundle(Bundle savedInstanceState){

        if(savedInstanceState != null){
            if(savedInstanceState.keySet().contains(Constants.LOCATION_KEY))
                mLastLocation = savedInstanceState.getParcelable(Constants.LOCATION_KEY);

            if(savedInstanceState.keySet().contains(Constants.LAST_UPDATED_TIME_STRING_KEY))
                mLastTimeUpdated = savedInstanceState.getString(Constants.LAST_UPDATED_TIME_STRING_KEY);

            //update the ui accordingly.
            mLatitudeCurrent.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeCurrent.setText(String.valueOf(mLastLocation.getLongitude()));
            mLastUpdateTime.setText(mLastTimeUpdated);
        }

    }


    /**
     * ----------------------
     * Code block for revierse geocoding service
     * ------------------------
     */


    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);
        startService(intent);
    }




    class AddressResultReceiver extends ResultReceiver {

        Context context;
        public AddressResultReceiver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string
            // or an error message sent from the intent service.
            mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            displayAddressOutput();

            // Show a toast message if an address was found.
            if (resultCode == Constants.SUCCESS_RESULT) {
                Toast.makeText(context, "Address successfully resolved!", Toast.LENGTH_SHORT).show();
            }
            else // Show a toast message if an address was found.
                if (resultCode == Constants.FAILURE_RESULT) {
                    Toast.makeText(context, "Ohh! Address cannot be resolved!", Toast.LENGTH_SHORT).show();
                }

            mAddressRequested = false; // Reset. Enable the Fetch Address button and stop showing the progress bar.

        }
    }


    protected void displayAddressOutput(){
        mAddressText.setText(mAddressOutput);

        if(!mAddressButton.isEnabled())
            mAddressButton.setEnabled(true);
    }
}
