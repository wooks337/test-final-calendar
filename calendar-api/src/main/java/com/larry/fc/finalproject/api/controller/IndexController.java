package com.larry.fc.finalproject.api.controller;

import static com.larry.fc.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Model model, HttpSession httpSession,
			@RequestParam(required = false) String redirect) {
		model.addAttribute("isSignIn", httpSession.getAttribute(LOGIN_SESSION_KEY) != null);
		model.addAttribute("redirect", redirect);
		return "index";
	}
}