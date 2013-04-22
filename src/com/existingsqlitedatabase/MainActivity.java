package com.existingsqlitedatabase;

import com.existingsqlitedatabase.R;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner s = (Spinner)findViewById(R.id.spinner_wing);
        fillSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
// FIX THIS, 
	private void fillSpinner() {
		//Spinner speciesSpinner = (Spinner)findViewById(R.id.spinner_wing);
//		TestAdapter mDbHelper = new TestAdapter(this);
//		Cursor wingCursor = mDbHelper.getRows("wing");
		
    	TestAdapter mDbHelper = new TestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open(); 
    	
    	Cursor data = mDbHelper.getRows("wing");
//////*EVERYTHING AFTER THIS COULD BE THE REASON THE PROGRAM DOESN'T WORK!!!!!*///	
    	startManagingCursor(data);
//
//		// create an array to specify which fields we want to display
		String[] from = new String[] {"wing_name"};
//		// create an array of the display item we want to bind our data to
		int[] to = new int[] { android.R.id.text1 };
//		// create simple cursor adapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, data, from, to);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		// get reference to our spinner
		Spinner s = (Spinner) findViewById(R.id.spinner_wing);
		s.setAdapter(adapter);
		

	}
    
    public void LoadEmployee(View v)
    {
    	TestAdapter mDbHelper = new TestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open(); 
    	 
    	Cursor testdata = mDbHelper.getTestData(); 
    	 
    	String name = Utility.GetColumnValue(testdata, "bird_name");
//    	String email = Utility.GetColumnValue(testdata, "Email");
    	
    	Utility.ShowMessageBox(this, "Name: "+ name + " and Email: ");
    	mDbHelper.close();
    }
    
//  public void SaveEmployee(View v)
//  {
//  	EditText txtName = (EditText)findViewById(R.id.txtName);
//  	EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
//  	
//  	String name = txtName.getText().toString();
//  	String email = txtEmail.getText().toString();
//  	
//  	
//  	TestAdapter mDbHelper = new TestAdapter(this);         
//  	mDbHelper.createDatabase();       
//  	mDbHelper.open(); 
//  	
//  	if(mDbHelper.SaveEmployee(name, email))
//  	{
//  		Utility.ShowMessageBox(this,"Data saved.");
//  	}
//  	else
//  	{
//  		Utility.ShowMessageBox(this,"OOPS try again!");
//  	}
//  }
}
