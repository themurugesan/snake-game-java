package com.snake;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame =new JFrame();
		
		SnakeGame game= new SnakeGame();
		
		frame.add(game);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		
	
//		frame.setLocation(300, 400);
		
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}

}
