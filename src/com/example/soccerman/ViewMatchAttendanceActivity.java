package com.example.soccerman;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewMatchAttendanceActivity extends ListActivity 
{
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	private List<Player> players;
	int match;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	    loginDataBaseAdapter=loginDataBaseAdapter.open();
	    
	    Bundle extras = getIntent().getExtras();
	    match = extras.getInt("match_id");
	    
	    players = generateAttendanceList(loginDataBaseAdapter.getPlayers(match));

	    String[] p = new String[players.size()];
	    for(Player play : players)
	    {
	    	p[players.indexOf(play)] = play.getName() + " (" + play.getAttendance().getDisplayText() + ")";
	    }
	    
	    setListAdapter(new ArrayAdapter<String>(this, R.layout.match_list, p));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
	}
	
	private List<Player> generateAttendanceList(Cursor cursor)
	{
		List<Player> playerList = new ArrayList<Player>();
		while(cursor.moveToNext())
		{
			int userId = cursor.getInt(cursor.getColumnIndex("id"));
			String first = cursor.getString(cursor.getColumnIndex("firstname"));
			String last = cursor.getString(cursor.getColumnIndex("lastname"));
			
			PlayerAttendance a = loginDataBaseAdapter.getAttendance(userId, match);
			playerList.add(new Player((first + " " + last), a));
		}
		
		return playerList;
	}
	
//	private class CustomAdapter extends ArrayAdapter<Player>
//	{
//		CustomAdapter() 
//		{
//			super(ViewMatchAttendanceActivity.this, R.layout.row, R.id.label, players);
//		}
//	}
	
	private class Player
	{
		private String name;
		private PlayerAttendance attendance;
		
		public Player(String name, PlayerAttendance attend)
		{
			this.setName(name);
			this.setAttendance(attend);
		}

		public PlayerAttendance getAttendance()
		{
			return attendance;
		}

		public void setAttendance(PlayerAttendance attendance)
		{
			this.attendance = attendance;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}
	}
}
