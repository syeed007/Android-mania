package com.example.mybuttontutorial;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class CheckboxActivity extends ActionBarActivity {

	Button mChangeVisibBtn = null;
	static CheckBox mCheckBox1 = null;
	static CheckBox mCheckBox2 = null;
	static CheckBox mCheckBox3 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkbox);
		
		//get the reference of the checkboxes
		mCheckBox1 = (CheckBox) findViewById(R.id.checkbox_browser);
		mCheckBox2 = (CheckBox) findViewById(R.id.checkbox_calender);
		mCheckBox3 = (CheckBox) findViewById(R.id.checkbox_contact);
		
		//button is used to hide or unhide the checkboxes
		mChangeVisibBtn = (Button) findViewById(R.id.btn_checklist_visibility);
		
		
		this.addButtonSelectionListener();
	}

	
	/**
	 * Adding lister to all the check boxes.
	 * Each check box is added separately in android. hence each event should be handled
	 * independently
	 * @param view
	 */
	public void onCheckBoxClick(View view) {
		
	    // Is the view now checked?
	    boolean checked = ((CheckBox) view).isChecked();
	    
	    // Check which checkbox was clicked
	    switch(view.getId()) {
	        case R.id.checkbox_browser:
	            if (checked){
	            	Toast.makeText(CheckboxActivity.this, "browser checked", Toast.LENGTH_SHORT).show();
	            }   
	            else
	            	Toast.makeText(CheckboxActivity.this, "browser un-checked", Toast.LENGTH_SHORT).show();
	            break;
	        case R.id.checkbox_calender:
	            if (checked){
	            	Toast.makeText(CheckboxActivity.this, "calender checked", Toast.LENGTH_SHORT).show();
	            }
	                
	            else
	            	Toast.makeText(CheckboxActivity.this, "calender un-checked", Toast.LENGTH_SHORT).show();
	            break;
	        // TODO: Veggie sandwich
	        case R.id.checkbox_contact:
	            if (checked){
	            	Toast.makeText(CheckboxActivity.this, "contact checked", Toast.LENGTH_SHORT).show();
	            }
	                // Cheese me
	            else
	            	Toast.makeText(CheckboxActivity.this, "contact un-checked", Toast.LENGTH_SHORT).show();
	            break;
	        // TODO: Veggie sandwich
	    }
	}
	
	
	private void addButtonSelectionListener(){
		mChangeVisibBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// if the checkboxes are visible then hide then all together.
				if(mCheckBox1.isShown() && mCheckBox2.isShown() && mCheckBox3.isShown())
				{
					mCheckBox1.setVisibility(View.INVISIBLE);
					mCheckBox2.setVisibility(View.INVISIBLE);
					mCheckBox3.setVisibility(View.INVISIBLE);
				}
				//otherwise bring them into existence.
				else
				{
					mCheckBox1.setVisibility(View.VISIBLE);
					mCheckBox2.setVisibility(View.VISIBLE);
					mCheckBox3.setVisibility(View.VISIBLE);
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checkbox, menu);
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
