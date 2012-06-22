package com.fdm.routeplanner.model.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.linereader._LineDataReader;
import com.fdm.routeplanner.model.zonereader._ZoneDataReader;

@Service("edgeMapBuilder")
public class EdgeMapBuilder implements _MapBuilder{
	
	private final _LineDataReader reader;
	private final String file;
	private final _RoutesMap map;
	private final HashMap<String, _Node> existingStations;
	private final _ZoneDataReader zoneReader;
	private Map<String, Integer> zoneMap;

	@Autowired
	public EdgeMapBuilder(	@Value("#{lineDataReader}") _LineDataReader reader,
							@Value("$file{train_filename}") String file,
							@Value("#{edgeMap}") _RoutesMap map,
							@Value("#{hashMap}") HashMap<String, _Node> exStations,
							@Value("#{zoneReader}") _ZoneDataReader zoneReader) {
		this.reader = reader;
		this.file = file;
		this.map = map;
		existingStations = exStations;
		this.zoneReader = zoneReader;
	}

	/**
	 * Retrieves the node object with the name stationName from the routes network 
	 * or creates a new object
	 * @param stationName is the Station name string
	 * @return the object holding the station 
	 */
	private final _Node getExistingNodeOrCreateNew(String stationName) 
	{
		_Node st = null;
	
		if(existingStations.containsKey(stationName)) {
			st = existingStations.get(stationName);
		} else {
			if(zoneMap.containsKey(stationName.toUpperCase())) {
				st = new Station(stationName, zoneMap.get(stationName.toUpperCase()));
			} else {
				st = new Station(stationName, 0);
			}
			existingStations.put(st.getName(), st);
		}
		return st;
	}

	/**
	 * Constructs the graph holding the network structure 
	 * @return a _RoutesMap object representing the routes network
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final _RoutesMap buildRoutesMap() throws RoutePlannerException {
		
		zoneReader.readFile();
		zoneMap = zoneReader.getMap();
		
		HashMap<String, ArrayList<String>> linesStationsMap;
		linesStationsMap = (HashMap<String, ArrayList<String>>) reader.getNetworkData(getClass().getClassLoader().getResourceAsStream(file));	
		Set<String>	linesSet = linesStationsMap.keySet();
		Iterator<String> linesSetIterator = linesSet.iterator();
		ArrayList<String> stations;
		
		int numOfStations;
		while(linesSetIterator.hasNext()) {
			String stationLine = linesSetIterator.next();
			stations = linesStationsMap.get(stationLine);
			numOfStations = stations.size();
			_Line line = new TrainLine(stationLine);
			for (int i = 0; i < numOfStations-1; i++) {
				_Node start = getExistingNodeOrCreateNew(stations.get(i));
				_Node end = getExistingNodeOrCreateNew(stations.get(i+1));
				int dist = 1;
				map.addDirectRoute(start, end, dist, line);
				map.addDirectRoute(end, start, dist, line);	
			}
		}
		return map;
	}

}
