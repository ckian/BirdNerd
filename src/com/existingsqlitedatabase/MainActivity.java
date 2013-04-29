package com.existingsqlitedatabase;

import com.existingsqlitedatabase.R;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private Spinner colorSpin, locationSpin, shapeSpin, sizeSpin, billSpin, wingSpin, tailSpin, irisSpin;
	private Button btn_query;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        colorSpin = (Spinner)findViewById(R.id.spinner_color);
        locationSpin = (Spinner)findViewById(R.id.spinner_location);
        shapeSpin = (Spinner)findViewById(R.id.spinner_shape);
        sizeSpin = (Spinner)findViewById(R.id.spinner_size);
        billSpin = (Spinner)findViewById(R.id.spinner_bill);
        wingSpin = (Spinner)findViewById(R.id.spinner_wing);
        tailSpin = (Spinner)findViewById(R.id.spinner_tail);
        irisSpin = (Spinner)findViewById(R.id.spinner_iris);
        
        initDB();
        
        fillSpinner("color", colorSpin);
        fillSpinner("location", locationSpin);
        fillSpinner("shape", shapeSpin);
        fillSpinner("size", sizeSpin);
        fillSpinner("bill", billSpin);
        fillSpinner("wing", wingSpin);
        fillSpinner("tail", tailSpin);
        fillSpinner("iris", irisSpin);
        
        
        btn_query = (Button) findViewById(R.id.btn_query);
        
//        final Button btnQuery = (Button) findViewById(R.id.btn_query);
//
//        btnQuery.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
////            	String colorArg = colorSpin.getSelectedItem().toString();
////            	getQuery(colorArg);
//            }
//        });        
    }

    public void addListenerOnButton() {
    	 
    	colorSpin = (Spinner) findViewById(R.id.spinner_color);
    	btn_query = (Button) findViewById(R.id.btn_query);
     
    	btn_query.setOnClickListener(new View.OnClickListener() {
     
    	  @Override
    	  public void onClick(View v) {
    		  
    		  String h = String.valueOf(colorSpin.getSelectedItem());
    		  
    		  Utility.ShowMessageBox(MainActivity.this, h);
//    	    Toast.makeText(MyAndroidAppActivity.this,
//    		"OnClickListener : " + 
//                    "\nSpinner 1 : "+ String.valueOf(colorSpin.getSelectedItem())
//    			Toast.LENGTH_SHORT).show();
    	}
    	});
    }
    public void SearchBirds(View v)
    {
    	Spinner color = (Spinner)findViewById(R.id.spinner_color);
    	
    	int position = color.getSelectedItemPosition(); 
//    	Cursor cursor = (Cursor) adapter.getItem(position);
//    	String myText = cursor.getString(cursor.getColumnIndex("color_name"));
    	
    	
    	
        String c = color.getSelectedItem().toString();
        Utility.ShowMessageBox(this, c + " , " + position);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private void initDB(){
    	TestAdapter mDbHelper = new TestAdapter(this);         
    	mDbHelper.createDatabase();       
    	
    }
    
	@SuppressWarnings("deprecation")
	private void fillSpinner(String table, Spinner spin) {
		//Spinner speciesSpinner = (Spinner)findViewById(R.id.spinner_wing);
//		TestAdapter mDbHelper = new TestAdapter(this);
//		Cursor wingCursor = mDbHelper.getRows("wing");
		
    	TestAdapter mDbHelper = new TestAdapter(this);         
//    	mDbHelper.createDatabase();       
    	mDbHelper.open(); 
    	
    	Cursor data = mDbHelper.getRows(table);
    	startManagingCursor(data);

		// create an array to specify which fields we want to display
		String[] from = new String[] {table + "_name"};
//		// create an array of the display item we want to bind our data to
		int[] to = new int[] { android.R.id.text1 };
//		// create simple cursor adapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, data, from, to);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		// get reference to our spinner
//		Spinner s = (Spinner) findViewById(R.id.spinner_wing);
		spin.setAdapter(adapter);
	}
    
//	public void getQuery(String s)
//	{
//		Utility.ShowMessageBox(this, s);
//
//	}
	
	
//    public void LoadEmployee(View v)
//    {
//    	TestAdapter mDbHelper = new TestAdapter(this);         
//    	mDbHelper.createDatabase();       
//    	mDbHelper.open(); 
//    	 
//    	Cursor testdata = mDbHelper.getTestData(); 
//    	 
//    	String name = Utility.GetColumnValue(testdata, "bird_name");
////    	String email = Utility.GetColumnValue(testdata, "Email");
//    	
//    	Utility.ShowMessageBox(this, "Name: "+ name + " and Email: ");
//    	mDbHelper.close();
//    }
    
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
