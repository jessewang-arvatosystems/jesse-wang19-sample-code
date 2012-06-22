package com.fdm.routeplanner.model.routebuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.network._Edge;
import com.fdm.routeplanner.model.network._Line;
import com.fdm.routeplanner.model.network._Node;
import com.fdm.routeplanner.model.network._RoutesMap;

@Service("routeBuilder")
@Scope("request")
final class RouteBuilder implements _RouteBuilder {

	private final _RouteCalculator routeCalculator;
	private _Route route;

	@Autowired
	RouteBuilder(@Value("#{routeCalculator}") _RouteCalculator routeCalculator) {
		this.routeCalculator = routeCalculator;
	}

	public final List<_Route> getRoutes(_Node station1, _Node station2)
			throws RoutePlannerException {

		routeCalculator.execute(station1, station2);
		return arrangeRoute(routeCalculator.getPredecessor(station2));

	}

	public final List<_Route> arrangeRoute(List<List<_Edge>> predecessors) throws RoutePlannerException {
		final List<_Route> routeList = new ArrayList<_Route>();
		for (final List<_Edge> edgeList : predecessors) {

			Thread routeThread = new Thread(new Runnable() {
				public void run() {
					route = findRoute(edgeList);
					synchronized (this) {
						routeList.add(route);
					}
				}
			});
			routeThread.start();
			try {
				routeThread.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}
		return routeList;
	}

	private final _Route findRoute(List<_Edge> predecessors) {
		int minZone;
		Iterator<_Edge> edgeIterator = predecessors.iterator();
		_Route route = null;
		_Leg leg = null;
		_Edge edge = null;
		_Line startLine, nextLine = null;
		edge = edgeIterator.next();
		if (edge.getStartNode().getZone() <= edge.getEndNode().getZone())
			minZone = edge.getStartNode().getZone();
		else
			minZone = edge.getEndNode().getZone();
		startLine = edge.getLine();
		nextLine = startLine;
		leg = new Leg();
		leg.addEdge(edge);
		route = new Route();
		while (edgeIterator.hasNext()) {
			edge = edgeIterator.next();

			if (minZone != 1 && minZone > edge.getStartNode().getZone())
				minZone = edge.getStartNode().getZone();
			if (minZone != 1 && minZone > edge.getEndNode().getZone())
				minZone = edge.getEndNode().getZone();

			startLine = edge.getLine();

			if (nextLine.equals(startLine))
				leg.addEdge(edge);
			else {
				route.addLeg(leg);
				leg = new Leg();
				leg.addEdge(edge);
			}
			nextLine = startLine;
		}

		route.addLeg(leg);
		route.setLowestZone(minZone);
		return route;
	}

	@Override
	public final void setRoutesMap(_RoutesMap routesMap) {
		routeCalculator.setMap(routesMap);
	}

}
