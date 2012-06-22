package com.fdm.routeplanner.advice;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;


//@Aspect
//@Component("webControllerAdvice")
public class WebControllerAdvice {

	private static final Logger myLogger = Logger.getLogger("Router");
	private HttpSession session;
	private ModelAndView modelAndView;

	@Pointcut(
		"(execution(* com.fdm.routeplanner.controller.web.*.handleRequestInternal(..)) && " +
		"!execution(* com.fdm.routeplanner.controller.web.ValidateAccountController.handleRequestInternal(..))) || " +
		"execution(* com.fdm.routeplanner.controller.web.RedirectController.logout(..)) || " +
		"execution(* com.fdm.routeplanner.controller.web.RedirectController.homepage(..)) || " +
		"execution(* com.fdm.routeplanner.controller.web.RedirectController.contact(..))"
		)
	public void getInternalRequest() {}

	@Around("getInternalRequest()")
	public ModelAndView handleInvalidSessions(ProceedingJoinPoint joinPoint) throws Throwable {
		boolean verified = true;
		Object[] arguments;
		
		arguments = joinPoint.getArgs();
		retrieveArguments(arguments);
		verified = verifySession(session);
		joinPoint.proceed();
		
		if (!verified) {
			myLogger.error("Invalid Login at " + joinPoint.getTarget().getClass().getSimpleName());
			modelAndView.addObject("message", "Invalid Login Credentials!");
			modelAndView.setViewName("jsp/error.jsp");
		}

		return modelAndView;
	}

	private void retrieveArguments(Object[] arguments) {
		for (Object anArg : arguments) {
			if (anArg instanceof HttpSession) {
				session = (HttpSession) anArg;
			}
			if (anArg instanceof ModelAndView) {
				modelAndView = (ModelAndView) anArg;
			}
		}
	}

	private boolean verifySession(HttpSession session) {
		boolean verified;
	
		if (session == null) {
			verified = false;
		} else {
			verified = session.getAttribute("userID") != null;
		}
		
		return verified;
	}

}