package org.sab.invsys.web.controller.parties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sab.invsys.common.util.extjs.FilterRequest;
import org.sab.invsys.common.util.extjs.JsonUtils;
import org.sab.invsys.common.util.extjs.ResponseMap;
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
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private UserService userService;

	@Autowired
	private ResponseMap<UserUI> responseMap;

	private Logger logger = Logger.getLogger(UserController.class);
	private final String ACCOUNT_TYPE = "Staff";

	@RequestMapping
	public String startPage() {
		return "staff/list";
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
			filters.add(new FilterRequest("accountType", ACCOUNT_TYPE));

			if (filter != null) {
				filters.addAll(JsonUtils.getListFromJsonArray((String) filter));
			}

			users = userService.findAll(pageable, filters);
			long total = users.getTotalElements();
			logger.debug("users : " + users.getContent());

			return responseMap.mapOK(users.getContent(), total);
		} catch (Exception e) {
			logger.error(e);
			return responseMap
					.mapError("Error retrieving account from database.");
		}
	}

	@RequestMapping(value = "/view/{userName}")
	public String view(@PathVariable String userName, Model model)
			throws Exception {
		UserUI user = userService.findByUsername(userName);

		model.addAttribute("userBean", user);
		model.addAttribute("operationName", "update");

		return "staff/view";
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	Map<String, ? extends Object> update(UserUI data) throws Exception {
		try {
			UserUI user = userService.update(data);

			if (user != null) {
				return responseMap.mapOK(user, "Account Updated Succesfully");
			} else {
				return responseMap.mapError("Error trying to update account.");
			}
		} catch (Exception e) {
			logger.error(e);
			return responseMap.mapError("Error trying to update account.");
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() throws Exception {
		return "staff/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> add(UserUI data) {
		try {
			if (userService.findByUsername(data.getUsername()) != null) {
				return responseMap.mapError("Account Name already exists");
			}

			UserUI savedUser = userService.create(data);
			if (savedUser != null) {
				return responseMap.mapOK(savedUser,
						"Account Created Succesfully");
			} else {
				return responseMap.mapError("Error trying to create account.");
			}
		} catch (Exception e) {
			return responseMap.mapError("Error trying to create account.");
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
				UserUI user = userService.findByUsername(userName);

				if (user != null) {
					userService.delete(user);
					deletedUsers.add(user);
				}
			}

			return responseMap.mapOK(deletedUsers);
		} catch (Exception e) {
			e.printStackTrace();
			return responseMap.mapError("Error trying to update contact.");
		}
	}
}
