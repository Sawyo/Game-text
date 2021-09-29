package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning;
	private final int WIDTH=380;
	private final int HEIGHT=220;
	private final int SCALE=2;
	
	private BufferedImage image;
	
	private Spritsheet sheet;
	private BufferedImage bk;

	
	public Game() {
		sheet=new Spritsheet("/background.jpeg");
		bk=sheet.getSprite(0, 0, 380, 220);
		setPreferredSize(new Dimension(WIDTH*SCALE,SCALE*HEIGHT));
		
		initFrame();
		
		image=new BufferedImage(380, 220, BufferedImage.TYPE_INT_RGB);
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
		
		//Render background
		
		g.drawImage(bk, 0, 0, null);
		
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.setColor(Color.white);
		g.drawString("O cão ...", 170, 120);
		
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
