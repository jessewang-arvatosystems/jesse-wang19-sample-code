package com.fdm.routeplanner.model.network;


public class TrainLine implements _Line{
	
	private final String name;

	public TrainLine(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TrainLine other = (TrainLine) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrainLine [name=" + name + "]";
	}
	
	@Override
	public int compareTo(_Line line) {
	    int result = this.getName().compareTo(line.getName());
	    return result;
	  }
}
