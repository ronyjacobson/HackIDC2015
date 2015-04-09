package il.co.onthefly.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class QueryManager extends AsyncTask<String, Void, String> {
	public boolean DB = true;
	public String result;
	public AsyncResponse delegate = null;
	public boolean expectResolt;

	public QueryManager(AsyncResponse delegate) {
		this.delegate = delegate;
	};

	@SuppressWarnings("finally")
	public String sendQueryToDB(String query) {
		StringBuilder resultBuilder;
		String result = "";
		try {
			URL url = new URL(query);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			try {
				InputStream is = urlConnection.getInputStream();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				resultBuilder = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					resultBuilder.append(line);
				}
				result = resultBuilder.toString();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				urlConnection.disconnect();
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public ArrayList<FeedEntry> parseFeedResponse(String result) {
		ArrayList<FeedEntry> feedEntrys = new ArrayList<FeedEntry>();
		FeedEntry currentFeed;
		try {
			JSONArray jsonArr = new JSONArray(result);
			int numOfItems = jsonArr.length();
			JSONObject json;
			for (int i = 0; i < numOfItems; i++) {
				json = jsonArr.getJSONObject(i);
				currentFeed = FeedEntry.parseJsonToFeedItem(json);
				feedEntrys.add(currentFeed);
			}
			return feedEntrys;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return feedEntrys;
	}

	public ArrayList<User> parsePeopleResponse(String result) {
		ArrayList<User> users = new ArrayList<User>();
		User currentUser;
		try {
			JSONArray jsonArr = new JSONArray(result);
			int numOfItems = jsonArr.length();
			JSONObject json;
			for (int i = 0; i < numOfItems; i++) {
				json = jsonArr.getJSONObject(i);
				currentUser = User.parseJsonToUserItem(json);
				users.add(currentUser);
			}
			return users;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	protected String doInBackground(String... urls) {
		return result = sendQueryToDB(urls[0]);
	}

	@Override
	protected void onPostExecute(String result) {
		if (expectResolt) {
			delegate.processFinish(result);
		}
	}
}
