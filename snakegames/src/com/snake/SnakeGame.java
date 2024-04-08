package com.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JPanel;

public class SnakeGame extends JPanel implements ActionListener,KeyListener {
	
	
	static final int SCREEN_WIDTHX=600;
	static final int SCREEN_HEIGHTY = 600;
	
	static final int SIZE=30;
	
	static final int DEALY=200;
	
	final int[] x = new int[SCREEN_HEIGHTY*SCREEN_WIDTHX];
	final int[] y=new int[SCREEN_HEIGHTY*SCREEN_WIDTHX];
	
	int bodyPart =3;
	int FoodEatern;
	int FoodX;
	int FoodY;
	int score;
	
	int currentDirectionX=1; //Right 
	int currentDirectionY=0; 
	
	boolean running=false;
	Random random;
	Timer timer;
    
	
	public SnakeGame() {
		random=new Random();
		this.setPreferredSize(new Dimension(SCREEN_HEIGHTY,SCREEN_WIDTHX));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(this);
		startGame();
	}
	
	public void startGame() {
		CreateFood();
		running=true;
		timer=new Timer(DEALY,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
			for(int i=0;i<SCREEN_HEIGHTY;i++) {
			g.drawLine(i*SIZE,0,i*SIZE,SCREEN_HEIGHTY);
			g.drawLine(0,i*SIZE,SCREEN_WIDTHX,i*SIZE);
		}
		g.setColor(Color.yellow);
		g.fillOval(FoodX,FoodY,SIZE,SIZE);
		
		for(int i=0;i<bodyPart;i++) {
			g.setColor(Color.red);
			g.fillRect(x[i],y[i],SIZE,SIZE);
			
		}
		
		}
		else {
			GameOver(g);
		}
		
	}
	
	public void GameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Serif",Font.BOLD,75));
		FontMetrics met =getFontMetrics(g.getFont());
		g.drawString("Scroe : "+ score,SCREEN_HEIGHTY/5,SCREEN_WIDTHX/3);
		g.drawString("Game Over : "+ score,SCREEN_HEIGHTY/5,SCREEN_WIDTHX/2);
		
	}
	public void move() {
		for(int i=bodyPart;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
			
			
		}
		x[0]=x[0]+currentDirectionX*SIZE;
		y[0]=y[0]+currentDirectionY*SIZE;
		
	}
	
	public void CreateFood() {
		FoodX =random.nextInt(((int)SCREEN_WIDTHX/SIZE)*SIZE);
		FoodY =random.nextInt(((int)SCREEN_HEIGHTY/SIZE)*SIZE);
		
	}
	
	
	public void CheckFood() {
	    // Calculate the range for food capture
	    int foodRangeX = FoodX + SIZE;
	    int foodRangeY = FoodY + SIZE;
	    
	    // Check if the snake's head position is within the food range
	    if (x[0] >= FoodX && x[0] < foodRangeX && y[0] >= FoodY && y[0] < foodRangeY) {
	        CreateFood();
	        score++;
	        bodyPart++;
	    }
	}

	
	public void CheckCollied() {
		for(int i=bodyPart;i>0;i--) {
			if(x[0] ==x[i] && y[0] ==y[i]) {
				running=false;
			}
			if(x[0]<0) {
				running=false;
			}
			if(x[0] >= SCREEN_WIDTHX) {
				running=false;
			}
			if(y[0]<0) {
				running =false;
			}
			if(y[0]>=SCREEN_HEIGHTY) {
				running=false;
			}
			if(!running) {
				timer.stop();
			}
			
		}
		
	}

	


	@Override
	public void keyPressed(KeyEvent arg0) {
		
		// TODO Auto-generated method stub
		
		int key=arg0.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT:
			if(currentDirectionX !=1) {
				currentDirectionX=-1; //left
				currentDirectionY=0;  //stop up,down move
			}
			
			break;
		case KeyEvent.VK_RIGHT:
			if(currentDirectionX !=-1) {
				currentDirectionX=1; //right
			
										//stop up,down move
				currentDirectionY=0;  
			}
			
		  
			break;
		case KeyEvent.VK_UP:
			if(currentDirectionY !=1) {
				currentDirectionY=-1;  //up 
				currentDirectionX=0;   //stop left and right
			}
			
			break;
			
			
		case KeyEvent.VK_DOWN:
			if(currentDirectionY !=-1) {
				currentDirectionY=1;	//down
			    currentDirectionX=0;	//stop left and right
			}
			
			break;
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(running) {
			move();
			CheckFood();
			CheckCollied();
			
		}
		repaint();
		
	}
	
	

}
