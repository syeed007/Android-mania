package com.example.mybuttontutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	String [] mButtonList = null;
	ListView mButtonListView = null;
	
	static final int BUTTON = 0;
	static final int TOGG_BUTTON = 1;
	static final int CHECK_BUTTON = 2;
	static final int RATING_BUTTON = 3;
	static final int AUTO_COMPLETE = 4;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //get the list of button in the string array
        mButtonList = getResources().getStringArray(R.array.button_types);
        
        //get the list view
        mButtonListView = (ListView) findViewById(R.id.button_list);
        
        //create the simple array list adapter with the list item
        ArrayAdapter<String> buttonListAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item_layout, mButtonList);
        
        //append the adapter with the list view
        mButtonListView.setAdapter(buttonListAdapter);
        
        this.setButtonListViewListener();
    }


    private void setButtonListViewListener(){
    	mButtonListView.setOnItemClickListener(new OnItemClickListener() {
    		@Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
                
                String item = ((TextView)view).getText().toString();
                String itemFromList = mButtonList[position];
                
                Toast.makeText(getBaseContext(), "Item: " + item + " Position: " + position + " ItemFromArray: " + itemFromList, 
                		Toast.LENGTH_LONG).show();
                
                Intent mActivityIntent = null;
                
                switch(position){
                case BUTTON:
                	mActivityIntent = new Intent(MainActivity.this, ButtonActivity.class);
                	startActivity(mActivityIntent);
                	break;
                case TOGG_BUTTON:
                	mActivityIntent = new Intent(MainActivity.this,ToggleButtonActivity.class);
                	startActivity(mActivityIntent);
                	break;
                case CHECK_BUTTON:
                	mActivityIntent = new Intent(MainActivity.this, CheckboxActivity.class);
                	startActivity(mActivityIntent);
                	break;
                case RATING_BUTTON:
                	mActivityIntent = new Intent(MainActivity.this, RatingBarActivity.class);
                	startActivity(mActivityIntent);
                	break;
                case AUTO_COMPLETE:
                	mActivityIntent = new Intent(MainActivity.this, AutoCompleteTextActivity.class);
                	startActivity(mActivityIntent);
                	break;
                }
                
                
            }
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
