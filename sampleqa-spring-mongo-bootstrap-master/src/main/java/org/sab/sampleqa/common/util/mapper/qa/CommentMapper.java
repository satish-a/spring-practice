package org.sab.sampleqa.common.util.mapper.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sab.sampleqa.common.util.mapper.user.UserMapper;
import org.sab.sampleqa.data.model.qa.Comment;
import org.sab.sampleqa.web.model.qa.CommentUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class CommentMapper {
	UserMapper userMapper = new UserMapper();

	public CommentUI toUIBean(Comment pBean) {
		CommentUI uiBean = null;

		if (pBean != null) {
			uiBean = new CommentUI();
			uiBean.setContent(pBean.getContent());
			uiBean.setCreatedDate(pBean.getCreatedDate());
			uiBean.setEditedDate(pBean.getEditedDate());
			uiBean.setId(pBean.getId());

			if (pBean.getCreatedBy() != null) {
				uiBean.setCreatedBy(userMapper.toUIBean(pBean.getCreatedBy()));
			}
		}

		return uiBean;
	}

	public List<CommentUI> toUIBean(List<Comment> comments) {
		List<CommentUI> uiBeans = new ArrayList<CommentUI>();

		for (Comment comment : comments) {
			uiBeans.add(toUIBean(comment));
		}
		return uiBeans;
	}

	public List<CommentUI> toUIBean(Map<String, Comment> comments) {
		List<CommentUI> uiBeans = new ArrayList<CommentUI>();

		for (String key : comments.keySet()) {
			CommentUI comment = toUIBean(comments.get(key));
			comment.setId(key);
			uiBeans.add(comment);
		}

		return uiBeans;
	}

	public Page<CommentUI> toUIBean(Page<Comment> comments, Pageable pageable) {
		Page<CommentUI> uiBeans = new PageImpl<CommentUI>(
				toUIBean(comments.getContent()), pageable,
				comments.getTotalElements());

		return uiBeans;
	}

	public Comment toPersistenceBean(CommentUI uiBean) {
		Comment comment = null;

		if (uiBean != null) {
			comment = new Comment();
			comment.setContent(uiBean.getContent());
			comment.setCreatedDate(uiBean.getCreatedDate());
			comment.setEditedDate(uiBean.getEditedDate());
			comment.setId(uiBean.getId());

			if (uiBean.getCreatedBy() != null) {
				comment.setCreatedBy(userMapper.toPersistenceBean(uiBean
						.getCreatedBy()));
			}
		}
		return comment;
	}

	public List<Comment> toPersistenceBean(List<CommentUI> uiComments) {
		List<Comment> comments = new ArrayList<Comment>();

		for (CommentUI uiBean : uiComments) {
			comments.add(toPersistenceBean(uiBean));
		}
		return comments;
	}

	public Map<String, Comment> toPersistenceBeanMap(List<CommentUI> uiComments) {
		Map<String, Comment> comments = new HashMap<String, Comment>();

		for (CommentUI uiBean : uiComments) {
			comments.put(uiBean.getId(), toPersistenceBean(uiBean));
		}

		return comments;
	}
}
