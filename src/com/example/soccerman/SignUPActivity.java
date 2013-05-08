package com.example.soccerman;

import com.example.soccerman.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPActivity extends Activity
{
	EditText editTextUserName,editTextFirstname,editTextLastname,editTextPassword,editTextConfirmPassword;
	Button btnCreateAccount;

	LoginDataBaseAdapter loginDataBaseAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		// get Instance  of Database Adapter
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();

		// Get References of Views
		editTextUserName=(EditText)findViewById(R.id.editTextUserName);
		editTextFirstname=(EditText)findViewById(R.id.editTextFirstname);
		editTextLastname=(EditText)findViewById(R.id.editTextLastname);
		editTextPassword=(EditText)findViewById(R.id.editTextPassword);
		editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);

		btnCreateAccount=(Button)findViewById(R.id.buttonCreateAccount);
		btnCreateAccount.setOnClickListener(new View.OnClickListener() {

		public void onClick(View v) 
		{
			String userName=editTextUserName.getText().toString();
			String firstname = editTextFirstname.getText().toString();
			firstname = Character.toUpperCase(firstname.charAt(0)) + firstname.substring(1);
			String lastname = editTextLastname.getText().toString();
			lastname = Character.toUpperCase(lastname.charAt(0)) + lastname.substring(1);
			String password=editTextPassword.getText().toString();
			String confirmPassword=editTextConfirmPassword.getText().toString();

			// check if any of the fields are vacant
			if(userName.equals("")||firstname.equals("")||lastname.equals("")||password.equals("")
					||confirmPassword.equals(""))
			{
					Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
					return;
			}
			// check username isn't already taken
			if(loginDataBaseAdapter.userExists(userName))
			{
				Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
				return;
			}
			// check if both password matches
			if(!password.equals(confirmPassword))
			{
				Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
				return;
			}
			else
			{
			    // Save the Data in Database
			    loginDataBaseAdapter.insertUser(userName, firstname, lastname, password);
			    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
			    Intent main = new Intent(SignUPActivity.this, HomeActivity.class);
			    startActivity(main);
			}
		}
	});
}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		loginDataBaseAdapter.close();
	}
}