package com.example.mymenuandcontextmenututorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView mText = (TextView) findViewById(R.id.text);
        registerForContextMenu(mText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
        case R.id.rajit:
        	Toast.makeText(MainActivity.this, "rajit btn", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.mou:
        	Toast.makeText(MainActivity.this, "mou btn", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.suha:
        	Toast.makeText(MainActivity.this, "suha btn", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.samah:
        	Toast.makeText(MainActivity.this, "samah btn", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.help:
        	Toast.makeText(MainActivity.this, "help btn", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.action_settings:
        	Toast.makeText(MainActivity.this, "setting btn", Toast.LENGTH_SHORT).show();
        	break;
        }
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	// TODO Auto-generated method stub
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater contextMenu = getMenuInflater();
    	contextMenu.inflate(R.menu.menu_context, menu);
    	
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	int id = item.getItemId();
    	switch(id){
    	case R.id.rajit_me:
    		Toast.makeText(MainActivity.this, "context rajit", Toast.LENGTH_SHORT).show();
    		break;
    	case R.id.mou_me:
        	Toast.makeText(MainActivity.this, "context mou", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.suha_me:
        	Toast.makeText(MainActivity.this, "context suha", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.samah_me:
        	Toast.makeText(MainActivity.this, "context samah", Toast.LENGTH_SHORT).show();
        	break;
    		
    	}
    	return super.onContextItemSelected(item);
    }
}
