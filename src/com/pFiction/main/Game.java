package com.pFiction.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.pFiction.graficos.Spritsheet;
import com.pFiction.objetos.Entity;
import com.pFiction.objetos.Player;
import com.pFiction.world.World;

public class Game extends Canvas implements Runnable, KeyListener{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	
	private boolean isRunning = true;
	
	private final int HEIGHT = 761;
	private final int WIDTH = 761;
	private final int SCALE = 1;
	
	private BufferedImage image;
	
	public List<Entity> entities;
	public static Spritsheet spritesheet;
	public static Spritsheet floor;
    public static Spritsheet grass;
    public static Spritsheet wall;
    
    public static World world;

	public static Player player;
	
	public Game() {
		
		this.addKeyListener(this);
		
		setPreferredSize(new Dimension(WIDTH*SCALE,SCALE*HEIGHT));
		initFrame();
		
		image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritsheet("/spritesheet.png");
		
		player = new Player(0, 0, 64, 64, spritesheet.getSprite(0, 0, 64, 64));
		entities.add(player);
		
		grass = new Spritsheet("/grass.png");
        floor = new Spritsheet("/floor.png");
        wall = new Spritsheet("/wall.png");
        
        world = new World("/map.png");
		
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
			
			/*if (e instanceof Player) {
				//Estou dando tick no Player
			}*/
			
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
		
		world.render(g);
		
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

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
	}

}

