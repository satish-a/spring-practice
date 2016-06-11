package org.sab.sampleqa.data.model.qa;

import java.util.Date;
import java.util.List;

import org.sab.sampleqa.data.model.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Tag {
	@Id
	private String id;

	private String name;
	private int questionCount;
	private List<String> questions;
	private Date createdDate;
	private User createdBy;

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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", questionCount="
				+ questionCount + ", questions=" + questions + "]";
	}
}
