package org.sab.sampleqa.common.util.mapper.user;

import java.util.ArrayList;
import java.util.List;

import org.sab.sampleqa.data.model.user.User;
import org.sab.sampleqa.web.model.user.UserUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class UserMapper {
	public UserUI toUIBean(User user) {
		UserUI uiBean = null;

		if (user != null) {
			uiBean = new UserUI();
			uiBean.setFirstName(user.getFirstName());
			uiBean.setId(user.getId());
			uiBean.setLastName(user.getLastName());
			uiBean.setPassword(user.getPassword());
			uiBean.setComments(user.getComments());
			uiBean.setCountry(user.getCountry());
			uiBean.setCountryState(user.getCountryState());
			uiBean.setUserName(user.getUserName());
		}

		return uiBean;
	}

	public List<UserUI> toUIBean(List<User> users) {
		List<UserUI> uiBeans = new ArrayList<UserUI>();

		for (User user : users) {
			uiBeans.add(toUIBean(user));
		}
		return uiBeans;
	}

	public Page<UserUI> toUIBean(Page<User> users, Pageable pageable) {
		Page<UserUI> uiBeans = new PageImpl<UserUI>(
				toUIBean(users.getContent()), pageable,
				users.getTotalElements());

		return uiBeans;
	}

	public User toPersistenceBean(UserUI uiBean) {
		User user = null;

		if (uiBean != null) {
			user = new User();
			user.setFirstName(uiBean.getFirstName());
			user.setId(uiBean.getId());
			user.setLastName(uiBean.getLastName());
			user.setPassword(uiBean.getPassword());
			user.setComments(uiBean.getComments());
			user.setCountry(uiBean.getCountry());
			user.setCountryState(uiBean.getCountryState());
			user.setUserName(uiBean.getUserName());
		}
		return user;
	}

	public List<User> toPersistenceBean(List<UserUI> uiBeans) {
		List<User> users = new ArrayList<User>();

		for (UserUI uiBean : uiBeans) {
			users.add(toPersistenceBean(uiBean));
		}
		return users;
	}
}
