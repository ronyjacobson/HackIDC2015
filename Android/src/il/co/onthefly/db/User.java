package il.co.onthefly.db;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

	/* personal Information */
	private String facebookID;
	private String id;
	private String firstName;
	private String lastName;
	private String birthday;
	private String age;
	private String country;
	private String email;
	private String phone;
	private String school;
	private String degree;
	private String work;

	/* Flight Information */
	private String flightNum;
	private String fromAirport;
	private String destinationAirport;
	private String connectionAirport;

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
	
	public String getAge() {
		return age;
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

	public String getFacebookID() {
		return facebookID;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public String getFromAirport() {
		return fromAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
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
		this.age= "25"; //= TODO
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

	private void setFromAirport(String from) {
		this.fromAirport= from;
		
	}

	private void setDestinationAirport(String destination) {
		this.destinationAirport= destination;
		
	}

	private void setConnectionAirport(String connection) {
		this.connectionAirport = connection;
		
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
				user.setFromAirport(json.getString("from"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setDestinationAirport(json.getString("destination"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				user.setConnectionAirport(json.getString("connection"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}

	public int getDetailCode(int i) {
		//TODO
		Random r = new Random();
		return r.nextInt(6);
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
