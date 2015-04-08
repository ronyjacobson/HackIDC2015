package il.co.onthefly.db;

import il.co.onthefly.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedEntry {

	static int entryTypeCode = -1;

	String id;
	String userId;
	String userImage;
	String userName;
	String timePosted;
	User user;
	String content;
	String typeCode;
	List<String> comments;

	public FeedEntry() {
		this.comments = new ArrayList<String>();
	}

	public String getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getContent() {
		return content;
	}

	public List<String> getComments() {
		return comments;
	}

	private void setId(String id) {
		this.id = id;
	}

	private void setUser(User user) {
		this.user = user;
	}

	private void setContent(String content) {
		this.content = content;
	}

	private void setComments(List<String> comments) {
		this.comments = comments;
	}

	public FeedEntry(User user, String content) {
		this.user = user;
		this.content = content;
		this.comments = new ArrayList<String>();
	}

	public int getEntryTypeCode() {
		entryTypeCode++;
		return entryTypeCode % 7;
	}

	public static FeedEntry parseJsonToFeedItem(JSONObject json) {
		FeedEntry fe = new FeedEntry();

		try {
			fe.setUserId(json.getString("Id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			fe.setUserImage(json.getString("img"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			fe.setTimePosted(json.getString("time"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			fe.setUserName(json.getString("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			fe.setContent(json.getString("status"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			fe.setTypeCode(json.getString("type"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return fe;
	}

	private void setTypeCode(String typeCode) {
		this.typeCode = typeCode;

	}

	private void setUserName(String userName) {
		this.userName = userName;

	}

	private void setTimePosted(String timePosted) {
		this.timePosted = timePosted;

	}

	private void setUserImage(String userImage) {
		this.userImage = userImage;

	}

	private void setUserId(String userId) {
		this.userId = userId;

	}

	public CharSequence getUserName() {
		if (this.user == null) {
			return this.userName;
		} else {
			return this.user.getFirstName();
		}
	}

	// TODO: toString(User user)

}
