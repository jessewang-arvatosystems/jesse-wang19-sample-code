package com.fdm.routeplanner.model;

import java.util.List;

import com.fdm.routeplanner.model.routebuilder._Route;

public interface _JourneyBuilder {
	
	/**
	 * Creates list of route leg Strings for view
	 * 
	 * @param allRoutes
	 * @return _Journey
	 */
	public abstract _Journey createMessages(List<_Route> allRoutes);

	public abstract _Journey createOneMessage(_Route _Route);
}
