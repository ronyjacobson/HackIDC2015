package il.co.onthefly.db;

import il.co.onthefly.LoginActivity;

import java.util.HashSet;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

	/* personal Information */
	private String facebookID;
	private String userImage;
	private String id;
	private String firstName;
	private String lastName;
	private String birthday;
	private String age;
	private String watingTime;
	private String kids;
	private String country;
	private String email;
	private String phone;
	private String school;
	private String degree;
	private String work;
	private HashSet<Integer> detailsTypes = new HashSet<Integer>();

	/* Flight Information */
	private String flightNum;
	private String origin;
	private String destination;
	private String connectionAirport;

	public User() {
	}

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		if (id != null) {
			return id;
		} else {
			return "";
		}
	}

	public boolean withKids() {
		if (kids != null) {
			return (kids.equals("1"));
		} else {
			return false;
		}
	}

	public String getAge() {
		if (age != null) {
			return age;
		} else {
			return "";
		}
	}

	public String getUserImage() {
		if (userImage != null) {
			return userImage;
		} else {
			return "";
		}

	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFirstName() {
		if (firstName != null) {
			return firstName;
		} else {
			return "";
		}
	}

	public String getLastName() {
		if (lastName != null) {
			return lastName;
		} else {
			return "";
		}
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public String getBirthday() {
		if (birthday != null) {
			return birthday;
		} else {
			return "";
		}
	}

	public String getCountry() {
		if (country != null) {
			return country;
		} else {
			return "";
		}
	}

	public String getEmail() {
		if (email != null) {
			return email;
		} else {
			return "";
		}
	}

	public String getPhone() {
		if (phone != null) {
			return phone;
		} else {
			return "";
		}
	}

	public String getSchool() {
		if (school != null) {
			return school;
		} else {
			return "";
		}
	}

	public HashSet<Integer> getDetailsTypes() {
		return detailsTypes;
	}

	public String getDegree() {
		if (degree != null) {
			return degree;
		} else {
			return "";
		}
	}

	public String getWork() {
		if (work != null) {
			return work;
		} else {
			return "";
		}
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getFacebookID() {
		if (facebookID != null) {
			return facebookID;
		} else {
			return "";
		}
	}

	public String getFlightNum() {
		if (flightNum != null) {
			return flightNum;
		} else {
			return "";
		}
	}

	public String getFromAirport() {
		if (origin != null) {
			return origin;
		} else {
			return "";
		}
	}

	public String getDestinationAirport() {
		if (destination != null) {
			return destination;
		} else {
			return "";
		}

	}

	public String getConnectionAirport() {
		if (connectionAirport != null) {
			return connectionAirport;
		} else {
			return "";
		}
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private void setBirthday(String birthday) {
		this.birthday = birthday;
		this.age = "25"; // = TODO
	}

	private void setCountry(String country) {
		this.country = country;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private void setPhone(String phone) {
		this.phone = phone;
	}

	private void setSchool(String school) {
		this.school = school;
	}

	private void setDegree(String degree) {
		this.degree = degree;
	}

	private void setWork(String work) {
		this.work = work;
	}

	private void setFacebookID(String fbID) {
		this.facebookID = fbID;
	}

	private void setUserImage(String img) {
		this.userImage = img;
	}

	private void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
		;

	}

	private void setFromAirport(String from) {
		this.origin = from;

	}

	private void setWaitingTime(String time) {
		this.watingTime = time;

	}

	private void setDestinationAirport(String destination) {
		this.destination = destination;

	}

	private void setConnectionAirport(String connection) {
		this.connectionAirport = connection;

	}

	private void setKids(String string) {
		this.kids = string;

	}

	public User(String facebookID, String userImage, String firstName,
			String lastName, String birthday, String country, String email,
			String school, String degree, String work, String flightNum,
			String origin, String destination) {

		super();
		this.facebookID = facebookID;
		this.userImage = userImage;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.kids = "1";
		this.country = country;
		this.email = email;
		this.school = school;
		this.degree = degree;
		this.work = work;
		this.flightNum = flightNum;
		this.origin = origin;
		this.destination = destination;
		this.watingTime = (new Random().nextInt(16)) + " hours wait!";
	}

	public static User parseJsonToUserItem(JSONObject json) {
		User user = new User();

		try {
			user.setId(json.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setCountry(json.getString("country"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setUserImage(json.getString("img"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setFirstName(json.getString("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setAge(json.getString("age"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setConnectionAirport(json.getString("airport"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setWaitingTime(json.getString("waiting"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setWork(json.getString("work"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setDegree(json.getString("studies"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setKids(json.getString("kids"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			user.setDestinationAirport(json.getString("destination"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		user.setDetails(user);
		return user;
	}

	private void setDetails(User user) {
		if (!user.getDestinationAirport().equals("")) {
			detailsTypes.add(0);
		}
		if (LoginActivity.currentUser.withKids() && user.withKids()) {
			detailsTypes.add(1);
		}
		if (LoginActivity.currentUser.getCountry() == user.getCountry()) {
			detailsTypes.add(2);
		}
		if (LoginActivity.currentUser.getAge() == user.getAge()) {
			detailsTypes.add(3);
		}
		if (!user.getDegree().equals("")) {
			detailsTypes.add(4);
		}
		if (!user.getWork().equals("")) {
			detailsTypes.add(5);
		}
		if (LoginActivity.currentUser.getConnectionAirport() == user.getConnectionAirport()) {
			detailsTypes.add(6);
		}
	}

	public String getWatingTime() {
		if (watingTime != null) {
			return watingTime;
		} else {
			Random r= new Random();
			r.nextInt(16);
			return ((r.nextInt(16)+1)+" hours wait!");
		}
	}

}
