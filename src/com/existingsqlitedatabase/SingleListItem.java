package com.existingsqlitedatabase;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SingleListItem extends ListActivity{

	public SimpleCursorAdapter mAdapter;
	private Button reset;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_view);

		Bundle extras = getIntent().getExtras();
		int position = Integer.parseInt(extras.getString("ID"));

		TestAdapter mDbHelper = new TestAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open(); 

		Cursor data = mDbHelper.getFullInfo(position);
		startManagingCursor(data);

		// the desired columns to be bound
		String[] columns = new String[] { "_id", "bird_name", "bird_info",
				"length_range", "weight", "wingspan", "nesting", "foraging",
				"vocalization", "similar_species", "breeding_location",
				"breeding_type", "conservation", "egg_color", "nest_material",
				"nest_location", "migration", "latin_name" };
		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.bird_picture, R.id.bird_name,
				R.id.length_range, R.id.weight, R.id.wingspan, R.id.nesting, R.id.foraging,
				R.id.vocalization, R.id.similar_species, R.id.breeding_location,
				R.id.breeding_type, R.id.conservation, R.id.egg_color, R.id.nest_material,
				R.id.nest_location, R.id.migration, R.id.latin_name  };
		
		mAdapter = new SimpleCursorAdapter(this, R.layout.result_view, data, columns, to);
		mAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
			@Override
			public boolean setViewValue (View view, Cursor cursor, int columnIndex){
				if (view.getId() == R.id.bird_picture) {
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
//		ListView lv=getListView();
//		lv.setDivider(this.getResources().getDrawable(R.drawable.div_color));
//		lv.setDividerHeight(9);
		
        reset = (Button) findViewById(R.id.new_search_button);

      reset.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
        	  startNewSearch();
          }
      });
		
	}

	public void startNewSearch() {

		Intent intent = new Intent(getBaseContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
