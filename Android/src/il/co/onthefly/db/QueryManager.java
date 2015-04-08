package il.co.onthefly.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class QueryManager {
	
	public String queryDB(String query) {
		StringBuilder resultBuilder;
		String result;
		try {
			URL url = new URL(query);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			try {
				InputStream is = urlConnection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				resultBuilder = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					resultBuilder.append(line);
				}
				result = resultBuilder.toString();
			} finally {
				urlConnection.disconnect();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public ArrayList<FeedEntry> parseFeedResponse(String result) {
		return null;
		
	}
	
	public ArrayList<User> parsePeopleResponse(String result) {
		return null;
		
	}
}
