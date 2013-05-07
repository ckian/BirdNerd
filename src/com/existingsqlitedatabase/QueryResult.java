package com.existingsqlitedatabase;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class QueryResult extends ListActivity {

//	private SimpleCursorAdapter queryAdapter;
	public SimpleCursorAdapter mAdapter;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_list_view);

		Bundle extras = getIntent().getExtras();
		String birdQuery = extras.getString("birdQuery");

		TestAdapter mDbHelper = new TestAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open(); 

		Cursor data = mDbHelper.getQueryList(birdQuery);
		startManagingCursor(data);

		// the desired columns to be bound
		String[] columns = new String[] { "_id", "bird_name" };
		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.bird_thumb, R.id.bird_name };

		// create the adapter using the cursor pointing to the desired data as well as the layout information
//		SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.query_list_entry, data, columns, to);

		// set this adapter as your ListActivity's adapter
//		this.setListAdapter(mAdapter); #############THIS NEEDS TO BE FIXED, NullPointerException!!!!
		mAdapter = new SimpleCursorAdapter(this, R.layout.query_list_entry, data, columns, to);
		mAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
			@Override
			public boolean setViewValue (View view, Cursor cursor, int columnIndex){
				if (view.getId() == R.id.bird_thumb) {
					ImageView IV=(ImageView) view;
//					int resID = getApplicationContext().getResources().getIdentifier("file_" + cursor.getString(columnIndex), "drawable",  getApplicationContext().getPackageName());
					int resID = getApplicationContext().getResources().getIdentifier("com.existingsqlitedatabase:drawable/full_" + cursor.getString(columnIndex), null, null);
					IV.setImageDrawable(getApplicationContext().getResources().getDrawable(resID));
//					Utility.ShowMessageBox(getApplicationContext(), "returned true");
					return true;
				}
//				Utility.ShowMessageBox(getApplicationContext(), "returned false");
				return false;
			}
		});
		
		this.setListAdapter(mAdapter);
        ListView lv = getListView();
        
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
               
              // selected item
        	  Cursor cursor = ((SimpleCursorAdapter) parent.getAdapter()).getCursor();
        	  cursor.moveToPosition(position);
        	  
        	  String val = cursor.getString( cursor.getColumnIndex("_id") );

        	  cursor.close();
        	  
            Intent intent = new Intent(getBaseContext(), SingleListItem.class);
      		intent.putExtra("ID", val);
      		startActivity(intent);
             
          }
        });
		
	}
}