package mahbubul.syeed.myhttpjsontest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.util.LangUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class JSONTextview extends ActionBarActivity {

	LoadJsonAsync mLoadJson = null;
	TextView mShowData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jsontextview);
		
		mShowData = (TextView) findViewById(R.id.text);
		
		//start loading data.
		mLoadJson = new LoadJsonAsync();
		mLoadJson.execute();
	}

	
	private class LoadJsonAsync extends AsyncTask<Void, Void, List<String>>{

		private static final String USER_NAME = "aporter";
		private static final String URL = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username="
				+ USER_NAME;
		
		AndroidHttpClient mHttpClient;
		
		@Override
		protected void onPreExecute() {
			
			mHttpClient = AndroidHttpClient.newInstance("");
		}
		
		
		
		@Override
		protected List<String> doInBackground(Void... params) {
			
			HttpGet mGetRequest = new HttpGet(URL);
			JSONResponsehandler mJsonResponseHandler = new JSONResponsehandler();
			
			try{
				return mHttpClient.execute(mGetRequest, mJsonResponseHandler);
			}
			catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		@Override
		protected void onPostExecute(List<String> result) {
			// TODO Auto-generated method stub
			for(int i = 0; i < result.size(); i++){
				mShowData.append(result.get(i));
				mShowData.append(System.getProperty ("line.separator"));
			}
		}
		
		
		
	} // end async class
	
	
	
	private class JSONResponsehandler implements ResponseHandler<List<String>>{

		private static final String LONGITUDE_TAG = "lng";
		private static final String LATITUDE_TAG = "lat";
		private static final String MAGNITUDE_TAG = "magnitude";
		private static final String EARTHQUAKE_TAG = "earthquakes";
		
		@Override
		public List<String> handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			
			List<String> jsonResult = new ArrayList<String>();
			
			//get the basic string response..
			String BasicResponse = new BasicResponseHandler()
			.handleResponse(response);
			
			try{
				
				//get the json object from the basic string response
				JSONObject mJsonResponseObj = (JSONObject) new JSONTokener(
						BasicResponse).nextValue();
				
				//get the entire json page in a array.
				JSONArray mJsonArray = mJsonResponseObj
						.getJSONArray(EARTHQUAKE_TAG);
				
				for(int eachJsonData = 0; eachJsonData < mJsonArray.length(); eachJsonData ++)
				{
					//get each map of data from the data list..
					JSONObject eachDataMap = (JSONObject) mJsonArray.get(eachJsonData);
					
					jsonResult.add("Magnitude: " + eachDataMap.get(MAGNITUDE_TAG) + 
							" Lan: " + eachDataMap.get(LONGITUDE_TAG)+ " Lat: "+ eachDataMap.get(LATITUDE_TAG));
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			return jsonResult;
		}
		
	}
	
} // end activity
