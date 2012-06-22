package com.fdm.routeplanner.model.network;

import java.util.List;
import java.util.Set;

import com.fdm.routeplanner.exception.InvalidNetWorkException;
import com.fdm.routeplanner.exception.RoutePlannerException;


/**
 * This interface defines the object storing the graph of all routes in the
 * system.
 * 
 * @author Renaud Waldura &lt;renaud+tw@waldura.com&gt;
 * @version $Id: RoutesMap.java 2367 2007-08-20 21:47:25Z renaud $
 */

public interface _RoutesMap
{
	/**
	 * Enter a new edge into the graph.
	 * 
	 * @param start
	 * @param end
	 * @param distance
	 * @param line
	 */
	
	public abstract void addDirectRoute(_Node start, _Node end, int distance, _Line line);
	
	/**
	 * Get weight of an edge
	 * @param start
	 * @param end
	 * @return
	 */
	public abstract int getDistance(_Node start, _Node end);
	
	/**
	 * Get the list of cities that can be reached from the given city.
	 * 
	 * @param unsettledNode
	 * @return
	 */
	public abstract List<_Edge> getDestinations(_Node unsettledNode); 
	
	/**
	 * Get the list of cities that lead to the given city.
	 * 
	 * @param node
	 * @return <_Node>
	 */
	public abstract List<_Edge> getPredecessors(_Node node);
	
	/**
	 * @param nodeName
	 * @return node
	 * @throws InvalidNetWorkException
	 */
	public abstract _Node getNode(String nodeName) throws InvalidNetWorkException;
    
	/**
	 * @param start
	 * @param end
	 * @return edge between startNode and endNode
	 */
    public abstract List<_Edge> getEdgeList(_Node start, _Node end);

	/**
	 * @return the transposed graph of this graph, as a new RoutesMap instance.
	 */
	public abstract _RoutesMap getInverse();

	/**
	 * @return All nodes in edge network
	 * @throws RoutePlannerException
	 */
	public abstract Set<_Node> getNodes() throws RoutePlannerException;
}
