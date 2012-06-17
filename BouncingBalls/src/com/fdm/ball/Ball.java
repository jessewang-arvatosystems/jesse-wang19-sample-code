package com.fdm.ball;

import java.awt.Color;

public class Ball {

	private double 	posX, posY, velX, velY,
					diameter;
	private Color 	color;
	
	public Ball() {
		posX = 0d;
		posY = 0d;
		velX = 0d;
		velY = 0d;
		diameter = 0d;
		color = Color.WHITE;
	}
	
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public double getVelX() {
		return velX;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public double getDiameter() {
		return diameter;
	}
	public double getRadius() {
		return diameter/2;
	}
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void incPosX(double increment) {
		setPosX(getPosX()+increment);
	}
	public void incPosY(double increment) {
		setPosY(getPosY()+increment);
	}

}
