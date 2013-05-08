package com.example.soccerman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter 
{
		static final String DATABASE_NAME = "soccerman.db";
		static final int DATABASE_VERSION = 5;
		public static final int NAME_COLUMN = 1;
		// TODO: Create public field for each column in your table.
		static final String LOGIN_TABLE_CREATE = "create table user"+
		                             "( id integer primary key autoincrement, username text, firstname text," +
		                             "lastname text, password text); ";
		static final String MATCH_TABLE_CREATE = "create table match" +
		                             "( id integer primary key autoincrement, opponent text, round integer); ";
		static final String MATCH_DATA = "insert into match values (1, 'Team A', 1), (2, 'Team B', 2); ";
		// Variable to hold the database instance
		public  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper dbHelper;
		public  LoginDataBaseAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  LoginDataBaseAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertUser(String userName, String firstname, String lastname, String password)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("username", userName);
			newValues.put("firstname", firstname);
			newValues.put("lastname", lastname);
			newValues.put("password",password);

			// Insert the row into your table
			db.insert("user", null, newValues);
		}
		public int deleteUser(String UserName)
		{
		    String where="username=?";
		    int numberOFEntriesDeleted= db.delete("user", where, new String[]{UserName}) ;
	        return numberOFEntriesDeleted;
		}
		
		public boolean userExists(String username)
		{
			Cursor cursor=db.query("user", null, " username=?", new String[]{username}, null, null, null);
	        if(cursor.getCount()<1) 
	        {
	        	cursor.close();
	        	return false;
	        }
	        else
	        	return true;
		}
		
		public String getUserPassword(String userName)
		{
			Cursor cursor=db.query("user", null, " username=?", new String[]{userName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String password= cursor.getString(cursor.getColumnIndex("password"));
			cursor.close();
			return password;
		}
		public void  updateUser(String userName, String firstname, String lastname, String password)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("username", userName);
			updatedValues.put("firstname", firstname);
			updatedValues.put("lastname", lastname);
			updatedValues.put("password",password);

	        String where="username = ?";
		    db.update("user",updatedValues, where, new String[]{userName});			   
		}
		
		public void insertMatch(String opponent, int round)
		{
			ContentValues newValues = new ContentValues();

			newValues.put("opponent", opponent);
			newValues.put("round", round);

			db.insert("match", null, newValues);
		}
		public String[] getMatches()
		{
			String [] matches = {};
			Cursor cursor = db.query("match", null, null, null, null, null, null);
			int i=0;
			for(boolean hasItem = cursor.moveToFirst(); hasItem; hasItem = cursor.moveToNext())
			{
				matches[i] = cursor.getString(cursor.getColumnIndex("opponent"));
				i++;
			}
			return matches;
		}
}