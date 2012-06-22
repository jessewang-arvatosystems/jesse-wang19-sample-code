package com.fdm.routeplanner.model.network;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.exception.InvalidNetWorkException;

@Service("edgeMap")
class EdgeMap implements _RoutesMap {

	private final Map<_Node, Map<_Node, List<_Edge>>> edgeMap;
	
	@Autowired
	EdgeMap(@Value("#{hashMap}") Map<_Node, Map<_Node, List<_Edge>>> edgeMap)
	{
		this.edgeMap = edgeMap;
	}
	/**
	 * Append an edge object to the edgeMap property representing the graph 
	 * holding the Network structure
	 * @param start is the end Station
	 * @param end is the end Station
	 * @param distance the the cost
	 * @param line is the train line object which connects both stations 
	 */
	@Override
	public final void addDirectRoute(_Node start, _Node end, int distance, _Line line) 
	{
		Map<_Node, List<_Edge>> innerMap;
		List<_Edge> edgesList;
			
		Edge edge = createEdge(start, end, distance, line);
		if (edgeMap.containsKey(start)) {	
			innerMap = edgeMap.get(start);
			if (innerMap.containsKey(end)) {	
				edgesList = innerMap.get(end);
				if (!edgesList.contains(edge))	
					edgesList.add(edge);
			} else {							 
				edgesList = new ArrayList<_Edge>();
				edgesList.add(edge);				
			}
		} else {								
			innerMap = new HashMap<_Node, List<_Edge>>();
			edgesList = new ArrayList<_Edge>();
			edgesList.add(edge);
		}
		innerMap.put(end, edgesList);	
		edgeMap.put(start, innerMap);	
		}

	/**
	 * Return the list of edges directed out of the source node 
	 */
	@Override
	public final List<_Edge> getDestinations(_Node source) 
	{
		List<_Edge> destinationEdges = new ArrayList<_Edge>();
		Map<_Node, List<_Edge>> innerMap = edgeMap.get(source);
		Set<_Node> destinationsStationsSet = innerMap.keySet();
		Iterator<_Node> destinationsStationsIterator = destinationsStationsSet.iterator();
		while (destinationsStationsIterator.hasNext()) {
			_Node endStation = destinationsStationsIterator.next();

			List<_Edge> endStationEdges = innerMap.get(endStation);
			destinationEdges.addAll(endStationEdges);
		}
		return destinationEdges;
	}

	/**
	 * Computes the list of all available edges connecting start and end nodes
	 * @param start is the end Station
	 * @param end is the end Station
	 * @return a list of all available edges connecting start and end nodes
	 */
    @Override
    public final List<_Edge> getEdgeList(_Node start, _Node end) {
          Map<_Node, List<_Edge>> innerMap = edgeMap.get(start);
          return innerMap.get(end);
    }

	/**
	 * Retrieves the node object with the name stationName
	 * @param stationName is the Station name string
	 * @return the object holding the station 
	 */
	@Override
	public final _Node getNode(String stationName) throws InvalidNetWorkException {
		Set<_Node> list = edgeMap.keySet();
		for (_Node station : list) {
			if (stationName.equals(station.getName())) return station;
		}
		throw new InvalidNetWorkException("The station does not exist");
	}
	
	private final Edge createEdge(_Node start, _Node end, int distance, _Line stationLine)
	{
		Edge edge = new Edge();
		edge.setStartNode(start);
		edge.setEndNode(end);
		edge.setWeight(distance);
		edge.setLine(stationLine);			
		return edge;
	}

	/**
	 * Returns a set of all nodes stored in the Network structure
	 */
    @Override
    public final Set<_Node> getNodes(){
          return edgeMap.keySet();
    }
    
    @Override
	public final List<_Edge> getPredecessors(_Node source) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final _RoutesMap getInverse() {
		throw new UnsupportedOperationException();
	}

	@Override
	public final int getDistance(_Node start, _Node end) {
		throw new UnsupportedOperationException();
	}
	
}
