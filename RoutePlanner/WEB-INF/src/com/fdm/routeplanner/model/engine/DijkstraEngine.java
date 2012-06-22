
package com.fdm.routeplanner.model.engine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.network._Edge;
import com.fdm.routeplanner.model.network._Node;
import com.fdm.routeplanner.model.network._RoutesMap;

@Service("dijkstraEngine")
@Scope("request")
public final class DijkstraEngine implements _DijkstraEngine {
	
    private static final int INFINITE_DISTANCE = Integer.MAX_VALUE;
    private static final int INITIAL_CAPACITY = 1;
    
    @Value("#{edgeMap}")
    private _RoutesMap map;
   
    private final Comparator<_Node> shortestDistanceComparator = new Comparator<_Node>()
        {
            public int compare(_Node left, _Node right)
            {
                int result = getShortestDistance(left) - getShortestDistance(right);
                return (result==0)? 1:result;
            }
        };
  
    private final PriorityQueue<_Node> unsettledNodes = new PriorityQueue<_Node>(INITIAL_CAPACITY, shortestDistanceComparator);
    private final Set<_Node> settledNodes = new HashSet<_Node>();
    private final Map<_Node, Integer> shortestDistances = new HashMap<_Node, Integer>();
    private final Map<_Node,List<_Node>> predecessors = new HashMap<_Node,List<_Node>> ();
    
    
    public final void setMap(_RoutesMap map)
    {
		this.map = map;
	}
	
   
    private final void init(_Node start)
    {
        settledNodes.clear();
        unsettledNodes.clear();
        shortestDistances.clear();
        predecessors.clear();
        setShortestDistance(start, 0);
        unsettledNodes.add(start);
      
    }
    
    
    @Override
    public final void execute(_Node start, _Node destination) throws RoutePlannerException
    {
    	if (start == null || destination == null) {
    		throw new RoutePlannerException("Cannot find the start and/or end stations!");
    	} else if (start.equals(destination)) {
    		throw new RoutePlannerException("Cannot process the same start and end stations!");
    	}
    	
        init(start);
        _Node unsettledNode;
        while ((unsettledNode = unsettledNodes.poll()) != null)
        {
            assert !isSettled(unsettledNode);
            if (unsettledNode == destination) break;
            settledNodes.add(unsettledNode);
            relaxNeighbors(unsettledNode);
        }
    }

    /**
	 * Compute new shortest distance for neighboring nodes and update if a shorter
	 * distance is found.
	 * 
	 * @param unsettledNode the node
	 */
    private final void relaxNeighbors(_Node unsettledNode)
    {
        for (_Edge destinationEdge : map.getDestinations(unsettledNode))
        {
        	if (isSettled(destinationEdge.getEndNode())) continue;
        	int shortDist = getShortestDistance(unsettledNode) + destinationEdge.getWeight();// map.getDistance(unsettledNode, destinationEdge.getEndNode());
            if (shortDist <= getShortestDistance(destinationEdge.getEndNode()))
            {
                setShortestDistance(destinationEdge.getEndNode(), shortDist);
                setPredecessor(destinationEdge.getEndNode(),unsettledNode);
            }
         }        
    }

	/**
	 * Test a node.
	 * 
     * @param destinationNode the node to consider
     * 
     * @return whether the node is settled, ie. its shortest distance
     * has been found.
     */
    private final boolean isSettled(_Node destinationNode)
    {
        return settledNodes.contains(destinationNode);
    }
   
    /**
     * @return the shortest distance from the source to the given station, or
     * {@link DijkstraEngine#INFINITE_DISTANCE} if there is no route to the destination.
     */   
    @Override
    public final int getShortestDistance(_Node node)
    {
        Integer distance = shortestDistances.get(node);
        return (distance == null) ? INFINITE_DISTANCE : distance;
    }

	/**
	 * Set the new shortest distance for the given node,
	 * and re-balance the queue according to new shortest distances.
	 * 
	 * @param node the node to set
	 * @param distance new shortest distance value
	 */        
    private final void setShortestDistance(_Node node, int distance)
    {
        unsettledNodes.remove(node);
        shortestDistances.put(node, distance);
		unsettledNodes.add(node);        
    }
    /**
     * creates a list containing a list of edges.
     *  Each edge list is the route a person will take from the end destination
     *  to the start destination 
     * @param end last station
     * @return list of all the possible destinations starting from that end node
     */
    @Override
    public final Map<_Node, List<_Node>> getPredecessor(_Node end) throws RoutePlannerException
    {
    	return predecessors;
    }
    /**
     * adds the end destination as a key in predecessors map,
     * and then adds a list of previous destinations from the end destination
     * so the value of each key will be a list of stations
     * @param end end station
     * @param start start station
     */
    private final void setPredecessor(_Node end, _Node start)
    {
    	List<_Node> listNodes = null;
    	if(predecessors.containsKey(end))
    	{
    		if(!predecessors.get(end).contains(start))
    		{
    			predecessors.get(end).add(start);
    			listNodes = predecessors.get(end);
    			predecessors.put(end, listNodes);
    		}
    	}
    	else
    	{
    		listNodes = new ArrayList<_Node>();
    		listNodes.add(start);
    		predecessors.put(end, listNodes);
    	}
    }
}


