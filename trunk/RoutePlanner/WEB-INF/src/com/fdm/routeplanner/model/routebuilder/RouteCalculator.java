package com.fdm.routeplanner.model.routebuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.engine._DijkstraEngine;
import com.fdm.routeplanner.model.network._Edge;
import com.fdm.routeplanner.model.network._Node;
import com.fdm.routeplanner.model.network._RoutesMap;


@Service("routeCalculator")
@Scope("request")
class RouteCalculator implements _RouteCalculator {
	
	private final _DijkstraEngine dijkstraEngine;
	private Queue<EncapsulatedEdgeList> queue = new LinkedList<EncapsulatedEdgeList>();
	private Map<_Node,List<_Node>> predecessors;
	private int minTransfer = Integer.MAX_VALUE;
	private _RoutesMap routesMap;

	@Autowired
	RouteCalculator (@Value("#{dijkstraEngine}") _DijkstraEngine dijksrasEngine) {
		this.dijkstraEngine = dijksrasEngine;
	}
	
    @Override
    public void setMap(_RoutesMap routesMap) {
    	this.routesMap = routesMap;
    }
	
	@Override
	public final void execute(_Node startNode, _Node destinationNode) throws RoutePlannerException {
		minTransfer = Integer.MAX_VALUE;
		queue.clear();
		dijkstraEngine.execute(startNode, destinationNode);
		predecessors = dijkstraEngine.getPredecessor(destinationNode);
	}

	@Override
	public final List<List<_Edge>> getPredecessor(_Node destinationNode) throws RoutePlannerException {	
		List<List<_Edge>> minimumEdgeList = null;
		if (predecessors.get(destinationNode) == null)
    		throw new RoutePlannerException("These stations do not connect to each other");
    	
    	List<_Edge> destinations = new ArrayList<_Edge>();
    	EncapsulatedEdgeList encapsulatedEdgeList = new EncapsulatedEdgeList(destinations, 0);
    	queue.add(encapsulatedEdgeList);
    	List<_Edge> edges = getPredecessorEdges(destinationNode);
    	deepClone(encapsulatedEdgeList, edges);

    	OUTERLOOP:
    	while ((encapsulatedEdgeList = queue.poll()) != null) {
    		if (encapsulatedEdgeList.getTransfer() > minTransfer)
    			continue OUTERLOOP;
    		List<_Edge> tempEdgeList = encapsulatedEdgeList.getEdgeList();
    		int listSize = tempEdgeList.size();
    		for (int listIndex=(listSize-1); listIndex < listSize; listIndex++) {
        		if (encapsulatedEdgeList.getTransfer() > minTransfer)
        			continue OUTERLOOP;
    			_Node tempNode = tempEdgeList.get(listIndex).getStartNode();
    			List<_Edge> tempEdges = getPredecessorEdges(tempNode, tempEdgeList.get(listIndex));
    			if (tempEdges.isEmpty()) break;
    			else {
    				deepClone(encapsulatedEdgeList, tempEdges);
    				listSize = tempEdgeList.size();
    			}
    		}
    		int localTransfer = encapsulatedEdgeList.getTransfer();
    		if (localTransfer < minTransfer) {
    			minTransfer = localTransfer;
    			minimumEdgeList = new ArrayList<List<_Edge>>();
    		}
    		if (localTransfer <= minTransfer) {
    	    	Collections.reverse(tempEdgeList);
    	    	minimumEdgeList.add(tempEdgeList);
    		}
    	}
    	return minimumEdgeList;
	}
	
	 /**
     * Makes a clone of a list of edges that contains multiple routes
     * from a certain node
     * @param list list of edges to be cloned 
     * @param edges edges to be added to the end of each node
     * @return a list containing the clones of the list of edges with multiple routes 
     */
    private final void deepClone(EncapsulatedEdgeList encapsulatedEdgeList, List<_Edge> edges) {
    	int index = 1;
    	List<_Edge> tempEdgeList = encapsulatedEdgeList.getEdgeList();
    	if (encapsulatedEdgeList.getEdgeList().size() > 0) {
    		int transfer = encapsulatedEdgeList.getTransfer();
    		_Edge lastEdge = tempEdgeList.get(tempEdgeList.size()-1);
	    	while (index<edges.size()) {
	    		int localTransfer = transfer;
	    		if (!lastEdge.getLine().equals(edges.get(index).getLine())) {
	    			localTransfer++;
	    		}
	    		if (localTransfer <= minTransfer) {
	    			cloneHelper(tempEdgeList, edges.get(index), localTransfer);
	    		}
	    		index++;
	    	}
	    	if (!lastEdge.getLine().equals(edges.get(0).getLine())) {
	    		encapsulatedEdgeList.incrementTransfer();
    			transfer++;
    		}
	    	if (transfer <= minTransfer) {
	    		tempEdgeList.add(edges.get(0));
	    	}
    	} else {
    		while (index<edges.size()) {
    			cloneHelper(tempEdgeList, edges.get(index), 0);
        		index++;
    		}
    		tempEdgeList.add(edges.get(0));
    	}
    }
    
    /**
     * Creates a clone of the original edge list, then adds the specified edge and finally adds
     * to queue
     * @param originalEdgeList
     * @param edgeToAdd
     * @param numOfTransfer
     */
    private final void cloneHelper(List<_Edge> originalEdgeList, _Edge edgeToAdd, int numOfTransfer) {
		List<_Edge> temp = new ArrayList<_Edge>(originalEdgeList);
		EncapsulatedEdgeList tempObject = new EncapsulatedEdgeList(temp, numOfTransfer);
		temp.add(edgeToAdd);
		queue.add(tempObject);
    }
    
    /**
     * Creates a list of edges from a particular node
     * @param endNode, end node
     * @return list of edges from that node
     */
    private final List<_Edge> getPredecessorEdges(_Node endNode) {
    	List<_Edge> temp = new ArrayList<_Edge>();
    	if (predecessors.containsKey(endNode))
    	{
    		for (_Node node : predecessors.get(endNode)) {
    			temp.addAll(routesMap.getEdgeList(node, endNode));
    		}
    	}
    	return temp;
    }
    
    /**
     * Creates a list of edges from a particular node but forces the edge in the
     * same line as previous edge to be in index 0.
     * @param endNode, end node
     * @param prevEdge, previous edge to compare with
     * @return list of edges from that node
     */
	private final List<_Edge> getPredecessorEdges(_Node endNode, _Edge prevEdge) {
    	List<_Edge> temp = new ArrayList<_Edge>();
    	if (predecessors.containsKey(endNode)) {
    		for (_Node node : predecessors.get(endNode)) {
    			for (_Edge tempEdge : routesMap.getEdgeList(node, endNode)) {
    				if (tempEdge.getLine() != prevEdge.getLine()) {
    					temp.add(tempEdge);
    				} else {
    					temp.add(0, tempEdge);
    				}
    			}
    		}
    	}
    	return temp;
	}
    
    /**
     * EncapsulatedEdgeList is used to keep track of the number of transfers. Otherwise
     * it would be necessary to iterate through the list every time
     * @author Wren.Chan
     */
    private final class EncapsulatedEdgeList {
    	
    	private final List<_Edge> edgeList;
    	private int transfer;
    	
    	public EncapsulatedEdgeList(List<_Edge> edgeList, int transfer) {
    		this.edgeList = edgeList;
    		this.transfer = transfer;
    	}
        
        public final List<_Edge> getEdgeList() {
        	return edgeList;
        }
        
        public final void incrementTransfer() {
        	transfer++;
        }
        
        public final int getTransfer() {
        	return transfer;
        }
    }
}