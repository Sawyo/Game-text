package com.pFiction.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pFiction.main.Game;
import com.pFiction.world.tiles.Collision;
import com.pFiction.world.tiles.NotCollision;

public class World {
	
	private Tile[] tiles;
	public static int HEIGHT, WIDTH;
		
	public World(String path) {
		
		try {
			
			BufferedImage map =ImageIO.read(getClass().getResource(path));
			
			int[] pixels = new int [map.getWidth() * map.getHeight()];
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					
					int pixelAtual = pixels[xx + (yy*map.getWidth())];
					
					if (pixelAtual == 0xFF14155E) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_GRASS);
						
					} else if (pixelAtual == 0xFFFFFFFF) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_UP);
						
					} else if (pixelAtual == 0xFF7E7E7E) {
						
						tiles[xx + (yy * WIDTH)] = new NotCollision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_MID);
						
					} else if (pixelAtual == 0xFFC2C2C2) {
						
						tiles[xx + (yy * WIDTH)] = new NotCollision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_DOWN);
						
					} else if (pixelAtual == 0xFFFEF11C) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_EXTERIOR_WALL_UP);
						
					} else if (pixelAtual == 0xFF1EB04E) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_EXTERIOR_WALL_MID);
						
					} else if (pixelAtual == 0xFFB3E529) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_EXTERIOR_WALL_DOWN);
						
					} else if (pixelAtual == 0xFF000000) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_TOP_DOWN);
						
					} else if (pixelAtual == 0xFFFFADC8) {
						//Player
						
						Game.player.setX(xx*64);
						Game.player.setY(yy*64);
						
					} 
					else {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_FLOOR);
					}
					
				}
			}
			}catch(IOException e) {
				e.printStackTrace();
			}
		
	}
	
	public void render(Graphics g) {
		
		for(int xx = 0; xx < WIDTH; xx++) {
			
			for(int yy = 0; yy < HEIGHT; yy++) {
				
				Tile tile = tiles[xx+(yy*WIDTH)];
				tile.render(g);
			}
			
		}
		
	}

}
