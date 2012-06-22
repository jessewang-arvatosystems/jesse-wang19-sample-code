package com.fdm.routeplanner.model.network;


public class Edge implements _Edge {
	
	private _Line line;
	private _Node startStation;
	private _Node endStation;
	private int weight;
	
	@Override
	public _Line getLine() {
		return line;
	}

	@Override
	public void setLine(_Line line) {
		this.line = line;
	}

	@Override
	public _Node getStartNode() {
		return startStation;
	}

	@Override
	public void setStartNode(_Node startStation) {
		this.startStation = startStation;
	}

	@Override
	public _Node getEndNode() {
		return endStation;
	}

	@Override
	public void setEndNode(_Node endStation) {
		this.endStation = endStation;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endStation == null) ? 0 : endStation.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result
				+ ((startStation == null) ? 0 : startStation.hashCode());
		result = prime * result + weight;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (endStation == null) {
			if (other.endStation != null)
				return false;
		} else if (!endStation.equals(other.endStation))
			return false;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (startStation == null) {
			if (other.startStation != null)
				return false;
		} else if (!startStation.equals(other.startStation))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Edge [line=" + line + ", startStation=" + startStation
				+ ", endStation=" + endStation + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(_Edge edge) {
	    int result = this.getLine().compareTo(edge.getLine());
	    return result;
	  }
}
