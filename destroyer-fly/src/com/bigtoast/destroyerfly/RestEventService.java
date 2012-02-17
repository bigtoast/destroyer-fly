package com.bigtoast.destroyerfly;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RestEventService implements EventService {
	
	// tfly test account
	final static String UPCOMING_URI = "http://www.ticketfly.com/api/events/upcoming.json?orgId=6";
	final static String VENUES_URI   = "http://www.ticketfly.com/api/venues.json?orgId=6";
	final static String ORG_URI      = "http://www.ticketfly.com/api/orgs.json?orgId=6";

	public JSONObject getUpcomingEvents() {
		return read(UPCOMING_URI);
	}
	
	public JSONObject read(String uri) {
		StringBuilder builder = new StringBuilder();
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(uri);
		
		try {
			HttpResponse response = client.execute(get);
			StatusLine status = response.getStatusLine();
			int code = status.getStatusCode();
			if ( code == 200 ) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader( new InputStreamReader(content) );
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(RestEventService.class.toString(), "Problem getting uri: " + uri + " status code: " + code);
			}
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject();
		
		try {
		    obj = new JSONObject(builder.toString());
		} catch ( JSONException ex ) {
			ex.printStackTrace();
		}
		
		return obj;
	}

}
