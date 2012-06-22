package com.fdm.routeplanner.model.routebuilder;

import java.util.List;

import com.fdm.routeplanner.model.network._Edge;
import com.fdm.routeplanner.model.network._Line;
import com.fdm.routeplanner.model.network._Node;


public interface _Leg {
	/**
	 * @return list of all edges within the leg
	 */
	public abstract List<_Edge> getListOfEdges(); 
	
	/**
	 * @return number of stops in leg
	 */
	public abstract int getNumberOfStops(); 	
	
	/**
	 * @return leg start node
	 */
	public abstract _Node getLegStartNode();
	
	/**
	 * @return leg end node
	 */
	public abstract _Node getLegEndNode(); 
	
	/**
	 * @return network line
	 */
	public abstract _Line getNetworkLine();	
	
	/**
	 * Add edge to Leg
	 * @param edge
	 */
	public abstract void addEdge(_Edge edge);
}
