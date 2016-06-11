package org.sab.invsys.web.controller.parties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.extjs.JsonUtils;
import org.sab.invsys.common.util.extjs.ResponseMap;
import org.sab.invsys.common.util.extjs.FilterRequest;
import org.sab.invsys.service.user.UserService;
import org.sab.invsys.web.model.user.UserUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;

	@Autowired
	private ResponseMap<UserUI> extJS;
	
	private Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping
	public String getUsersPage() {
		return "user/users";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody
	Map<String, ? extends Object> list(@RequestParam int page,
			@RequestParam int start, @RequestParam int limit,
			@RequestParam(required = false) Object filter) throws Exception {
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			Page<UserUI> users = null;
			List<FilterRequest> filters = new ArrayList<FilterRequest>();
			
			if (filter != null) {
				logger.debug("Processing Filters!");
				filters.addAll(JsonUtils
						.getListFromJsonArray((String) filter));
			}
			
			users = service.findAll(pageable, filters);
			long total = users.getTotalElements();
			logger.debug("users : " + users.getContent());

			return extJS.mapOK(users.getContent(), total);
		} catch (Exception e) {
			logger.error(e);
			return extJS.mapError("Error retrieving Contacts from database.");
		}
	}

	@RequestMapping(value = "/view/{userName}")
	public String view(@PathVariable String userName, Model model)
			throws Exception {
		UserUI user = service.findByUsername(userName);

		model.addAttribute("userBean", user);
		model.addAttribute("operationName", "update");

		return "user/user";
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	Map<String, ? extends Object> update(UserUI data) throws Exception {
		try {
			UserUI user = service.update(data);

			if (user != null) {
				return extJS.mapOK(user);
			} else {
				return extJS.mapError("Error trying to update contact.");
			}
		} catch (Exception e) {
			logger.error(e);
			return extJS.mapError("Error trying to update contact.");
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) throws Exception {
		model.addAttribute("userBean", new UserUI());
		model.addAttribute("operationName", "add");

		return "user/user";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> add(UserUI data) {
		try {
			UserUI savedUser = service.create(data);

			if (savedUser != null) {
				return extJS.mapOK(savedUser, "Account Created Succesfully");
			} else {
				return extJS.mapError("Error trying to create account.");
			}
		} catch (Exception e) {
			return extJS.mapError("Error trying to create account.");
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> delete(
			@RequestParam(value = "users") String name) {
		try {

			List<UserUI> deletedUsers = new ArrayList<UserUI>();

			for (String userName : name.substring(1, name.length() - 1)
					.replaceAll("\"", "").split(",")) {
				UserUI user = service.findByUsername(userName);

				if (user != null) {
					service.delete(user);
					deletedUsers.add(user);
					System.out.println("Deleted :: " + user.getUsername());
				}
			}

			return extJS.mapOK(deletedUsers);
		} catch (Exception e) {
			e.printStackTrace();
			return extJS.mapError("Error trying to update contact.");
		}
	}
}
