/*
 * WATER LEADERS ACADEMY APPLICATION
 * 
 * FILE: WaterLeaders.java
 * DESCRIPTION: Class responsible for inflating and managing the first screen,
 * 				the menu of the application.
 * */

package com.ds.waterleadersacademy;

import com.example.waterleadersacademy.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

@SuppressWarnings("deprecation")
public class MenuActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// call superclass's version
		super.onCreate(savedInstanceState);
		
		//Opens app in portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		// inflate the menu screen GUI
		setContentView(R.layout.main);
		
		// open the connection with the database
		DatabaseConnector databaseConnector = new DatabaseConnector(this);
		databaseConnector.open();
		
		/* in case there are any records in the database, the first record must
		 * be created and all the fields must be initialized with 0 */
		long n = databaseConnector.getTotalRecords();
		if(n == 0) {
			databaseConnector.insertRecord(0, 0, 0, 0, 0, 0, 0, 0, 0);
		}
	
		// close the connection with the database
		databaseConnector.close();
		
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
	
	// Shower button on click event
	public void onClickShower(View view) {
		// create a new Intent object, responsible for launching the shower activity
		Intent showerIntent = new Intent(MenuActivity.this, ShowerActivity.class);
		startActivity(showerIntent);
	}
	
	// Toilet button on click event
	public void onClickToilet(View view) {
		// create a new Intent object, responsible for launching the toilet activity
		Intent showerIntent = new Intent(MenuActivity.this, ToiletActivity.class);
		startActivity(showerIntent);
	}
		
	// Bathroom button on click event
	public void onClickBathroom(View view) {
		// create a new Intent object, responsible for launching the bathroom activity
		Intent showerIntent = new Intent(MenuActivity.this, BathroomActivity.class);
		startActivity(showerIntent);
	}
	
	// Kitchen button on click event
	public void onClickKitchen(View view) {
		// create a new Intent object, responsible for launching the kitchen activity
		Intent showerIntent = new Intent(MenuActivity.this, KitchenActivity.class);
		startActivity(showerIntent);
	}
	
	// Dishwasher button on click event
	public void onClickDishwasher(View view) {
		// create a new Intent object, responsible for launching the dishwasher activity
		Intent showerIntent = new Intent(MenuActivity.this, DishwasherActivity.class);
		startActivity(showerIntent);
	}
	
	// Laundry button on click event
	public void onClickLaundry(View view) {
		// create a new Intent object, responsible for launching the laundry activity
		Intent showerIntent = new Intent(MenuActivity.this, LaundryActivity.class);
		startActivity(showerIntent);
	}
	
	// Sprinklers button on click event
	public void onClickSprinklers(View view) {
		// create a new Intent object, responsible for launching the sprinklers activity
		Intent showerIntent = new Intent(MenuActivity.this, SprinklersActivity.class);
		startActivity(showerIntent);
	}
	
	// Miscellaneous button on click event
	public void onClickMisc(View view) {
		// create a new Intent object, responsible for launching the miscellaneous activity
		Intent showerIntent = new Intent(MenuActivity.this, MiscActivity.class);
		startActivity(showerIntent);
	}
	
	// Outdoor hose button on click event
	public void onClickOutdoorHose(View view) {
		// create a new Intent object, responsible for launching the outdoor hose activity
		Intent showerIntent = new Intent(MenuActivity.this, OutdoorHoseActivity.class);
		startActivity(showerIntent);
	}
	
	// Report activity button on click event
	public void onClickReport(View view) {
		// create a new Intent object, responsible for launching the report activity
		Intent showerIntent = new Intent(MenuActivity.this, ReportActivity.class);
		startActivity(showerIntent);
	}
	
}
