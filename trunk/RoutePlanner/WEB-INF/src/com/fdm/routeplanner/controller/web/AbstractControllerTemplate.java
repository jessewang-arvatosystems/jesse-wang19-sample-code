package com.fdm.routeplanner.controller.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.fdm.routeplanner.controller._RouteController;

abstract class AbstractControllerTemplate {
	
	protected static final Logger myLogger = Logger.getLogger("Router");
	
	@Value("$url{errorURL}")
	protected String errorURL;
	
	@Value("#{routeController}")
	protected _RouteController routeController;
	
	protected String urlPath;
	
	protected final void setErrorURL(String errorURL) {
		this.errorURL = errorURL;
	}

	protected final void setRouteController(_RouteController routeController) {
		this.routeController = routeController;
	}

	protected void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	
}
