package com.fdm.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.fdm.ball.BallManager;
import com.fdm.ball.Ball;

public class BouncingBallFrame extends JFrame {

	private static final long serialVersionUID = -3890663057950214695L;
	
	private final int NUMBER_OF_BALLS = 20;
		
	private BallManager[] ballManagers = new BallManager[NUMBER_OF_BALLS];
	private Thread[] ballThreads = new Thread[NUMBER_OF_BALLS];
	private BufferedImage backbuffer;
	private Graphics2D g2d;
	
	public BouncingBallFrame() {
		super("Bouncing Balls");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setVisible(true);
		intialize();
		animateBallForEachBallManager();
	}
	
	public void paint(Graphics g) {
		g.drawImage(backbuffer, 0, 0, this);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		drawBallForEachBallManager();
		updateBalls();
	}
	
	private void intialize() {
		backbuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
		createBallManagers();
	}

	private void createBallManagers() {
		for (int noBallManager=0; noBallManager<NUMBER_OF_BALLS; noBallManager++) {
			ballManagers[noBallManager] = new BallManager(this, noBallManager);
		}
	}
	
	private void drawBallForEachBallManager() {
		for (int noBallManager=0; noBallManager<NUMBER_OF_BALLS; noBallManager++) {
			ballManagers[noBallManager].drawBall(g2d);
		}
	}
	
	private void animateBallForEachBallManager() {
		for (int noBallManager=0; noBallManager<NUMBER_OF_BALLS; noBallManager++) {
			ballThreads[noBallManager] = new Thread(ballManagers[noBallManager]);
			ballThreads[noBallManager].start();
		}
	}
	
	private void updateBalls() {
		checkCollisions();
		updateBallMovement();
	}
	
	private void updateBallMovement() {
		for (int ballManagerNo=0; ballManagerNo<NUMBER_OF_BALLS; ballManagerNo++) {
			if (ballManagers[ballManagerNo].getBall().getPosX() < 0 || ballManagers[ballManagerNo].getBall().getPosX() 
					> getWidth() - ballManagers[ballManagerNo].getBall().getDiameter())
				ballManagers[ballManagerNo].getBall().setVelX(-ballManagers[ballManagerNo].getBall().getVelX());
			
			if (ballManagers[ballManagerNo].getBall().getPosY() < ballManagers[ballManagerNo].getBall().getDiameter() - 5 || 
					ballManagers[ballManagerNo].getBall().getPosY() > getHeight() - ballManagers[ballManagerNo].getBall().getDiameter())
				ballManagers[ballManagerNo].getBall().setVelY(-ballManagers[ballManagerNo].getBall().getVelY());
			
			ballManagers[ballManagerNo].getBall().incPosX(ballManagers[ballManagerNo].getBall().getVelX());
			ballManagers[ballManagerNo].getBall().incPosY(ballManagers[ballManagerNo].getBall().getVelY());
		}
	}
	
	private void checkCollisions() {
		for (int firstBall=0; firstBall<NUMBER_OF_BALLS; firstBall++) {
			for (int secondBall = firstBall+1; secondBall<NUMBER_OF_BALLS; secondBall++) {
				if (distanceBetweenTwoBalls(ballManagers[firstBall].getBall(),ballManagers[secondBall].getBall()) 
						< (ballManagers[firstBall].getBall().getRadius()+ballManagers[secondBall].getBall().getRadius())) {
					ballManagers[firstBall].getBall().setVelX((-ballManagers[firstBall].getBall().getVelX()));
					ballManagers[firstBall].getBall().setVelY((-ballManagers[firstBall].getBall().getVelY()));
					ballManagers[secondBall].getBall().setVelX((-ballManagers[secondBall].getBall().getVelX()));
					ballManagers[secondBall].getBall().setVelY((-ballManagers[secondBall].getBall().getVelY()));
				}
			}
		}
	}
	
	private double distanceBetweenTwoBalls(Ball ballOne, Ball ballTwo) {	
		double xDist, yDist;
		xDist = ballOne.getPosX()-ballTwo.getPosX();
		yDist = ballOne.getPosY()-ballTwo.getPosY();
		
		return Math.sqrt((Math.pow(xDist, 2) + Math.pow(yDist, 2)));
	}
}
