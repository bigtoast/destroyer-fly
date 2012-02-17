package com.bigtoast.destroyerfly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class DestroyerFlyActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		((Button) findViewById(R.id.event_list))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						Intent myIntent = new Intent(view.getContext(),
								EventListActivity.class);
						startActivityForResult(myIntent, 0);
					}
				});

		((Button) findViewById(R.id.venue_list))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						Intent myIntent = new Intent(view.getContext(),
								EventListActivity.class);
						startActivityForResult(myIntent, 0);
					}
				});

	}
}