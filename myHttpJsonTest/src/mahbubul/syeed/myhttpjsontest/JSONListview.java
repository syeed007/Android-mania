package mahbubul.syeed.myhttpjsontest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class JSONListview extends ActionBarActivity {

	LoadJSONAsync mLoadJson = null;
	TextView mDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jsonlistview);

		mDisplay = (TextView) findViewById(R.id.textList);

		mLoadJson = new LoadJSONAsync();
		mLoadJson.execute();
	}

	private class LoadJSONAsync extends AsyncTask<Void, Void, List<String>> {

		// static final String URL =
		// "https://ajax.googleapis.com/ajax/services/feed/load?v=2.0&q=http://phys.org/rss-feed/&num=20";
		// static final String URL = "http://headers.jsontest.com/";
		static final String URL = "https://bugzilla.mozilla.org/rest/bug/35";

		AndroidHttpClient mHttpClient;

		@Override
		protected void onPreExecute() {
			mHttpClient = AndroidHttpClient.newInstance("");
		}

		@Override
		protected List<String> doInBackground(Void... params) {

			HttpGet mGetRequest = new HttpGet(URL);
			JSONResponseHandler mRsponseHandler = new JSONResponseHandler();

			try {
				return mHttpClient.execute(mGetRequest, mRsponseHandler);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<String> result) {

			for (int i = 0; i < result.size(); i++) {
				mDisplay.append(System.getProperty("line.separator"));
				mDisplay.append(result.get(i));

				Toast.makeText(JSONListview.this, result.get(i),
						Toast.LENGTH_SHORT).show();
			}
		}

	} // end async task..

	private class JSONResponseHandler implements ResponseHandler<List<String>> {

		static final String BUG_TAG = "bugs";
		static final String CC_TAG = "cc_detail";
		static final String EMAIL_TAG = "email";
		static final String ALIAS_TAG = "alias";
		
		@Override
		public List<String> handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {

			List<String> jsonResult = new ArrayList<String>();

			// get the basic string response..
			String BasicResponse = new BasicResponseHandler()
					.handleResponse(response);

			// get the json object from the basic string response
			try {

				JSONObject mJsonResponseObj = (JSONObject) new JSONTokener(
						BasicResponse).nextValue();
				
				
				//the parent structure is an array named "Bugs"
				//get that array elements
				JSONArray mJsonBugsArray = mJsonResponseObj.getJSONArray(BUG_TAG);
				
				for(int i = 0; i < mJsonBugsArray.length(); i++)
				{
					JSONObject obj = mJsonBugsArray.getJSONObject(i);
					
					//add the alias..
					jsonResult.add("alias: " + obj.getString(ALIAS_TAG));
					
					
					//get the cc email addresses
					JSONArray mJsonArrayCC = obj.getJSONArray(CC_TAG);
					if(mJsonArrayCC != null)
					{
						for(int j = 0; j < mJsonArrayCC.length(); j++)
						{
							JSONObject mails = mJsonArrayCC.getJSONObject(j);
							jsonResult.add("Email: " + mails.getString(EMAIL_TAG));
						}
					}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return jsonResult;
		}
	}
}
