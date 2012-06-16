package com.fdm.routeplanner.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.engine.DijkstraEngine;
import com.fdm.routeplanner.model.engine._DijkstraEngine;
import com.fdm.routeplanner.model.network.EdgeMapBuilder;
import com.fdm.routeplanner.model.network._MapBuilder;
import com.fdm.routeplanner.model.network._Node;
import com.fdm.routeplanner.model.network._RoutesMap;

final class DijkstraTest {
	
	private _DijkstraEngine engine= new DijkstraEngine();
	private _RoutesMap map; 
	
	@Before
	public void setUp() throws RoutePlannerException
	{	
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		_MapBuilder mapBean = (EdgeMapBuilder) context.getBean("edgeMapBuilder");
		map = mapBean.buildRoutesMap();		
	}
	@Test
	public void testExecute() throws RoutePlannerException 
	{
		_Node start= map.getNode("Edgeware");
		_Node end= map.getNode("Bank");
		_Node tempStation1= map.getNode("Camden");
		_Node tempStation2= map.getNode("Angel");
		engine.setMap(map);
		engine.execute(start, end);
		assertTrue(engine.getPredecessor(end).get(3).contains(map.getEdgeList(start, tempStation1).get(1)));
		assertTrue(engine.getPredecessor(end).get(3).contains(map.getEdgeList(tempStation2,end).get(0)));
	}

	@Test
	public void testGetShortestDistance() throws RoutePlannerException 
	{
		_Node start= map.getNode("Edgeware");
		_Node end= map.getNode("Bank");
		_Node tempStation1= map.getNode("Camden");
		_Node tempStation2= map.getNode("Leicester Square");
		engine.setMap(map);
		engine.execute(start, end);
		assertEquals(engine.getShortestDistance(start),0);
		assertEquals(engine.getShortestDistance(tempStation1),1);
		assertEquals(engine.getShortestDistance(tempStation2),3);
		assertEquals(engine.getShortestDistance(end),4);
	}

	@Test
	public void testGetPredecessor() throws RoutePlannerException 
	{
		_Node start= map.getNode("Edgeware");
		_Node end= map.getNode("Bank");
		_Node tempStation1= map.getNode("Camden");
		_Node tempStation2= map.getNode("Kings Cross");
		_Node tempStation3= map.getNode("Angel");
		engine.setMap(map);
		engine.execute(start, end);
		assertTrue(engine.getPredecessor(end).get(3).contains(map.getEdgeList(start, tempStation1).get(1)));
		assertTrue(engine.getPredecessor(end).get(3).contains(map.getEdgeList(tempStation1, tempStation2).get(0)));
		assertTrue(engine.getPredecessor(end).get(3).contains(map.getEdgeList(tempStation2, tempStation3).get(0)));
		assertTrue(engine.getPredecessor(end).get(3).contains(map.getEdgeList(tempStation3,end).get(0)));
	}
	
	@Test (expected=RoutePlannerException .class)
	public void testInvalidNetworkException() throws RoutePlannerException 
	{
		_Node start= map.getNode("Edgeware");
		_Node end= map.getNode("Bank");
		_Node invalidStation= map.getNode("Line");
		engine.setMap(map);
		engine.execute(start, end);
		engine.getPredecessor(invalidStation);
	}
}
