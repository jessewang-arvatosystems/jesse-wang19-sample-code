package com.fdm.routeplanner.model.engine;

import java.util.List; 
import java.util.Map;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.network._RoutesMap;
import com.fdm.routeplanner.model.network._Node;

	/**
	 * An implementation of Dijkstra's shortest path algorithm. It computes the shortest path (in distance)
	 * to all cities in the map. The output of the algorithm is the shortest distance from the start station 
	 * to every other station, and the shortest path from the start station to every other.
	 * <p>
	 * Upon calling
	 * {@link #execute(_Node, _Node)}, 
	 * the results of the algorithm are made available by calling
	 * {@link #getPredecessor(_Node)}
	 * and 
	 * {@link #getShortestDistance(_Node)}.
	 * 
	 * To get the shortest path between the station <var>destination</var> and
	 * the source station after running the algorithm, one would do:
	 * <pre>
	 * ArrayList&lt;_Station&gt; l = new ArrayList&lt;_Station&gt;();
	 *
	 * for (_Station station = destination; station != null; station = engine.getPredecessor(station))
	 * {
	 *     l.add(station);
	 * }
	 *
	 * Collections.reverse(l);
	 *
	 * return l;
	 * </pre>
	 * 
	 * @see #execute(_Node, _Node)
	 * 
	 * @author Renaud Waldura &lt;renaud+tw@waldura.com&gt;
	 * @version $Id: DijkstraEngine.java 2379 2007-08-23 19:06:29Z renaud $
	 */
public interface _DijkstraEngine {
	
	/**
	 * Returns shortest routes
	 * 
	 * @param end
	 * @return List<List<_Edge>>
	 * @throws RoutePlannerException 
	 */
	public abstract Map<_Node, List<_Node>> getPredecessor(_Node end) throws RoutePlannerException;
	
	/**
	 * Set up node map
	 * 
	 * @param map
	 */
	public abstract void setMap(_RoutesMap map);
	
	/**
     * Run Dijkstra's shortest path algorithm on the map.
     * The results of the algorithm are available through
     * {@link #getPredecessor(_Node)}
     * and 
     * {@link #getShortestDistance(_Node)}
     * upon completion of this method.
     * 
     * @param startNode
     * @param endNode the destination station. If this argument is <code>null</code>, the algorithm is
     * run on the entire graph, instead of being stopped as soon as the destination is reached.
     * @throws RoutePlannerException 
     */
	public abstract void execute(_Node startStation, _Node endStation) throws RoutePlannerException;
	

    /**
     * @return the shortest distance from the source to the given station, or
     * {@link DijkstraEngine#INFINITE_DISTANCE} if there is no route to the destination.
     */
	public abstract int getShortestDistance(_Node start);
	
}
