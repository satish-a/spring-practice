package org.sab.sampleqa.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MediatorController {

	@RequestMapping
	public String getRootPage() {
		return "redirect:question/list";
	}
	
	@RequestMapping(value = "/home")
	public String getHomePage() {
		return "home";
	}

	@RequestMapping(value = "/user")
	public String getUserPage() {
		return "user";
	}

	@RequestMapping(value = "/admin")
	public String getAdminPage() {
		return "admin";
	}
}
