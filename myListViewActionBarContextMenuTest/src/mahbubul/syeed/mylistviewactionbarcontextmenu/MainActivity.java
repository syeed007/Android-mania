package mahbubul.syeed.mylistviewactionbarcontextmenu;

import java.util.ArrayList;

import mahbubul.syeed.mylistviewfunctionalitytest.R;
import android.app.ListActivity;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author rajit
 *
 *This example offers the followings:
 *
 *1. a context menu for long pressing list item.
 *2. Delete the selected item if the menu item is selected.
 *3. Show empty list view when the list is empty
 *-----------------------------------------------------------------
 */
public class MainActivity extends ListActivity {

	String[] values = new String[] { "Android"};
	/*
	String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
			"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
			"Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
			"OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
			"Android", "iPhone", "WindowsMobile" };
	*/
	//variables for default array adapter test
	final ArrayList<String> list = new ArrayList<String>();
	ArrayAdapter<String> adapter; 
	
	//variables for custom array adapter test
	CustomArrayAdapter mCustomAdapter;

	 protected Object mActionMode;
	  public int selectedItem = -1;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		/**
		 * Set a emplty list view to show when the list is empty..
		 */
		View emptyView = getLayoutInflater().inflate(R.layout.listitem_layout, null);
		addContentView(emptyView, getListView().getLayoutParams()); // You're using the same params as listView
		
		getListView().setEmptyView(emptyView);
		
		/**
		 * Testing the Customized array adapter for custom layout
		 */
		this.testCustomArrayAdapter();

		/**
		 * set click and long click listener
		 */
		this.setOnClickListener();
		//this.setOnLongClickListener();
		this.setOnLongClickListenerForCustomArrayAdapter();
		
		
	}


	
	/**
	 * Custom array adapter test
	 */
	private void testCustomArrayAdapter(){
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}	
		mCustomAdapter = new CustomArrayAdapter(
				MainActivity.this, list);
		
		getListView().setAdapter(mCustomAdapter);
		
		//getListView().setEmptyView(findViewById(R.id.em));
	}
	
	
	
	
	/**
	 * show a toast for clicking the item
	 */
	private void setOnClickListener() {
		getListView().setOnItemClickListener(
				new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent,
							final View view, int position, long id) {
						final String item = (String) parent
								.getItemAtPosition(position);
						Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
					}

				});
	}
	
	
	/**
	 * delete item on long press on that list item.
	 */
	private void setOnLongClickListenerForCustomArrayAdapter() {
		/*getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, final View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final String item = (String) parent
						.getItemAtPosition(position);
				view.animate().setDuration(2000).alpha(0)
						.withEndAction(new Runnable() {
							@Override
							public void run() {
								list.remove(item);
								mCustomAdapter.notifyDataSetChanged();
								view.setAlpha(1);
							}
						});
				return false;
			}
		});*/
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

		      @Override
		      public boolean onItemLongClick(AdapterView<?> parent, View view,
		          int position, long id) {

		        if (mActionMode != null) {
		          return false;
		        }
		        selectedItem = position;

		        // start the CAB using the ActionMode.Callback defined above
		        mActionMode = MainActivity.this
		            .startActionMode(new actionMode());
		        view.setSelected(true);
		        return true;
		      }
		    });
	}
	
	
	private class actionMode implements ActionMode.Callback{

		@Override
		// called when the action mode is created; startActionMode() was called
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	      // Inflate a menu resource providing context menu items
	      MenuInflater inflater = mode.getMenuInflater();
	      // assumes that you have "contexual.xml" menu resources
	      inflater.inflate(R.menu.rowselection, menu);
	      return true;
	    }

		@Override
		// the following method is called each time 
	    // the action mode is shown. Always called after
	    // onCreateActionMode, but
	    // may be called multiple times if the mode is invalidated.
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	      return false; // Return false if nothing is done
	    }

		@Override
		// called when the user selects a contextual menu item
	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	      switch (item.getItemId()) {
	      case R.id.menuitem1_show:
	        show();
	        //delete selected item
	        list.remove(selectedItem);
			mCustomAdapter.notifyDataSetChanged();
			
			/*
	        final String item = (String) parent
					.getItemAtPosition(position);
			view.animate().setDuration(2000).alpha(0)
					.withEndAction(new Runnable() {
						@Override
						public void run() {
							list.remove(item);
							mCustomAdapter.notifyDataSetChanged();
							view.setAlpha(1);
						}
					});
	        */
	        
	        // the Action was executed, close the CAB
	        mode.finish();
	        return true;
	      default:
	        return false;
	      }
	    }

		@Override
		// called when the user exits the action mode
	    public void onDestroyActionMode(ActionMode mode) {
	      mActionMode = null;
	      selectedItem = -1;
	    }
		
	}
	
	/*
	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

	    // called when the action mode is created; startActionMode() was called
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	      // Inflate a menu resource providing context menu items
	      MenuInflater inflater = mode.getMenuInflater();
	      // assumes that you have "contexual.xml" menu resources
	      inflater.inflate(R.menu.rowselection, menu);
	      return true;
	    }

	    // the following method is called each time 
	    // the action mode is shown. Always called after
	    // onCreateActionMode, but
	    // may be called multiple times if the mode is invalidated.
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	      return false; // Return false if nothing is done
	    }

	    // called when the user selects a contextual menu item
	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	      switch (item.getItemId()) {
	      case R.id.menuitem1_show:
	        show();
	        //delete selected item
	        list.remove(selectedItem);
			mCustomAdapter.notifyDataSetChanged();
			
		
	        
	        
	        // the Action was executed, close the CAB
	        mode.finish();
	        return true;
	      default:
	        return false;
	      }
	    }

	    // called when the user exits the action mode
	    public void onDestroyActionMode(ActionMode mode) {
	      mActionMode = null;
	      selectedItem = -1;
	    }
	  };
*/
	  private void show() {
	    Toast.makeText(MainActivity.this,
	        String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
	  }
	
	
	
} // end class
