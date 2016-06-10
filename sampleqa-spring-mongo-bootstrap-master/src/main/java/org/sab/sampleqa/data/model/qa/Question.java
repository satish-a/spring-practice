package org.sab.sampleqa.data.model.qa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.sab.sampleqa.data.model.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Question {
	@Id
	private String id;
	private String subject;
	private String content;

	private Map<String, Answer> answers;
	private Map<String, Comment> comments;
	private Map<String, Vote> votes;
	private Map<String, Bookmark> bookmarks;
	private List<String> tags;
	private int viewCount;

	@DBRef
	private User createdBy;
	private Date createdDate;

	public Question() {

	}

	public Question(String subject, String content) {
		this.subject = subject;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id_) {
		id = id_;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject_) {
		subject = subject_;
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

	public Map<String, Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<String, Answer> answers) {
		this.answers = answers;
	}

	public Map<String, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<String, Comment> comments) {
		this.comments = comments;
	}

	public Map<String, Vote> getVotes() {
		return votes;
	}

	public void setVotes(Map<String, Vote> votes) {
		this.votes = votes;
	}

	public Map<String, Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(Map<String, Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", subject=" + subject + ", content="
				+ content + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", answers=" + answers + ", comments=" + comments
				+ ", votes=" + votes + ", bookmarks=" + bookmarks + ", tags="
				+ tags + ", viewCount=" + viewCount + "]";
	}
}
