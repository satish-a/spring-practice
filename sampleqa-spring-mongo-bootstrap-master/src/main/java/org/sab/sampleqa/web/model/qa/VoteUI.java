package org.sab.sampleqa.web.model.qa;

import java.util.Date;

public class VoteUI {
	String id;
	String user;
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

	@Override
	public String toString() {
		return "Vote [id=" + id + ", user=" + user + ", createdDate="
				+ createdDate + "]";
	}
}
