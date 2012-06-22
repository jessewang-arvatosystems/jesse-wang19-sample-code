package com.fdm.routeplanner.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.registration.Registration;
import com.fdm.routeplanner.model.registration.User;

@Controller
@Scope("request")
class RegisterAccountController extends AbstractControllerTemplate {

	@Value("$url{successURL}")
	private String urlPath;
	
	@Value("#{registration}")
	private Registration registerAccount;
	
	@RequestMapping("/success.htm")
	public ModelAndView handleRequestInternal(	@RequestParam(value = "first_name", required = false) String firstName,
												@RequestParam(value = "last_name", required = false) String lastName,
												@RequestParam(value = "username", required = false) String userID,
												@RequestParam(value = "password", required = false) String password,
												ModelAndView modelAndView) {	
		
		User user = new User();
		user.setFirst(firstName);
		user.setLast(lastName);
		user.setUser_id(userID);
		user.setPassword(password);
		
		try {
			registerAccount.registerUser(user);
			modelAndView.addObject("user", user);
			modelAndView.setViewName(urlPath);	
		} catch (RoutePlannerException e) {
			modelAndView.addObject("message", e.getLocalizedMessage());
			modelAndView.setViewName(errorURL);	
		}					
		
		return modelAndView;
	}
	
	/**
	 * @param registerAccount the registerAccount to set
	 */
	@SuppressWarnings("unused")
	private final void setRegisterAccount(Registration registerAccount) {
		this.registerAccount = registerAccount;
	}
}