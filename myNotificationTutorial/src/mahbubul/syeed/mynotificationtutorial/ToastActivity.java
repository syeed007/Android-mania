package mahbubul.syeed.mynotificationtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ToastActivity extends Activity {

	Button mToast;
	Button mToastCustom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toast);
		
		mToast = (Button) findViewById(R.id.btn_toast);
		mToastCustom = (Button) findViewById(R.id.btn_toast_cust);
		
		this.setClickListener();
	}

	private void setClickListener(){
		mToast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ToastActivity.this, "Tesing general Toast", Toast.LENGTH_SHORT).show();
			}
		});
		
		mToastCustom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast mToast = new Toast(ToastActivity.this);
				mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				mToast.setDuration(Toast.LENGTH_SHORT);
				
				mToast.setView(getLayoutInflater().inflate(R.layout.custom_toast, null));
				
				mToast.show();
				
			}
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toast, menu);
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
