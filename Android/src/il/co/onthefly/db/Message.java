package il.co.onthefly.db;

public class Message {

	/* personal Information */
	private User user;
	private String userImage;
	private boolean sentMessage;
	private String text;
	private String time;

	public Message(User user, String userImage, boolean sentMessage,
			String text, String time) {
		this.user = user;
		this.userImage = userImage;
		this.sentMessage = sentMessage;
		this.text = text;
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public boolean isSentMessage() {
		return sentMessage;
	}

	public void setSentMessage(boolean sentMessage) {
		this.sentMessage = sentMessage;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
