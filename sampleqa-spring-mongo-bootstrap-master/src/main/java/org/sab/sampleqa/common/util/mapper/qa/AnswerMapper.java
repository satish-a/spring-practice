package org.sab.sampleqa.common.util.mapper.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sab.sampleqa.common.util.mapper.user.UserMapper;
import org.sab.sampleqa.data.model.qa.Answer;
import org.sab.sampleqa.web.model.qa.AnswerUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class AnswerMapper {
	CommentMapper commentmapper = new CommentMapper();
	UserMapper userMapper = new UserMapper();

	public AnswerUI toUIBean(Answer pBean) {
		AnswerUI uiBean = null;

		if (pBean != null) {
			uiBean = new AnswerUI();
			uiBean.setContent(pBean.getContent());
			uiBean.setCreatedDate(pBean.getCreatedDate());
			uiBean.setEditedDate(pBean.getEditedDate());
			uiBean.setId(pBean.getId());

			if (pBean.getCreatedBy() != null) {
				uiBean.setCreatedBy(userMapper.toUIBean(pBean.getCreatedBy()));
			}

			if (pBean.getComments() != null && pBean.getComments().size() > 0) {
				uiBean.setComments(commentmapper.toUIBean(pBean.getComments()));
			}
		}

		return uiBean;
	}

	public List<AnswerUI> toUIBean(List<Answer> answers) {
		List<AnswerUI> uiBeans = new ArrayList<AnswerUI>();

		for (Answer answer : answers) {
			uiBeans.add(toUIBean(answer));
		}
		return uiBeans;
	}

	public List<AnswerUI> toUIBean(Map<String, Answer> answers) {
		List<AnswerUI> uiBeans = new ArrayList<AnswerUI>();

		for (String key : answers.keySet()) {
			AnswerUI answer = toUIBean(answers.get(key));
			answer.setId(key);
			uiBeans.add(answer);
		}
		return uiBeans;
	}

	public Page<AnswerUI> toUIBean(Page<Answer> answers, Pageable pageable) {
		Page<AnswerUI> uiBeans = new PageImpl<AnswerUI>(
				toUIBean(answers.getContent()), pageable,
				answers.getTotalElements());

		return uiBeans;
	}

	public Answer toPersistenceBean(AnswerUI uiBean) {
		Answer answer = null;

		if (uiBean != null) {
			answer = new Answer();
			answer.setContent(uiBean.getContent());
			answer.setCreatedDate(uiBean.getCreatedDate());
			answer.setEditedDate(uiBean.getEditedDate());
			answer.setId(uiBean.getId());

			if (uiBean.getCreatedBy() != null) {
				answer.setCreatedBy(userMapper.toPersistenceBean(uiBean
						.getCreatedBy()));
			}

			if (uiBean.getComments() != null && uiBean.getComments().size() > 0) {
				answer.setComments(commentmapper.toPersistenceBeanMap(uiBean
						.getComments()));
			}
		}
		return answer;
	}

	public List<Answer> toPersistenceBean(List<AnswerUI> uiAnswers) {
		List<Answer> answers = new ArrayList<Answer>();

		for (AnswerUI uiBean : uiAnswers) {
			answers.add(toPersistenceBean(uiBean));
		}
		return answers;
	}

	public Map<String, Answer> toPersistenceBeanMap(List<AnswerUI> uiAnswers) {
		Map<String, Answer> answers = new HashMap<String, Answer>();

		for (AnswerUI uiBean : uiAnswers) {
			answers.put(uiBean.getId(), toPersistenceBean(uiBean));
		}
		return answers;
	}
}
