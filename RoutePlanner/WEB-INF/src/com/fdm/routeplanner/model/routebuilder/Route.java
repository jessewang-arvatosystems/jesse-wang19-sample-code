package com.fdm.routeplanner.model.routebuilder;

import java.util.ArrayList;
import java.util.List;

import com.fdm.routeplanner.model.network._Node;

class Route implements _Route, Comparable<_Route> {

	private final List<_Leg> legs;
	private int totalStops = 0;
	private int lowestZone;
	
	Route() {
		legs = new ArrayList<_Leg>();
	}

	public final void addLeg(_Leg leg) {
		totalStops += leg.getNumberOfStops();
		legs.add(leg);
	}
	
	public final _Node getRouteStartNode()
	{
		return legs.get(0).getLegStartNode();
	}
	
	public final _Node getRouteEndNode()
	{
		return legs.get(legs.size()-1).getLegEndNode();
	}

	public final int getNumberOfStops(){
		return (totalStops - 1);	
	}
	
	public List<_Leg> getListOfLegs(){
		return legs;
	}
	
	public final int getNumberOfLegs(){
		return legs.size();
	}
	
	@Override
	public int compareTo(_Route route)
	{
		_Route tmp = route;
		if(this.getNumberOfLegs() < tmp.getNumberOfLegs())
		{
			return -1;
		}
		else if(this.getNumberOfLegs() > tmp.getNumberOfLegs())
		{
			return 1;
		}

		return 0;
	}

	@Override
	public String toString() {
		return "RouteImpl [legs=" + legs + ", totalStops=" + totalStops + ", totalTransfers="+ getNumberOfLegs() +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((legs == null) ? 0 : legs.hashCode());
		result = prime * result + totalStops;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Route))
			return false;
		Route other = (Route) obj;
		if (legs == null) {
			if (other.legs != null)
				return false;
		} else if (!legs.equals(other.legs))
			return false;
		if (totalStops != other.totalStops)
			return false;
		return true;
	}

	public void setLowestZone(int lowestZone) {
		this.lowestZone = lowestZone;
	}

	public int getLowestZone() {
		return lowestZone;
	}

}
