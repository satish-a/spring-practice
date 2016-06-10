package org.sab.sampleqa.web.model.qa;

import java.util.Date;
import java.util.List;

import org.sab.sampleqa.web.model.user.UserUI;

public class AnswerUI {
	private String id;
	private String content;
	private String questionId;
	private List<CommentUI> comments;
	private Date createdDate;
	private UserUI createdBy;
	private Date editedDate;

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

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate_) {
		editedDate = editedDate_;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public List<CommentUI> getComments() {
		return comments;
	}

	public void setComments(List<CommentUI> comments) {
		this.comments = comments;
	}

	public UserUI getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserUI createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "AnswerUI [id=" + id + ", content=" + content + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", editedDate="
				+ editedDate + ", questionId=" + questionId + ", comments="
				+ comments + "]";
	}
}
