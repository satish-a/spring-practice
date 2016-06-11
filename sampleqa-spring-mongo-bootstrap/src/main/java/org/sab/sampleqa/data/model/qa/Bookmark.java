package org.sab.sampleqa.data.model.qa;

import java.util.Date;

public class Bookmark {
	String id;
	String user;
	String questionId;
	Date createdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "Bookmark [id=" + id + ", user=" + user + ", questionId="
				+ questionId + ", createdDate=" + createdDate + "]";
	}
}
