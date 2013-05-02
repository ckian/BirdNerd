package com.existingsqlitedatabase;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ImageView;

public class QueryResult extends ListActivity {

//	private SimpleCursorAdapter queryAdapter;

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
		String[] columns = new String[] { /*"_id",*/ "bird_name", "_id" };
		// the XML defined views which the data will be bound to
		int[] to = new int[] { /*R.id.bird_id,*/ R.id.bird_name, R.id.bird_thumb };

		// create the adapter using the cursor pointing to the desired data as well as the layout information
		SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.query_list_entry, data, columns, to);

		// set this adapter as your ListActivity's adapter
//		this.setListAdapter(mAdapter); #############THIS NEEDS TO BE FIXED!!
		mAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
			@Override
			public boolean setViewValue (View view, Cursor cursor, int columnIndex){
				if (view.getId() == R.id.bird_thumb) {
					ImageView IV=(ImageView) view;
					int resID = getApplicationContext().getResources().getIdentifier("full_" + cursor.getString(columnIndex) + ".jpeg", "drawable",  getApplicationContext().getPackageName());
					IV.setImageDrawable(getApplicationContext().getResources().getDrawable(resID));
					return true;
				}
				return false;
			}
		});
	}
}