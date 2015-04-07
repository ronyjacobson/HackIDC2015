package il.co.onthefly.db;

public class User {
	
	/* personal Information */
	private static int globalID = 0;
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
	private String destination;
	private String connection;
	

	
	public User(String firstName, String lastName) {
		
		this.id = String.valueOf(globalID);
		globalID++;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String firstName, String lastName, String birthday,
			String country, String email, String phone, String school,
			String degree, String work) {
		this.id = String.valueOf(globalID);
		globalID++;
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
		return firstName+" "+lastName;
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
	
	//TODO: toString(User user), Parse(String)...
	
	
}
