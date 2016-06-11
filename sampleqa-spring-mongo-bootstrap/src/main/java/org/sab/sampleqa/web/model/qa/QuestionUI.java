package org.sab.sampleqa.web.model.qa;

import java.util.Date;
import java.util.List;

import org.sab.sampleqa.web.model.user.UserUI;

public class QuestionUI {
	private String id;
	private String subject;
	private String content;
	private List<AnswerUI> answers;
	private List<CommentUI> comments;
	private List<VoteUI> votes;
	private List<BookmarkUI> bookmarks;
	private List<String> tags;
	private int viewCount;
	private Date createdDate;
	private UserUI createdBy;

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

	public UserUI getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserUI createdBy) {
		this.createdBy = createdBy;
	}

	public List<AnswerUI> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerUI> answers_) {
		answers = answers_;
	}

	public List<CommentUI> getComments() {
		return comments;
	}

	public void setComments(List<CommentUI> comments) {
		this.comments = comments;
	}

	public List<VoteUI> getVotes() {
		return votes;
	}

	public void setVotes(List<VoteUI> votes) {
		this.votes = votes;
	}

	public List<BookmarkUI> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<BookmarkUI> bookmarks) {
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
		return "QuestionUI [id=" + id + ", subject=" + subject + ", content="
				+ content + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", answers=" + answers + ", comments=" + comments
				+ ", votes=" + votes + ", bookmarks=" + bookmarks + ", tags="
				+ tags + ", viewCount=" + viewCount + "]";
	}
}
