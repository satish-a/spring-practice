package org.sab.sampleqa.common.util.mapper.qa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.sab.sampleqa.common.util.mapper.user.UserMapper;
import org.sab.sampleqa.data.model.qa.Question;
import org.sab.sampleqa.web.model.qa.QuestionUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class QuestionMapper {
	AnswerMapper answerMapper = new AnswerMapper();
	CommentMapper commentmapper = new CommentMapper();
	UserMapper userMapper = new UserMapper();
	VoteMapper voteMapper = new VoteMapper();
	BookmarkMapper bmMapper = new BookmarkMapper();

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public QuestionUI toUIBean(Question pBean) {
		QuestionUI uiBean = null;

		if (pBean != null) {
			uiBean = new QuestionUI();

			if (pBean.getAnswers() != null && pBean.getAnswers().size() > 0) {
				uiBean.setAnswers(answerMapper.toUIBean(pBean.getAnswers()));
			}

			if (pBean.getComments() != null && pBean.getComments().size() > 0) {
				uiBean.setComments(commentmapper.toUIBean(pBean.getComments()));
			}

			if (pBean.getCreatedBy() != null) {
				uiBean.setCreatedBy(userMapper.toUIBean(pBean.getCreatedBy()));
			}

			if (pBean.getVotes() != null) {
				uiBean.setVotes(voteMapper.toUIBean(pBean.getVotes()));
			}

			if (pBean.getBookmarks() != null) {
				uiBean.setBookmarks(bmMapper.toUIBean(pBean.getBookmarks()));
			}

			uiBean.setTags(pBean.getTags());
			uiBean.setContent(pBean.getContent());
			uiBean.setCreatedDate(pBean.getCreatedDate());
			uiBean.setId(pBean.getId());
			uiBean.setSubject(pBean.getSubject());
			uiBean.setViewCount(pBean.getViewCount());
		}

		return uiBean;
	}

	public List<QuestionUI> toUIBean(List<Question> questions) {
		List<QuestionUI> uiBeans = new ArrayList<QuestionUI>();

		for (Question question : questions) {
			uiBeans.add(toUIBean(question));
		}
		return uiBeans;
	}

	public Page<QuestionUI> toUIBean(Page<Question> questions, Pageable pageable) {
		Page<QuestionUI> uiBeans = new PageImpl<QuestionUI>(
				toUIBean(questions.getContent()), pageable,
				questions.getTotalElements());

		return uiBeans;
	}

	public Question toPersistenceBean(QuestionUI uiBean) {
		Question question = null;

		if (uiBean != null) {
			question = new Question();

			if (uiBean.getAnswers() != null && uiBean.getAnswers().size() > 0) {
				question.setAnswers(answerMapper.toPersistenceBeanMap(uiBean
						.getAnswers()));
			}

			if (uiBean.getComments() != null && uiBean.getComments().size() > 0) {
				question.setComments(commentmapper.toPersistenceBeanMap(uiBean
						.getComments()));
			}

			if (uiBean.getCreatedBy() != null) {
				question.setCreatedBy(userMapper.toPersistenceBean(uiBean
						.getCreatedBy()));
			}

			if (uiBean.getVotes() != null) {
				question.setVotes(voteMapper.toPersistenceBeanMap(uiBean
						.getVotes()));
			}

			if (uiBean.getBookmarks() != null) {
				question.setBookmarks(bmMapper.toPersistenceBeanMap(uiBean
						.getBookmarks()));
			}

			question.setTags(uiBean.getTags());
			question.setContent(uiBean.getContent());
			question.setCreatedDate(uiBean.getCreatedDate());
			question.setId(uiBean.getId());
			question.setSubject(uiBean.getSubject());
			question.setViewCount(uiBean.getViewCount());
		}
		return question;
	}

	public List<Question> toPersistenceBean(List<QuestionUI> uiQuestions) {
		List<Question> questions = new ArrayList<Question>();

		for (QuestionUI uiBean : uiQuestions) {
			questions.add(toPersistenceBean(uiBean));
		}
		return questions;
	}

}
