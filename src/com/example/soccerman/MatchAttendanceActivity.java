package com.example.soccerman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MatchAttendanceActivity extends Activity
{
	Button yesButton, noButton;
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	int userId, match;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match_attendance);
		
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	    loginDataBaseAdapter=loginDataBaseAdapter.open();
	    
	    SharedPreferences pref = getApplicationContext().getSharedPreferences("com.example.soccerman", 0);
	    userId = pref.getInt("userId", -1);

	    Bundle extras = getIntent().getExtras();
	    match = extras.getInt("match_id");
	}
	
	public void yes(View V)
	{
		loginDataBaseAdapter.addAttendance(userId, match, true);
		Intent list = new Intent(MatchAttendanceActivity.this, ListMatchesActivity.class);
		startActivity(list);
	}
	
	public void no(View V)
	{
		loginDataBaseAdapter.addAttendance(userId, match, false);
		Intent list = new Intent(MatchAttendanceActivity.this, ListMatchesActivity.class);
		startActivity(list);
	}
	
}
