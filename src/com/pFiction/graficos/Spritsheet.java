package com.pFiction.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritsheet {

	private BufferedImage spritesheet;
	
	
	//Metodo criado para puxar imagens de uma pasta
	
	
	public Spritsheet(String path)
	{
		try {
			spritesheet=ImageIO.read(getClass().getResource(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//Metodo para percorrer pontos dterminados da imagem e pegar a informação
	
	
	public BufferedImage getSprite(int x,int y,int width,int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
}
