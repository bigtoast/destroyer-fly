package com.bigtoast.destroyerfly;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;

public class EventListActivity extends ListActivity {

	final private EventService service = new RestEventService();
	
	public String[] getEventNames(){
		String[] eventNames = new String[0];
		
		try {
		  JSONArray events = service.getUpcomingEvents().getJSONArray("events");
		  eventNames = new String[events.length() + 1];
		  
		  for ( int i = 0; i < events.length(); i++ ){
			JSONObject event = events.getJSONObject(i);
			eventNames[i] = event.getString("name");
		  }
		  eventNames[events.length()] = "andyandy";
		  
		} catch ( JSONException ex ){
			Log.e(EventListActivity.class.getName(), "Exception", ex);
			ex.printStackTrace();
		}
		  
		return eventNames;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.event_list, getEventNames());
		setListAdapter(adapter);
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		      // When clicked, show a toast with the TextView text
		      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
		          Toast.LENGTH_SHORT).show();
		    }
		  });
		
	}

}
