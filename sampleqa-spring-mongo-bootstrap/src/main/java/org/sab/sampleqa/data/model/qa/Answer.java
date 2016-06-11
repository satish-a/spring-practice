package org.sab.sampleqa.data.model.qa;

import java.util.Date;
import java.util.Map;

import org.sab.sampleqa.data.model.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Answer {
	@Id
	private String id;

	private String content;
	private Map<String, Comment> comments;

	@DBRef
	private User createdBy;
	private Date createdDate;
	private Date editedDate;

	public Answer() {

	}

	public Answer(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id_) {
		id = id_;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content_) {
		content = content_;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate_) {
		createdDate = createdDate_;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy_) {
		createdBy = createdBy_;
	}

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate_) {
		editedDate = editedDate_;
	}

	public Map<String, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<String, Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", content=" + content + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", editedDate="
				+ editedDate + ", comments=" + comments + "]";
	}
}
