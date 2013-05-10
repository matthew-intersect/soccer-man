package com.example.soccerman;

import android.app.ListActivity;
import android.os.Bundle;

public class ViewMatchAttendanceActivity extends ListActivity 
{
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	    loginDataBaseAdapter=loginDataBaseAdapter.open();
	    
	    
	}
}
