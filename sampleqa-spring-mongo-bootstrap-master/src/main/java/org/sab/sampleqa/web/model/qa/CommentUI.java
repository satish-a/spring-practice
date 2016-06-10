package org.sab.sampleqa.web.model.qa;

import java.util.Date;

import org.sab.sampleqa.web.model.user.UserUI;

public class CommentUI {
	private String id;
	private String content;
	private String questionId;
	private String answerId;
	private Date createdDate;
	private UserUI createdBy;
	private Date editedDate;

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

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public UserUI getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserUI createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "CommentUI [id=" + id + ", content=" + content
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", editedDate=" + editedDate + ", questionId=" + questionId
				+ ", answerId=" + answerId + "]";
	}
}
