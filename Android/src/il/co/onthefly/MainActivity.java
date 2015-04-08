package il.co.onthefly;

import il.co.onthefly.db.User;

import java.util.Locale;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements
		ConnectionCallbacks, OnConnectionFailedListener {
	ViewPager Tab;
	TabPagerAdapter TabAdapter;
	
	//Maps Integration
	ActionBar actionBar;
	GoogleApiClient googleApiClient;
	Location lastLocation;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_map:
			openMap();
			return true;
		case R.id.action_settings:
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void openMap() {
		String uri;
		
		uri ="geo:37.7749,-122.4192?q=Heathrow Airport";
		
		if (lastLocation != null) {
			String latitude = String.valueOf(lastLocation.getLatitude());
			String longitude = String.valueOf(lastLocation.getLongitude());
			uri = String.format(Locale.ENGLISH, "geo:"+latitude+","+longitude);
		}
		
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		MainActivity.this.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		buildGoogleApiClient();
		setContentView(R.layout.activity_main);
		
		TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
		Tab = (ViewPager) findViewById(R.id.pager);
		Tab.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar = getActionBar();
				actionBar.setSelectedNavigationItem(position);
			}
		});
		Tab.setAdapter(TabAdapter);
		actionBar = getActionBar();
		// Enable Tabs on Action Bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
				Tab.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}
		};
		// Add New Tab
		actionBar.addTab(actionBar.newTab().setText(R.string.tab_feed)
				.setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText(R.string.tab_people)
				.setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText(R.string.tab_chat)
				.setTabListener(tabListener));
	}

	protected synchronized void buildGoogleApiClient() {
		googleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		Location l = LocationServices.FusedLocationApi
				.getLastLocation(googleApiClient);
		if (l != null) {
			lastLocation = l;
		}
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

}
