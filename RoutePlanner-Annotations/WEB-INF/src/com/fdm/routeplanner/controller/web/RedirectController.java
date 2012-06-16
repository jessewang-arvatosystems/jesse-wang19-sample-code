package com.fdm.routeplanner.controller.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
class RedirectController {

	private static final Logger myLogger = Logger.getLogger("Router");
	
	@RequestMapping("/login.htm")
	public ModelAndView login(@Value("$url{loginURL}") String url,
			ModelAndView modelAndView) {
		return redirectToThisURL(url, modelAndView);
	}

	@RequestMapping("/logout.htm")
	public ModelAndView logout(@Value("$url{logoutURL}") String url,
			HttpSession session, ModelAndView modelAndView) {
		return redirectToThisURL(url, modelAndView);
	}

	@RequestMapping("/homepage.htm")
	public ModelAndView homepage(@Value("$url{homepageURL}") String url,
			HttpSession session, ModelAndView modelAndView,
			Principal principal) {
		String username = principal.getName();
		session.setAttribute("userID", username);
		return redirectToThisURL(url, modelAndView);
	}

	@RequestMapping("/contact.htm")
	public ModelAndView contact(@Value("$url{contactURL}") String url,
			HttpSession session, ModelAndView modelAndView) {
		return redirectToThisURL(url, modelAndView);
	}

	@RequestMapping("/register.htm")
	public ModelAndView register(@Value("$url{registerURL}") String url,
			ModelAndView modelAndView) {
		return redirectToThisURL(url, modelAndView);
	}

	@RequestMapping("/error.htm")
	public ModelAndView error(@Value("$url{errorURL}") String url,
			ModelAndView modelAndView) {
		myLogger.error("Invalid Login Occured!");
		modelAndView.addObject("message", "Invalid Login Credentials!");
		return redirectToThisURL(url, modelAndView);
	}
	
	private final ModelAndView redirectToThisURL(String url,
			ModelAndView modelAndView) {
		modelAndView.setViewName(url);
		return modelAndView;
	}

}