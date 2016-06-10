package org.sab.sampleqa.data.model.user;

import java.util.Map;

import org.sab.sampleqa.data.model.qa.Bookmark;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	@Id
	private String id;

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String country;
	private String countryState;
	private String comments;
	Map<String, Bookmark> bookmars;

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryState() {
		return countryState;
	}

	public void setCountryState(String countryState) {
		this.countryState = countryState;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Map<String, Bookmark> getBookmars() {
		return bookmars;
	}

	public void setBookmars(Map<String, Bookmark> bookmars) {
		this.bookmars = bookmars;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userName=" + userName + ", password="
				+ password + ", country=" + country + ", countryState="
				+ countryState + ", comments=" + comments + ", bookmars="
				+ bookmars + "]";
	}
}
