package il.co.onthefly.db;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

	/* personal Information */
	private String facebookID;
	private String id;
	private String firstName;
	private String lastName;
	private String birthday;
	private String country;
	private String email;
	private String phone;
	private String school;
	private String degree;
	private String work;

	/* Flight Information */
	private String flightNum;
	private String from;
	private String destination;
	private String connection;

	public User() {
	}

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String firstName, String lastName, String birthday,
			String country, String email, String phone, String school,
			String degree, String work) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.school = school;
		this.degree = degree;
		this.work = work;
	}

	public String getId() {
		return id;
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

	public String getDegree() {
		return degree;
	}

	public String getWork() {
		return work;
	}

	private void setId(String id) {
		this.id = id;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private void setBirthday(String birthday) {
		this.birthday = birthday;
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
	
	private void setFlightNum(String flightNum) {
		this.flightNum=flightNum;;
		
	}

	private void setFrom(String from) {
		this.from= from;
		
	}

	private void setDestination(String destination) {
		this.destination= destination;
		
	}

	private void setConnection(String connection) {
		this.connection = connection;
		
	}

	private User parseJsonToUser(String jsonStr) {
		User user = new User();
		try {
			JSONObject json = new JSONObject(jsonStr);
			try {
				/** Obligatory Data **/
				user.setId(json.getString("id"));
				user.setFirstName(json.getString("firstName"));
				user.setLastName(json.getString("lastName"));
			} catch (JSONException e) {
				e.printStackTrace();
			}

			/** Non Obligatory Data **/
			try {
				user.setBirthday(json.getString("birthday"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setFacebookID(json.getString("facebookID"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setCountry(json.getString("country"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setEmail(json.getString("email"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setPhone(json.getString("phone"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setSchool(json.getString("school"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setDegree(json.getString("degree"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setWork(json.getString("work"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setFlightNum(json.getString("flightNum"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setFrom(json.getString("from"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setDestination(json.getString("destination"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setConnection(json.getString("connection"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}



	/*
	 * public void writeUserToJSON() { JSONObject object = new JSONObject(); try
	 * { object.put("firstName", this.getFirstName()); object.put("firstName",
	 * this.getFirstName()); object.put("firstName", this.getFirstName());
	 * object.put("firstName", this.getFirstName()); object.put("firstName",
	 * this.getFirstName()); object.put("firstName", this.getFirstName());
	 * object.put("firstName", this.getFirstName());
	 * 
	 * private String facebookID; private String id; private String firstName;
	 * private String lastName; private String birthday; private String country;
	 * private String email; private String phone; private String school;
	 * private String degree; private String work; private String flightNum;
	 * private String from; private String destination; private String
	 * connection; } catch (JSONException e) { e.printStackTrace(); }
	 * System.out.println(object); }
	 */

	// TODO: toString(User user)

}
