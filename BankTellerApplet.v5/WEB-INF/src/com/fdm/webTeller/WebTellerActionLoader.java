package com.fdm.webTeller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class WebTellerActionLoader {
	
	private static final String FILE_NAME = "resources/BankTellerActions.txt";
	
	private Properties inputProperties = new Properties();
	
	public void connectToBankTellerActions(Properties inputProperties) {
		InputStream in = null;
		
		try {
			in = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
			inputProperties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void loadActions(Map<Integer, String> actions) {

		connectToBankTellerActions(inputProperties);
		
		for (int actionNo = 1; actionNo <= inputProperties.size(); actionNo++) {
			actions.put(actionNo, inputProperties.getProperty("ACTION_CLASS_" + actionNo));
			inputProperties.remove(actionNo);
		}
	
	}
	
}
