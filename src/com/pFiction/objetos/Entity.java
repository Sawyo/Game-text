package com.pFiction.objetos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pFiction.main.Game;
import com.pFiction.world.Camera;

public class Entity {
	
	
	// Entidades com sua localização especificada
	public static BufferedImage PIANO = Game.asset1.getSprite(540, 0, 120, 98);
	
	
	// Nomeação de Variaveis
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	private BufferedImage sprite;
	
	
	// Metodo contrutor que será chamada pelas entidades que herdarão esta informação
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
	}
	
	
	//Criação de Getters e Setters para Acessar ou modificar variaveis (Regra Basica do Encapsulamento)
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
	
	
	// Mecanica das Entidades
	public void tick() {
		
	}
	
	
	//Responsavel por exibir/desenhar a entidade na tela.
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX() - Camera.x, this.getY() - Camera.y,null);
	}

}
