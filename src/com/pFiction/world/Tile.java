package com.pFiction.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pFiction.main.Game;

public class Tile {
	
	//Apontamento dos Diferentes Tiles
	
	//Grass
	public static BufferedImage TILE_GRASS = Game.grass.getSprite(0, 0, 32, 32);
	
	//Floors
	public static BufferedImage TILE_FLOOR = Game.floor.getSprite(0, 1184, 32, 32);
	
	//Tapetes
	public static BufferedImage REDBLUE_TAPETE_UP_LEFT = Game.floor.getSprite( 0, 64, 32, 32);
	public static BufferedImage REDBLUE_TAPETE_UP = Game.floor.getSprite( 32, 64, 32, 32);
	public static BufferedImage REDBLUE_TAPETE_UP_RIGHT = Game.floor.getSprite( 64, 64, 32, 32);
	public static BufferedImage REDBLUE_TAPETE_MID_LEFT = Game.floor.getSprite( 0, 96, 32, 32);
	public static BufferedImage REDBLUE_TAPETE_MID = Game.floor.getSprite( 32, 96, 32, 32);
	public static BufferedImage REDBLUE_TAPETE_MID_RIGHT = Game.floor.getSprite( 64, 96, 32, 32);
	public static BufferedImage REDBLUE_TAPETE_DOWN_LEFT = Game.floor.getSprite( 0, 128, 32, 32);
	public static BufferedImage REDBLUE_TAPETE_DOWN = Game.floor.getSprite(32, 128, 32, 32);
	public static BufferedImage REDBLUE_TAPETE_DOWN_RIGHT = Game.floor.getSprite(64, 128, 32, 32);
	
	//Walls
	public static BufferedImage TILE_INTERIOR_WALL_UP = Game.wall.getSprite(520-6, 1216, 32, 32);
	public static BufferedImage TILE_INTERIOR_WALL_MID = Game.wall.getSprite(520-6, 1248, 32, 32);
	public static BufferedImage TILE_INTERIOR_WALL_DOWN = Game.wall.getSprite(520-6, 1280, 32, 32);
	public static BufferedImage TILE_EXTERIOR_WALL_UP = Game.wall.getSprite(261-6, 1983, 32, 32);
	public static BufferedImage TILE_EXTERIOR_WALL_MID = Game.wall.getSprite(261-6, 2015, 32, 32);
	public static BufferedImage TILE_EXTERIOR_WALL_DOWN = Game.wall.getSprite(261-6, 2047, 32, 32);
	
	//Holes
	public static BufferedImage TILE_TOP_DOWN = Game.wall.getSprite(558, 497, 32, 32);
	
	//Nomeação de Variaveis 
	private BufferedImage sprite;
	private int x, y;
	
	//Metodo Construtor do Tile
	public Tile(int x, int y, BufferedImage sprite) {
		
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		
	}
	
	//Apontando o que será rennderizado
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
