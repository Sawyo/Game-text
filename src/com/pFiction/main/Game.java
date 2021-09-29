package com.pFiction.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.pFiction.graficos.Spritsheet;
import com.pFiction.objetos.Entity;
import com.pFiction.objetos.Player;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	
	private boolean isRunning = true;
	
	private final int HEIGHT = 768;
	private final int WIDTH = 768;
	private final int SCALE = 1;
	
	private BufferedImage image;
	
	public List<Entity> entities;
	public Spritsheet spritesheet;

	
	public Game() {
		
		
		setPreferredSize(new Dimension(WIDTH*SCALE,SCALE*HEIGHT));
		initFrame();
		
		image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritsheet("/spritesheet.png");
		
		Player player = new Player(0, 0, 64, 64, spritesheet.getSprite(0, 0, 64, 64));
		entities.add(player);
		
	}
	
	public void initFrame() {
		frame=new JFrame("#test game");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread=new Thread(this);
		isRunning=true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning=false;
		try {
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		
		Game game=new Game();
		game.start();
	}
	
	public void tick() {
		
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
	}
	
	public void render() {
		
		BufferStrategy bs=this.getBufferStrategy();
		
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g=image.getGraphics();
		g.setColor(new Color(40,40,40));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		g.dispose();
		g=bs.getDrawGraphics();
		g.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
		bs.show();
		
	}
	
	public void run() {
		long lastTime=System.nanoTime();
		double amountOfTicks=60.0;
		double ns=1000000000/amountOfTicks;
		double delta=0;
		while(isRunning) {
			
			long now=System.nanoTime();
			delta+= (now-lastTime)/ns;
			
			if(delta>=1) {
				tick();
				render();
			}
		}
		stop();
	}

}

