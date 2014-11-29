package mahbubul.syeed.myasynctaskexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ExmpAsyncTaskTwo extends Activity {

	ProgressDialog PD;
	TextView mtxt;
	Button mButton1;
	Button mButton2;

	loadDataAsync mLoadAsync = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exmp_async_task_two);

		mtxt = (TextView) findViewById(R.id.text);
		mButton1 = (Button) findViewById(R.id.load);
		mButton2 = (Button) findViewById(R.id.other);

		mButton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// start the background thread..
				mLoadAsync = new loadDataAsync();
				mLoadAsync.execute();
			}
		});

		mButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(ExmpAsyncTaskTwo.this,
						"Other button is working here :", Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	/**
	 * Handles devices key press event..
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		// check if the back button is pressed. and
		// if pressed just then cancel the async task.
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK: {

			Toast.makeText(ExmpAsyncTaskTwo.this, "Back btn pressed...", Toast.LENGTH_SHORT).show();
			if(mLoadAsync != null && mLoadAsync.getStatus() == Status.RUNNING)
			{
				Toast.makeText(ExmpAsyncTaskTwo.this, "Back btn pressed.", Toast.LENGTH_SHORT).show();
				mLoadAsync.cancel(true);
			}
			break;
		}

		}

		return super.onKeyDown(keyCode, event);
	}

	private class loadDataAsync extends AsyncTask<Void, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			PD = new ProgressDialog(ExmpAsyncTaskTwo.this);
			PD.setTitle("Loading Content..");
			PD.setMessage("Started...");
			
			//set the cancellable true for the progress dialog
			//then when canceled call the thread cancel to true.
			PD.setCancelable(true);
			PD.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					mLoadAsync.cancel(true);
				}
			});
			PD.show();
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String str = "Done";

			for (int i = 1; i < 50; i++) {
				sleep();

				publishProgress("Loading.... DDL_1.1." + i + " file");
				
				// Escape early if cancel() is called
	             if (isCancelled()) {
	            	 break;
	             }
			}
			return str;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			PD.setMessage(values[0]);
			// PD.show();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			PD.dismiss(); // close the dialog box..
			mtxt.setText(result);
		}

		private void sleep() {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
