package com.fdm.routeplanner.model.network;


class Station implements _Node {
	
	private final String name;
	private final int zone;
	
	Station(String name, int zone)
	{
		this.name= name;
		this.zone= zone;
	}

	public String getName() {
		return name;
	}
	
	public int getZone()
	{
		return zone;
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
		Station other = (Station) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + "]";
	}
	
	@Override
	public int compareTo(_Node station) {
	    int result = this.getName().compareTo(station.getName());
	    return result;
	  }

}
