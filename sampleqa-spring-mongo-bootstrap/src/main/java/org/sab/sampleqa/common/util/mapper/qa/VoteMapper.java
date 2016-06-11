package org.sab.sampleqa.common.util.mapper.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sab.sampleqa.data.model.qa.Vote;
import org.sab.sampleqa.web.model.qa.VoteUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class VoteMapper {
	public VoteUI toUIBean(Vote pBean) {
		VoteUI uiBean = null;

		if (pBean != null) {
			uiBean = new VoteUI();

			uiBean.setCreatedDate(pBean.getCreatedDate());
			uiBean.setId(pBean.getId());
			uiBean.setUser(pBean.getUser());
		}

		return uiBean;
	}

	public List<VoteUI> toUIBean(List<Vote> votes) {
		List<VoteUI> uiBeans = new ArrayList<VoteUI>();

		for (Vote vote : votes) {
			uiBeans.add(toUIBean(vote));
		}
		return uiBeans;
	}

	public List<VoteUI> toUIBean(Map<String, Vote> votes) {
		List<VoteUI> uiBeans = new ArrayList<VoteUI>();

		for (String key : votes.keySet()) {
			uiBeans.add(toUIBean(votes.get(key)));
		}

		return uiBeans;
	}

	public Page<VoteUI> toUIBean(Page<Vote> votes, Pageable pageable) {
		Page<VoteUI> uiBeans = new PageImpl<VoteUI>(
				toUIBean(votes.getContent()), pageable,
				votes.getTotalElements());

		return uiBeans;
	}

	public Vote toPersistenceBean(VoteUI uiBean) {
		Vote vote = null;

		if (uiBean != null) {
			vote = new Vote();

			vote.setCreatedDate(uiBean.getCreatedDate());
			vote.setId(uiBean.getId());
			vote.setUser(uiBean.getUser());
		}
		return vote;
	}

	public List<Vote> toPersistenceBean(List<VoteUI> uiVotes) {
		List<Vote> votes = new ArrayList<Vote>();

		for (VoteUI uiBean : uiVotes) {
			votes.add(toPersistenceBean(uiBean));
		}
		return votes;
	}

	public Map<String, Vote> toPersistenceBeanMap(List<VoteUI> uiVotes) {
		Map<String, Vote> votes = new HashMap<String, Vote>();

		for (VoteUI uiBean : uiVotes) {
			votes.put(uiBean.getId(), toPersistenceBean(uiBean));
		}

		return votes;
	}
}
