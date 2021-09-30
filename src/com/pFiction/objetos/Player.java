package com.pFiction.objetos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pFiction.main.Game;

public class Player extends Entity {
	
	public boolean right,up,left,down;
	public int dir_right=0,dir_left=1;
	public int dir=dir_right;
	public double speed=1.4;
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;


	private int frames=0,maxFrames=5,index=0,maxIndex=3;
	private boolean moved=false;


	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer =new BufferedImage[4];
		leftPlayer =new BufferedImage[4];
		
		for (int i=0;i<4;i++) {
		rightPlayer[0]=Game.spritesheet.getSprite(60*i, 65*i, 64, 64);
		}
		for (int i=0;i<4;i++) {
			leftPlayer[0]=Game.spritesheet.getSprite(80*i, 86*i, 64, 64);
			}
		
	}
	
	public void tick() {
		moved=false;
		if(right) {
			moved=true;
			dir=dir_right;
			x+=speed;
		}
		else if(left) {
			moved=true;
			dir=dir_left;
			x-=speed;
		}
		if(up) {
			moved=true;
			y-=speed;
		}
		else if(down) {
			moved=true;
			y+=speed;
		}
		/*if(moved) {
			frames++;
			if(frames==maxFrames) {
				frames=0;
				index++;
				if(index>maxIndex) {
					index=0;
				}
			}
		}*/
	}
	
	public void render(Graphics g) {
		if(dir==dir_right)
		g.drawImage(rightPlayer[index],this.getX(),this.getY(),null);
		else if(dir==dir_left)
			g.drawImage(leftPlayer[index],this.getX(),this.getY(),null);


	}

}
