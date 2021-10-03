package com.pFiction.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pFiction.main.Game;
import com.pFiction.world.tiles.Collision;

public class World {
	
	//Criação do array conjunto dos Tiles que serão obtidos
	private Tile[] tiles;
	
	//Nomeação de Variavel
	public static int HEIGHT, WIDTH;
		
	public World(String path) {
		
		try {
			
			// Apontando uma imagem que será usada de base para renderizar os tiles
			BufferedImage map =ImageIO.read(getClass().getResource(path));
			
			// Codigo de medida da immagem utilizada para a renderiazação do mundo
			int[] pixels = new int [map.getWidth() * map.getHeight()];
			
			// Dando valor a variaveis nomeadas anteriormente
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			//Chamada da classe como variavel onde apontaremos as medidas que serão relacionadas aos pixels
			tiles = new Tile[map.getWidth() * map.getHeight()];
			
			//Codigo para mapear as cores no mapa
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			//Loop responsavel por apontar as cores e os tiles que se relacionarão assim como as entidades
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					
					//Criação de varivel para facilitar questão logica
					int pixelAtual = pixels[xx + (yy*map.getWidth())];
					
					// Utilização do switch por carater de otimização
					switch (pixelAtual) {
					
					//Foliage and Grass
					
					case 0xFF13155f: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_GRASS);
						break;
					} 
					
					//Interior Walls and Your Parts
					
					case 0xFFffffff: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_UP);
						break;
					} case 0xFF7f7f7f: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_MID);
						break;
					} case 0xFFc3c3c3: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_INTERIOR_WALL_DOWN);
						break;
					} 
					
					// Tapetes
					
					case 0xFF00004B: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.REDBLUE_TAPETE_UP_LEFT);
						break;
					} case 0xFF0000E1: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.REDBLUE_TAPETE_UP);
						break;
					} case 0xFF000064: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.REDBLUE_TAPETE_UP_RIGHT);
						break;
					} case 0xFF000096: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.REDBLUE_TAPETE_MID_LEFT);
						break;
					} case 0xFF0000AF: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.REDBLUE_TAPETE_MID);
						break;
					} case 0xFF0000C8: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.REDBLUE_TAPETE_MID_RIGHT);
						break;
					} case 0xFF000019: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.REDBLUE_TAPETE_DOWN_LEFT);
						break;
					} case 0xFF00007D: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.REDBLUE_TAPETE_DOWN);
						break;
					} case 0xFF000032: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.REDBLUE_TAPETE_DOWN_RIGHT);
						break;
					} 
					
					// Separates and Holes
					
					case 0xFF000000: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_TOP_DOWN);
						break;
					} 
					
					//Player Location
					
					case 0xFF490252: {
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_FLOOR);
						Game.player.setX(xx*32-16);
						Game.player.setY(yy*32);
						break;
					} 
					
					//Padrão
					
					default:
						tiles[xx + (yy * WIDTH)] = new Collision(xx*32, yy*32, Tile.TILE_FLOOR);
						break;
					}
					
				}
			}
			}catch(IOException e) {
				e.printStackTrace();
			}
		
	}
	
	// Apontando que Tiles serão exibidos na tela.
	public void render(Graphics g) {
		
		for(int xx = 0; xx < WIDTH; xx++) {
			
			for(int yy = 0; yy < HEIGHT; yy++) {
				
				Tile tile = tiles[xx+(yy*WIDTH)];
				tile.render(g);
			}
			
		}
		
	}

}
