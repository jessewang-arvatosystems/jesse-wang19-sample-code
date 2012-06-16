package com.fdm.routeplanner.tests;

import static org.junit.Assert.*;  

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.fdm.routeplanner.exception.InvalidNetWorkException;
import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.network.Edge;
import com.fdm.routeplanner.model.network.EdgeMapBuilder;
import com.fdm.routeplanner.model.network.TrainLine;
import com.fdm.routeplanner.model.network._Edge;
import com.fdm.routeplanner.model.network._Line;
import com.fdm.routeplanner.model.network._MapBuilder;
import com.fdm.routeplanner.model.network._RoutesMap;
import com.fdm.routeplanner.model.network._Node;


public final class EdgeMapTest {

	private _RoutesMap map;
	@SuppressWarnings("unused")
	private _Node stationA, stationB, stationE;
	
	@Before
	public void setUp() throws RoutePlannerException
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		_MapBuilder mapBean = (EdgeMapBuilder) context.getBean("edgeMapBuilder");
		map = mapBean.buildRoutesMap();		
	}
	
	
    @Test
    public void getEdgeTest() throws InvalidNetWorkException
    {
          stationA = map.getNode("King's Cross St. Pancras");
          stationB = map.getNode("Farringdon");
          System.out.println(stationA.getZone());
          _Edge expectedEdge = new Edge();
          expectedEdge.setStartNode(stationA);
          expectedEdge.setEndNode(stationB);
          expectedEdge.setWeight(1);
          _Line line = new TrainLine("Metropolitan (Chesham-Aldgate)");
          expectedEdge.setLine(line);
          _Edge actualEdge = map.getEdgeList(stationA, stationB).get(0);
          assertEquals("Invalid output", expectedEdge, actualEdge);
    }

		
	@Test
	public void testaddRoute() throws InvalidNetWorkException
	{
		_Node start = map.getNode("Moorgate");
		_Node end = map.getNode("Bank");
		int dist = 1;
		_Line line1 = new TrainLine("NorthernEast");
		map.addDirectRoute(start, end, dist, line1);
		
		
		List<_Edge> expectedEdges = new ArrayList<_Edge>();
		_Edge edge1 = new Edge();
		edge1.setStartNode(start);
		edge1.setEndNode(end);
		edge1.setWeight(dist);
		edge1.setLine(line1);
		expectedEdges.add(edge1);
		
		_Line line2 = new TrainLine("NorthernWest");
		map.addDirectRoute(start, end, dist, line2);
		_Edge edge2 = new Edge();
		edge2.setStartNode(start);
		edge2.setEndNode(end);
		edge2.setWeight(dist);
		edge2.setLine(line2);
		expectedEdges.add(edge2);		
		Collections.sort(expectedEdges);
		Object[] expectedEdgesArr = (Object[]) expectedEdges.toArray();

		List<_Edge> actualEdges = map.getEdgeList(start, end);
		Collections.sort(actualEdges);
		Object[] actualEdgesArr = (Object[]) actualEdges.toArray();
	    
		assertArrayEquals("Invalid result", expectedEdgesArr, actualEdgesArr);
	}
	
	@Test (expected = InvalidNetWorkException.class)
	public void testExceptions() throws InvalidNetWorkException {
		stationE = map.getNode("Wren Jesse Sam Street");
	}
	
	
}
