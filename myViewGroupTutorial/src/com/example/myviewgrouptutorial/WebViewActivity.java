package com.example.myviewgrouptutorial;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class WebViewActivity extends Activity {

	private EditText mURL;
	WebView mWebView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);

		mURL = (EditText) findViewById(R.id.text_url);
		mWebView = (WebView) findViewById(R.id.webView_show);

		// Set a kind of listener on the WebView so the WebView can intercept
		// URL loading requests if it wants to

		mWebView.setWebViewClient(new HelloWebViewClient());

		//enable the java script
		mWebView.getSettings().setJavaScriptEnabled(true);

		//default loading the google search engine.
		mWebView.loadUrl("http://www.google.com");

		this.setClickListenerToTxt();
	}

	private class HelloWebViewClient extends WebViewClient {
		private static final String TAG = "HelloWebViewClient";;

		// Give application a chance to catch additional URL loading requests
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.i(TAG, "About to load:" + url);
			view.loadUrl(url);
			return true;
		}
	}

	private void setClickListenerToTxt() {

		mURL.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				// If the event is a key-down event on the "enter" button
				// if enter is pressed then load the page on the webview.

				String url = mURL.getText().toString();
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
					Toast.makeText(WebViewActivity.this, "URL: " + url,
							Toast.LENGTH_SHORT).show();

					// load the url
					mWebView.loadUrl(url);
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
