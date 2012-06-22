package com.fdm.routeplanner.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fdm.routeplanner.exception.InvalidNetWorkException;
import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.zonereader.ZoneDataReader;
import com.fdm.routeplanner.model.zonereader._ZoneDataReader;

public class TestZoneMapReader 
{
	private Map<String, Integer> map = new HashMap<String, Integer>();
	private _ZoneDataReader zoneDataReader;

	
	@Test
	public void testGetMap() throws RoutePlannerException 
	{
		zoneDataReader = new ZoneDataReader(map, "zone_data2.dat");
		zoneDataReader.readFile();
		map=zoneDataReader.getMap();
		assertTrue(map.containsKey("BANK"));
		assertTrue(map.containsValue(1));
		assertFalse(map.containsKey("head"));
		assertFalse(map.containsValue(11));
	}
	
	@Test (expected=InvalidNetWorkException.class)
	public void testRoutePlannerException() throws RoutePlannerException 
	{
		zoneDataReader = new ZoneDataReader(map, "zone_data.dat");
		zoneDataReader.readFile();
		map=zoneDataReader.getMap();
		assertTrue(map.containsKey("BANK"));
		assertTrue(map.containsValue(1));
	}

}
