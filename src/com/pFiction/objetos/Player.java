package com.pFiction.objetos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import com.pFiction.main.Game;

import com.pFiction.main.Game;

public class Player extends Entity {
	
	
	public boolean right, up, down, left;
	public double speed = 0.25;
	
	private int frames = 0, maxFrames = 26, index = 0, maxindex = 24;
	private boolean moved = false;
	
	private BufferedImage [] rightplayer;
	private BufferedImage [] leftplayer;
	private BufferedImage [] upplayer;
	private BufferedImage [] downplayer;
	private BufferedImage [] uprightplayer;
	private BufferedImage [] upleftplayer;
	private BufferedImage [] downrightplayer;
	private BufferedImage [] downleftplayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		
		super(x, y, width, height, sprite);
		
		rightplayer = new BufferedImage[25];
		leftplayer = new BufferedImage[25];
		upplayer = new BufferedImage[25];
		downplayer = new BufferedImage[25];
		uprightplayer = new BufferedImage[25];
		upleftplayer = new BufferedImage[25];
		downrightplayer = new BufferedImage[25];
		downleftplayer = new BufferedImage[25];
		
		for (int i = 0; i < 25; i++) {
			leftplayer[i] = Game.spritesheet.getSprite(0 + (i*64), 256, 64, 64);
		}
		for (int i = 0; i < 25; i++) {
			rightplayer[i] = Game.spritesheet.getSprite(0 + (i*64), 192, 64, 64);
		}
		for (int i = 0; i < 25; i++) {
			upplayer[i] = Game.spritesheet.getSprite(0 + (i*64), 0, 64, 64);
		}
		for (int i = 0; i < 25; i++) {
			downplayer[i] = Game.spritesheet.getSprite(0 + (i*64), 448, 64, 64);
		}
		for (int i = 0; i < 25; i++) {
			downleftplayer[i] = Game.spritesheet.getSprite(0 + (i*64), 384, 64, 64);
		}
		for (int i = 0; i < 25; i++) {
			downrightplayer[i] = Game.spritesheet.getSprite(0 + (i*64), 320, 64, 64);
		}
		for (int i = 0; i < 25; i++) {
			upleftplayer[i] = Game.spritesheet.getSprite(0 + (i*64), 128, 64, 64);
		}
		for (int i = 0; i < 25; i++) {
			uprightplayer[i] = Game.spritesheet.getSprite(0 + (i*64), 64, 64, 64);
		}
		
	}
	
	public void tick() {
		
		moved = false;
		
		if(left && down) {
			
			moved = true;
			x-=speed/2;
			y+=speed/2;
			
		} else if(right && down) {
			
			moved = true;
			x+=speed/2;
			y+=speed/2;
			
		}else if(up && left) {
			
			moved = true;
			x-=speed/2;
			y-=speed/2;
			
		}else if(up && right) {
			
			moved = true;
			x+=speed/2;
			y-=speed/2;
			
		}else if(right) {
			
			moved = true;
			x+=speed;
			
		}else if(left) {
			
			moved = true;
			x-=speed;
			
		}else if(up) {
			
			moved = true;
			y-=speed;
			
		}else if(down) {
			
			moved = true;
			y+=speed;
			
		}
		
		if (moved) {
			
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxindex) {
					index = 0;
				}
			}
			
		}
			
	}
	
	public void render(Graphics g) {
		
		if (left && down) {
			g.drawImage(downleftplayer[index], this.getX(), this.getY(), null);
		} else if(right && down) {
			g.drawImage(downrightplayer[index], this.getX(), this.getY(), null);
		}else if(up && left) {
			g.drawImage(upleftplayer[index], this.getX(), this.getY(), null);
		}else if(up && right) {
			g.drawImage(uprightplayer[index], this.getX(), this.getY(), null);
		} else if(right) {
			g.drawImage(rightplayer[index], this.getX(), this.getY(), null);
		} else if (left) {
			g.drawImage(leftplayer[index], this.getX(), this.getY(), null);
		} else if (up) {
			g.drawImage(upplayer[index], this.getX(), this.getY(), null);
		} else if (down) {
			g.drawImage(downplayer[index], this.getX(), this.getY(), null);
		} else {
			g.drawImage(downplayer[1], this.getX(), this.getY(), null);
		}
	}

}
