package com.fdm.routeplanner.model.routebuilder;
import java.util.*;

import com.fdm.routeplanner.model.network._Edge;
import com.fdm.routeplanner.model.network._Line;
import com.fdm.routeplanner.model.network._Node;


class Leg implements _Leg {
	
    private final List<_Edge> edgeList;
    private int stops;
    
    Leg() {
    	edgeList = new ArrayList<_Edge>();
    }
    
	public final _Node getLegStartNode(){
		return edgeList.get(0).getStartNode();
	}
	
	public final _Node getLegEndNode(){
		return edgeList.get((edgeList.size() -1)). getEndNode();
	}
	
    public final _Line getNetworkLine(){
    	return edgeList.get(0).getLine();
    } 
    
    public final void addEdge(_Edge edge){
    	edgeList.add(edge);
    	stops++;
    } 
    
    public int getNumberOfStops(){
    	return stops ;
    }

	@Override
	public List<_Edge> getListOfEdges() {
		return edgeList;
	} 

}
