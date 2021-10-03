package com.pFiction.objetos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pFiction.main.Game;

public class Entity {
	
	public static BufferedImage PIANO = Game.asset1.getSprite(560, 0, 120, 98);
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
	}
	
	public void setX(int newX) {
		
		this.x = (int)newX;
		
	}
	
	public void setY(int newY) {
		this.y = (int)newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX(),this.getY(),null);
	}

}
