package com.fdm.routeplanner.model.zonereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.exception.InvalidNetWorkException;

@Service("zoneReader")
public final class ZoneDataReader implements _ZoneDataReader 
{
	
	private final Map<String, Integer> zoneMap;
	private final String fileName;
	
	@Autowired
	public ZoneDataReader(	@Value("#{hashMap}") Map<String, Integer> zoneMap, 
							@Value("$file{zone_filename}") String fileName) {
		this.zoneMap = zoneMap;
		this.fileName = fileName;
	}
	
	@Override
	public final void readFile() throws InvalidNetWorkException
	{
		BufferedReader reader=null;
		String lineArray[]=null;
		String station=null;
		String line=null;
		Integer zone=null;
		
		try 
		{
			reader= new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)));
			while((line=reader.readLine())!=null)
			{
				String delimiter=":";
				lineArray=line.split(delimiter,2);
				station= lineArray[0];
				zone=Integer.parseInt(lineArray[1]);
				zoneMap.put(station, zone);
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			throw new InvalidNetWorkException(e, "Zone Mapping File Not Found.");
		}
		catch (IOException e) 
		{
			throw new InvalidNetWorkException(e, "Unable To Read Zone Mapping File.");
		}
	}
	
	@Override
	public Map<String,Integer> getMap()
	{
		return zoneMap;
	}
	
}
