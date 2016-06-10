package org.sab.sampleqa.web.model.qa;

import java.util.Date;
import java.util.List;

import org.sab.sampleqa.web.model.user.UserUI;

public class TagUI {
	private String id;
	private String name;
	private Date createdDate;
	private UserUI createdBy;
	List<String> questions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UserUI getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserUI createdBy) {
		this.createdBy = createdBy;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "TagUI [id=" + id + ", name=" + name + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", questions="
				+ questions + "]";
	}
}
