package com.fdm.routeplanner.model.zonereader;

import java.util.Map;
import com.fdm.routeplanner.exception.RoutePlannerException;

public interface _ZoneDataReader {
	public abstract void readFile() throws RoutePlannerException;
	public abstract Map<String,Integer> getMap();
}
