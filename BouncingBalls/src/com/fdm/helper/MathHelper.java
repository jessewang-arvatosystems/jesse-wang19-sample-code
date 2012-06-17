package com.fdm.helper;

public class MathHelper {

	public static double RandomNumber(double lowerBounds, double upperBounds) {
		return (((upperBounds-lowerBounds) * Math.random()) + lowerBounds);
	}

	public static int RandomNumber(int lowerBounds, int upperBounds) {
		return (int) (((upperBounds-lowerBounds) * Math.random()) + lowerBounds);
	}
	
}
