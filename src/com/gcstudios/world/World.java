package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
	
	private Tile tiles[];
	public static  int WIDTH,HEIGHT;
	
	
	public World(String path) {
		try {
		BufferedImage map=ImageIO.read(getClass().getResource(path));
		WIDTH=map.getWidth();
		HEIGHT=map.getHeight();
		tiles=new Tile[map.getWidth()*map.getHeight()];
		int pixels[]=new int[map.getWidth()*map.getHeight()];
		map.getRGB(0, 0,map.getWidth(),map.getHeight(),pixels ,0,map.getWidth());
		
		for(int xx=0;xx<pixels.length;xx++) {
			
			for(int yy=0;yy<pixels.length;yy++) {
				int pixel_atual=pixels[xx +(yy*map.getWidth())];
				if(pixel_atual==0xFFFF0000) {
					//ex: chao
					
				}else if(pixel_atual==0xFFFFFFFF) {
					// ex: parede
				}
				
			}
			
		}
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void render(Graphics g) {
		for(int xx=0;xx<WIDTH;xx++) {
			for(int yy=0;yy<WIDTH;yy++) {
				Tile tile=tiles[xx=(yy*WIDTH)];
				tile.render(g);
			}
		}
	}

}
