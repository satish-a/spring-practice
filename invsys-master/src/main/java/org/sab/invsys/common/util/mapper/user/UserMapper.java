package org.sab.invsys.common.util.mapper.user;

import java.util.ArrayList;
import java.util.List;

import org.sab.invsys.persistence.model.user.User;
import org.sab.invsys.web.model.user.UserUI;
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
			uiBean.setUsername(user.getUsername());
			uiBean.setAccountType(user.getAccountType());
			uiBean.setBillingAddress(user.getBillingAddress());
			uiBean.setCreateadBy(user.getCreateadBy());
			uiBean.setCreatedDate(user.getCreatedDate());
			uiBean.setDescription(user.getDescription());
			uiBean.setEmail(user.getEmail());
			uiBean.setLastLoggedIn(user.getLastLoggedIn());
			uiBean.setMobilePhone(user.getMobilePhone());
			uiBean.setModifiedBy(user.getModifiedBy());
			uiBean.setModifiedDate(user.getModifiedDate());
			uiBean.setNotes(user.getNotes());
			uiBean.setOfficePhone(user.getOfficePhone());

			if (user.getBalance() != null) {
				uiBean.setBalance(user.getBalance().getAmount());
			}
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
			user.setUsername(uiBean.getUsername());
			user.setAccountType(uiBean.getAccountType());
			user.setBillingAddress(uiBean.getBillingAddress());
			user.setCreateadBy(uiBean.getCreateadBy());
			user.setCreatedDate(uiBean.getCreatedDate());
			user.setDescription(uiBean.getDescription());
			user.setEmail(uiBean.getEmail());
			user.setLastLoggedIn(uiBean.getLastLoggedIn());
			user.setMobilePhone(uiBean.getMobilePhone());
			user.setModifiedBy(uiBean.getModifiedBy());
			user.setModifiedDate(uiBean.getModifiedDate());
			user.setNotes(uiBean.getNotes());
			user.setOfficePhone(uiBean.getOfficePhone());
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
