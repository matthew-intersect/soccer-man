package com.example.soccerman;

import com.example.soccerman.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListMatchesActivity extends ListActivity {

	LoginDataBaseAdapter loginDataBaseAdapter;
	
	private String[] matches; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	    loginDataBaseAdapter=loginDataBaseAdapter.open();
 
	    matches = loginDataBaseAdapter.getMatches();
	    
		setListAdapter(new ArrayAdapter<String>(this, R.layout.match_list,matches));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
 
	}
	
}
