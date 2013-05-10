package com.example.soccerman;

import com.example.soccerman.R;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListMatchesActivity extends ListActivity {

	LoginDataBaseAdapter loginDataBaseAdapter;
	
	private String[] matches; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
 
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	    loginDataBaseAdapter=loginDataBaseAdapter.open();
 
	    Cursor matchCursor = loginDataBaseAdapter.getMatches();
	    matches = getMatchList(matchCursor);
	    
		setListAdapter(new ArrayAdapter<String>(this, R.layout.match_list,matches));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				SharedPreferences pref = getApplicationContext().getSharedPreferences("com.example.soccerman", 0);
			    String user = pref.getString("username", "");
				
				boolean admin = loginDataBaseAdapter.isAdmin(user);
				
				if(admin)
				{
					
				}
				else
				{
				    Intent attend = new Intent(ListMatchesActivity.this, MatchAttendanceActivity.class);
				    attend.putExtra("match_id", position+1);
				    startActivity(attend);
				}
			}
		});
	}
	
	private String[] getMatchList(Cursor cursor)
	{
		String[] matches = new String[cursor.getCount()];
		while(cursor.moveToNext())
		{
			matches[cursor.getPosition()] = cursor.getString(cursor.getColumnIndex("opponent"));
		}
		return matches;
	}
	
}
