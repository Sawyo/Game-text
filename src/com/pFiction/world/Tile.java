package com.pFiction.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pFiction.main.Game;

public class Tile {
	
	public static BufferedImage TILE_GRASS = Game.grass.getSprite(0, 0, 32, 32);
	public static BufferedImage TILE_FLOOR = Game.floor.getSprite(0, 1184, 32, 32);
	public static BufferedImage TILE_INTERIOR_WALL_UP = Game.wall.getSprite(520, 1216, 32, 32);
	public static BufferedImage TILE_INTERIOR_WALL_MID = Game.wall.getSprite(520, 1248, 32, 32);
	public static BufferedImage TILE_INTERIOR_WALL_DOWN = Game.wall.getSprite(520, 1280, 32, 32);
	public static BufferedImage TILE_EXTERIOR_WALL_UP = Game.wall.getSprite(261, 1983, 32, 32);
	public static BufferedImage TILE_EXTERIOR_WALL_MID = Game.wall.getSprite(261, 2015, 32, 32);
	public static BufferedImage TILE_EXTERIOR_WALL_DOWN = Game.wall.getSprite(261, 2047, 32, 32);
	public static BufferedImage TILE_TOP_DOWN = Game.wall.getSprite(558, 497, 32, 32);
	
	private BufferedImage sprite;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}

}
