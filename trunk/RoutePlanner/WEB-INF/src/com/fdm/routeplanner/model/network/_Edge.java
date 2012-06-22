package com.fdm.routeplanner.model.network;


public interface _Edge extends Comparable<_Edge> {
	
	/**
	 * @return line
	 */
	public abstract _Line getLine();
	
	/**
	 * @param line
	 */
	public abstract void setLine(_Line line);
	
	/**
	 * @return startNode
	 */
	public abstract _Node getStartNode();
	
	/**
	 * @param startNode
	 */
	public abstract void setStartNode(_Node startNode);
	
	/**
	 * @return endNode
	 */
	public abstract _Node getEndNode();
	
	/**
	 * @param endNode
	 */
	public abstract void setEndNode(_Node endNode);
	
	/**
	 * @return weight
	 */
	public abstract int getWeight();
	
	/**
	 * @param weight
	 */
	public abstract void setWeight(int weight);
	
	/**
	 * @return hashcode of edge object
	 */
	public abstract int hashCode();
	
	/**
	 * Tests if edge belongs to route network
	 * @param obj
	 * @return 
	 */
	public abstract boolean equals(Object obj);
}
