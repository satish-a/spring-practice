package org.sab.sampleqa.data.model.qa;

import java.util.Date;

import org.sab.sampleqa.data.model.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Comment {
	@Id
	private String id;
	private String content;

	@DBRef
	private User createdBy;
	private Date createdDate;
	private Date editedDate;

	public Comment() {

	}

	public Comment(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", editedDate="
				+ editedDate + "]";
	}
}
