package org.sab.invsys.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MediatorController {

	@RequestMapping
	public String getHomePage() {
		return "home";
	}
	
	@RequestMapping(value="/home")
	public String getHomePageUrl() {
		return "home";
	}
	
	@RequestMapping(value="/test")
	public String getTestPageUrl() {
		return "test/test";
	}
}
