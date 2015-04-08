package il.co.onthefly.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FeedEntry {

	static int GlobalID=0;
	
	String id;
	User user;
	String content;
	String type;
	List<String> comments;

	public String getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getContent() {
		return content;
	}

	public String getType() {
		return type;
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

	private void setType(String type) {
		this.type = type;
	}

	private void setComments(List<String> comments) {
		this.comments = comments;
	}

	public FeedEntry(User user, String content, String type) {
		this.id= String.valueOf(GlobalID);
		GlobalID++;
		this.user = user;
		this.content = content;
		this.type = type;
		this.comments= new ArrayList<String>();
	}

	public int getEntryTypeCode() {
		return GlobalID % 7;
	}
	
	//TODO: toString(User user), Parse(String)...

}
