package com.fdm.helper;

public final class MathHelper {
	
	public static double roundToTwoDecimalPlaces(double number) {
		
		return Math.round(number*100d)/100d;
	}
	
}
