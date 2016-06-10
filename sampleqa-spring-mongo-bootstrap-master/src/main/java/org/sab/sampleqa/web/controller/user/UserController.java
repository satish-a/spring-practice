package org.sab.sampleqa.web.controller.user;

import java.util.Map;

import org.apache.log4j.Logger;
import org.sab.sampleqa.common.util.ui.DataTableResponseMap;
import org.sab.sampleqa.service.user.UserService;
import org.sab.sampleqa.web.model.user.UserUI;
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
	private Logger logger = Logger.getLogger(UserController.class);
	private DataTableResponseMap<UserUI> userResponseMap = new DataTableResponseMap<UserUI>();

	@RequestMapping
	public String getUsersPage(Model model) {
		model.addAttribute("users", service.findAll());

		return "user/list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody
	Map<String, ? extends Object> list(
			@RequestParam(required = false) String iDisplayStart,
			@RequestParam(required = false) String iDisplayLength,
			@RequestParam(required = false) String iSortingCols) {

		int pageLength = Integer.parseInt(iDisplayLength);
		int startPage = Integer.parseInt(iDisplayStart) / pageLength;
		Pageable pageable = new PageRequest(startPage, pageLength);
		Page<UserUI> users = service.findAll(pageable);

		return userResponseMap.mapOK(users.getContent(),
				users.getTotalElements());
	}

	@RequestMapping(value = "/view/{userName}")
	public String view(@PathVariable String userName, Model model)
			throws Exception {
		return "user/view";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() throws Exception {
		return "user/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(UserUI data) {
		System.out.println("In add post method");
		String resultPage = "access/login";

		if (service.findByUsername(data.getUserName()) != null) {
			logger.debug("Account Name already exists");
		}

		UserUI savedUser = service.create(data);
		if (savedUser != null) {
			logger.debug("Account Created Succesfully");
			resultPage = "access/login";
		} else {
			logger.debug("Error trying to create account.");
			resultPage = "user/add";
		}

		return resultPage;
	}
}
