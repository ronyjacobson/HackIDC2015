package il.co.onthefly.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FeedEntry {

	static int entryTypeCode = -1;
	
	String id;
	User user;
	String content;
	
	
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
		this.comments= new ArrayList<String>();
	}

	public int getEntryTypeCode() {
		entryTypeCode++;
		return entryTypeCode % 7;
	}
	
	//TODO: toString(User user), Parse(String)...

}
