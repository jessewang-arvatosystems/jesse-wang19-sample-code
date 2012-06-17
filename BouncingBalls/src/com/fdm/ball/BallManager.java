package com.fdm.ball;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import com.fdm.helper.MathHelper;

public class BallManager implements Runnable {

	private final static long DELAY = 20;
	
	private final double 	MIN_DIAMETER = 30, MAX_DIAMETER = 50,
							MIN_VEL = 1, MAX_VEL = 3;
	
	private Ball ball;
	private int ballNo;
	private Component component;
	
	public BallManager(Component component, int ballNo) {
		this.component = component;
		this.ballNo = ballNo;
		ball = new Ball();
		setupBall();
	}
	
	public void setupBallPosition() {
		int xPos, yPos,
			column, row;
		
			column = ballNo%9;
			row = ballNo%4;
			
			xPos = component.getWidth()/10 + component.getWidth()/10*column;
			yPos = component.getHeight()/5 + component.getHeight()/5*row;
			
			ball.setPosX(xPos);
			ball.setPosY(yPos);
	}
	
	public void setupBall() {
		ball.setDiameter(MathHelper.RandomNumber(MIN_DIAMETER, MAX_DIAMETER));
		setupBallPosition();
		ball.setVelX(MathHelper.RandomNumber(MIN_VEL, MAX_VEL));
		ball.setVelY(MathHelper.RandomNumber(MIN_VEL, MAX_VEL));
		ball.setColor(new Color(new Random().nextInt()));
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public void drawBall(Graphics2D g2d) {
		g2d.setColor(ball.getColor());
		g2d.fill(new Ellipse2D.Double(ball.getPosX(), ball.getPosY(), ball.getDiameter(), ball.getDiameter()));
	}
	
	public void run() {
		while(Thread.currentThread().isAlive()) {
			try {
				Thread.sleep(DELAY);
				component.repaint();
			} catch (Exception ex) {}
		}
	}

}
