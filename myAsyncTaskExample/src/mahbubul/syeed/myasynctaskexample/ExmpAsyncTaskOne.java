package mahbubul.syeed.myasynctaskexample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ExmpAsyncTaskOne extends Activity {

	Button mButton1;
	Button mButton2;
	
	ImageView mImageView;
	ProgressBar mProgress;
	int mDelay = 500;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exmp_async_task_one);
		
		mButton1 = (Button) findViewById(R.id.loadButton);
		mButton2 = (Button) findViewById(R.id.otherButton);
		mImageView = (ImageView) findViewById(R.id.imageView);
		mProgress = (ProgressBar) findViewById(R.id.progressBar);
		
		mButton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//start the background thread..
				new loadImageAsync().execute(R.drawable.painter);
			}
		});
		
		mButton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(ExmpAsyncTaskOne.this, "Other button is working here :", Toast.LENGTH_LONG).show(); 
			}
		});
		
	}

	
	public class loadImageAsync extends AsyncTask<Integer, Integer, Bitmap>{

		//this method executes in ui thread
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			//make the bar visible..
			mProgress.setVisibility(ProgressBar.VISIBLE);  
		}
		
		//this method executes in background thread
		@Override
		protected Bitmap doInBackground(Integer... params) {
			
			//get the bitmap
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), params[0]);
			
			//run the delay loop
			for(int i = 1; i < 11; i++){
				
				sleep();   // mock up for long running job
				
				publishProgress(i*10);  // this method call invokes onprogressUpdate()
			}
			return bmp;
		}
		
		//this methods executes in ui thead: for showing periodic updates..
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			mProgress.setProgress(values[0]); //increase the progress bar
		}
		
		//this method executes in ui thread: offers the result returnd by background thread.
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			
			mProgress.setVisibility(ProgressBar.INVISIBLE);
			mImageView.setImageBitmap(result);
		}
		
		//delay method..
		private void sleep(){
			
			try {
				Thread.sleep(mDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
