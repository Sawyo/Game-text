package com.pFiction.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pFiction.main.Game;
import com.pFiction.objetos.Entity;
import com.pFiction.objetos.Piano;
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
					
					switch (pixelAtual) {
					case 0xFF13155f: {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_GRASS);
						break;
						
					} case 0xFFffffff: {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_UP);
						break;
						
					} case 0xFF7f7f7f: {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_MID);
						break;
						
					} case 0xFFc3c3c3: {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_DOWN);
						break;
						
					} case 0xFF000000: {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_TOP_DOWN);
						break;
						
					} case 0xFF490252: {
						
						Game.entities.add(new Piano(xx*32, yy*32, 32, 32, Entity.PIANO));
						break;
						
					} default:
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_FLOOR);
						break;
						
					}
					
					/**else if (pixelAtual == 0xFF7f7f7f) {
						
						tiles[xx + (yy * WIDTH)] = new NotCollision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_MID);
						
					} else if (pixelAtual == 0xFFc3c3c3) {
						
						tiles[xx + (yy * WIDTH)] = new NotCollision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_DOWN);
						
					} else if (pixelAtual == 0xFF) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_EXTERIOR_WALL_UP);
						
					} else if (pixelAtual == 0xFF) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_EXTERIOR_WALL_MID);
						
					} else if (pixelAtual == 0xFF) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_EXTERIOR_WALL_DOWN);
						
					} else if (pixelAtual == 0xFF000000) {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_TOP_DOWN);
						
					} else {
						
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_FLOOR);
						
					}*/
					
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
