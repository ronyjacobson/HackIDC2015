package il.co.onthefly.db;

import il.co.onthefly.LoginActivity;
import il.co.onthefly.R;

import java.util.Collection;
import java.util.Currency;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.widget.ImageView;
import android.widget.TextView;

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
		return id;
	}

	public boolean withKids() {
		return (kids.equals("1"));
	}

	public String getAge() {
		return age;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getCountry() {
		return country;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getSchool() {
		return school;
	}

	public HashSet<Integer> getDetailsTypes() {
		return detailsTypes;
	}

	public String getDegree() {
		return degree;
	}

	public String getWork() {
		return work;
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getFacebookID() {
		return facebookID;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public String getFromAirport() {
		return origin;
	}

	public String getDestinationAirport() {
		return destination;
	}

	public String getConnectionAirport() {
		return connectionAirport;
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
	
	public User(String facebookID, String userImage,
			String firstName, String lastName, String birthday,
			String country, String email, String school, String degree,
			String work, String flightNum, String fromAirport,
			String destinationAirport) {
		super();
		this.facebookID = facebookID;
		this.userImage = userImage;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.kids = "0";
		this.country = country;
		this.email = email;
		this.school = school;
		this.degree = degree;
		this.work = work;
		this.flightNum = flightNum;
		this.origin = fromAirport;
		this.destination = destinationAirport;
	}

	public User parseJsonToUserItem(String jsonStr) {
		User user = new User();

		try {
			JSONObject json = new JSONObject(jsonStr);
			try {
				user.setId(json.getString("id"));
				user.setCountry(json.getString("country"));
				user.setUserImage(json.getString("img"));
				user.setFirstName(json.getString("name"));
				user.setAge(json.getString("age"));
				user.setConnectionAirport(json.getString("airport"));
				user.setWaitingTime(json.getString("waiting"));
				user.setWork(json.getString("work"));
				user.setDegree(json.getString("studies"));
				user.setKids(json.getString("kids"));
				user.setDestinationAirport(json.getString("destination"));
				LoginActivity.currentUser.setDetails(user); 
				return user;

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

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
		if (LoginActivity.currentUser.getDegree() == user.getDegree()) {
			detailsTypes.add(4);
		}
		if (LoginActivity.currentUser.getWork() == user.getWork()) {
			detailsTypes.add(5);
		}
		if (LoginActivity.currentUser.getConnectionAirport() == user
				.getConnectionAirport()) {
			detailsTypes.add(6);
		}
		

	}

}
