package org.sab.sampleqa.common.util.mapper.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sab.sampleqa.common.util.mapper.user.UserMapper;
import org.sab.sampleqa.data.model.qa.Tag;
import org.sab.sampleqa.web.model.qa.TagUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class TagMapper {
	UserMapper uMapper = new UserMapper();

	public TagUI toUIBean(Tag pBean) {
		TagUI uiBean = null;

		if (pBean != null) {
			uiBean = new TagUI();

			uiBean.setCreatedDate(pBean.getCreatedDate());
			uiBean.setId(pBean.getId());
			uiBean.setCreatedBy(uMapper.toUIBean(pBean.getCreatedBy()));
			uiBean.setName(pBean.getName());

			if (pBean.getQuestions() != null) {
				uiBean.setQuestions(pBean.getQuestions());
			}
		}

		return uiBean;
	}

	public List<TagUI> toUIBean(List<Tag> tags) {
		List<TagUI> uiBeans = new ArrayList<TagUI>();

		for (Tag tag : tags) {
			uiBeans.add(toUIBean(tag));
		}
		return uiBeans;
	}

	public List<TagUI> toUIBean(Map<String, Tag> tags) {
		List<TagUI> uiBeans = new ArrayList<TagUI>();

		for (String key : tags.keySet()) {
			uiBeans.add(toUIBean(tags.get(key)));
		}

		return uiBeans;
	}

	public Page<TagUI> toUIBean(Page<Tag> tags, Pageable pageable) {
		Page<TagUI> uiBeans = new PageImpl<TagUI>(toUIBean(tags.getContent()),
				pageable, tags.getTotalElements());

		return uiBeans;
	}

	public Tag toPersistenceBean(TagUI uiBean) {
		Tag tag = null;

		if (uiBean != null) {
			tag = new Tag();

			tag.setCreatedDate(uiBean.getCreatedDate());
			tag.setId(uiBean.getId());
			tag.setCreatedBy(uMapper.toPersistenceBean(uiBean.getCreatedBy()));
			tag.setName(uiBean.getName());

			if (uiBean.getQuestions() != null) {
				tag.setQuestions(uiBean.getQuestions());
			}
		}
		return tag;
	}

	public List<Tag> toPersistenceBean(List<TagUI> uiTags) {
		List<Tag> tags = new ArrayList<Tag>();

		for (TagUI uiBean : uiTags) {
			tags.add(toPersistenceBean(uiBean));
		}
		return tags;
	}

	public Map<String, Tag> toPersistenceBeanMap(List<TagUI> uiTags) {
		Map<String, Tag> tags = new HashMap<String, Tag>();

		for (TagUI uiBean : uiTags) {
			tags.put(uiBean.getId(), toPersistenceBean(uiBean));
		}

		return tags;
	}
}
