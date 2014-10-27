package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLoaderActivity extends Activity {
    
	static private final int GET_TEXT_REQUEST_CODE = 1;
	static private final String URL = "http://www.google.com";
	static private final String TAG = "Lab-Intents";
    
	// For use with app chooser
	static private final String CHOOSER_TEXT = "Load " + URL + " with:";
    
	// TextView that displays user-entered text from ExplicitlyLoadedActivity runs
	private TextView mUserTextView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_activity);
		
		// Get reference to the textView
		mUserTextView = (TextView) findViewById(R.id.textView1);
        
		// Declare and setup Explicit Activation button
		Button explicitActivationButton = (Button) findViewById(R.id.explicit_activation_button);
		explicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Call startExplicitActivation() when pressed
			@Override
			public void onClick(View v) {
				
				startExplicitActivation();
                
			}
		});
        
		// Declare and setup Implicit Activation button
		Button implicitActivationButton = (Button) findViewById(R.id.implicit_activation_button);
		implicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Call startImplicitActivation() when pressed
			@Override
			public void onClick(View v) {
                
				startImplicitActivation();
                
			}
		});
        
	}
    
	
	// Start the ExplicitlyLoadedActivity
	
	private void startExplicitActivation() {
        
		Log.i(TAG,"Entered startExplicitActivation()");
		
		// TODO - Create a new intent to launch the ExplicitlyLoadedActivity class
		Intent explicitIntent = null;
		
		//create an intent object with the calling activity context and the explicitly calling an activity
		explicitIntent = new Intent(ActivityLoaderActivity.this, ExplicitlyLoadedActivity.class);
		
		//starting the activity to return results.
		//param: intent and the unique identifier for the request. this identifier is used to 
		//verify whether a return result is for this activity or not!
		startActivityForResult(explicitIntent, GET_TEXT_REQUEST_CODE);
		
		// TODO - Start an Activity using that intent and the request code defined above
		
        
        
	}
    
	// Start a Browser Activity to view a web page or its URL
	
	@SuppressWarnings("static-access")
	private void startImplicitActivation() {
        
		Log.i(TAG, "Entered startImplicitActivation()");
        
		// TODO - Create a base intent for viewing a URL
		// (HINT:  second parameter uses Uri.parse())
		Uri webpage = Uri.parse("http://www.google.com");		
        Intent baseIntent = null;
        
        baseIntent = new Intent(Intent.ACTION_VIEW, webpage);
		
		// TODO - Create a chooser intent, for choosing which Activity
		// will carry out the baseIntent
		// (HINT: Use the Intent class' createChooser() method)
		Intent chooserIntent = null;
        chooserIntent = Intent.createChooser(baseIntent, "Load http://www.google.com with:");
        
		//Log.i(TAG,"Chooser Intent Action:" + chooserIntent.getAction());
        
        
		// TODO - Start the chooser Activity, using the chooser intent
		startActivity(chooserIntent);
        
	}
    
	/**
	 * This method is called when a result is returned by a calling activity
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        
		Log.i(TAG, "Entered onActivityResult()");
		
		// TODO - Process the result only if this method received both a
		// RESULT_OK result code and a recognized request code
		// If so, update the Textview showing the user-entered text.

		// Check which request we're responding to
	    if (requestCode == GET_TEXT_REQUEST_CODE) {
	    	
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	            // if the result is successfully returned process it as required!
	        	String receivedText = data.getStringExtra("course.labs.intentslab.ExplicitlyLoadedActivity");
	        	mUserTextView.setText(receivedText);
	        }
	    }
    
    
    
    
    }
}