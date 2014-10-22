package com.example.drawerexampletest;

import java.util.Locale;




import android.app.Activity;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	/**
	 * Application layout, drawer list view layout and the list content provider
	 */
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private String[] mDrawerContent;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mTitle;
	private CharSequence mDrawerTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get the current title
		mTitle = mDrawerTitle = getTitle();

		// get the layouts
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// load the array to the string array
		mDrawerContent = getResources().getStringArray(R.array.planets_array);

		/**
		 * Create a string adapter and attach it to the drawer list view context
		 * : mainactivity layout: list view layout for each list item content:
		 * string array for the adapter
		 * 
		 * This command loads the content to the drawer list.
		 */
		mDrawerList.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				R.layout.drawerlistitem_layout, mDrawerContent));

		// set selection listener to the drawer list
		DrawerItemClickListener customListener = new DrawerItemClickListener();
		mDrawerList.setOnItemClickListener(customListener);

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		/**
		 * Toggle action bar is used to toggle between the main window and the drawer.
		 */
		mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
				mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	
	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// Handle action buttons
				switch (item.getItemId()) 
				{
				case R.id.action_websearch:
				
					// create intent to perform web search for this planet
					Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
					intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
					
					// catch event that there's no activity to handle intent
					if (intent.resolveActivity(getPackageManager()) != null) {
						startActivity(intent);
					} else {
						Toast.makeText(this, R.string.app_not_available,
								Toast.LENGTH_LONG).show();
					}
					return true;
				
				default:
					return super.onOptionsItemSelected(item);
				}
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * This selection listener class listens to the list item selection. pass
	 * the position of the selected item to a item selector. The item selector
	 * starts over a new fragment in the frame layout and shows a image viewer
	 * with an image.
	 * 
	 * @author rajit
	 * 
	 */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			selectItem(position);
		}

		private void selectItem(int position) {

			Fragment showPlanetFragment = new PlanetFragment();

			/**
			 * Create a bundle to pass the list selection position to the
			 * fragment
			 */
			Bundle args = new Bundle();
			args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
			showPlanetFragment.setArguments(args);

			/**
			 * create a fragment manager and replace the existing one with this
			 * new fragment in the framelayout.
			 */
			FragmentManager fragManager = getFragmentManager();
			fragManager.beginTransaction()
					.replace(R.id.content_frame, showPlanetFragment).commit();

			/*
			 * highlight the list selected item close the drawer after
			 * selection.
			 */
			mDrawerList.setItemChecked(position, true);
			setTitle(mDrawerContent[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		}

	} // end of class DrawerItemClickListener

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	public static class PlanetFragment extends Fragment {

		// used to receive data from caller..
		public static final String ARG_PLANET_NUMBER = "planet_number";

		public PlanetFragment() {
			// Empty constructor required for fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.image_layout, container,
					false);

			int i = getArguments().getInt(ARG_PLANET_NUMBER);

			String planet = getResources()
					.getStringArray(R.array.planets_array)[i];

			int imageId = getResources().getIdentifier(
					planet.toLowerCase(Locale.getDefault()), "drawable",
					getActivity().getPackageName());

			((ImageView) rootView.findViewById(R.id.image))
					.setImageResource(imageId);

			getActivity().setTitle(planet);

			return rootView;
		}

	} // end fragment class

} // end activity class
