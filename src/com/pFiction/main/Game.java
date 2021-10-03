package com.pFiction.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.pFiction.graficos.Spritsheet;
import com.pFiction.objetos.Entity;
import com.pFiction.objetos.Player;
import com.pFiction.world.World;

public class Game extends Canvas implements Runnable, KeyListener{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	
	private boolean isRunning = true;
	
	//Constantes da Tela
	public final static int HEIGHT = 240;
	public final static int WIDTH = 240;
	public final int SCALE = 3;
	
	//Determinar o Tipo da variavel para a imagem de Fundo Padrão
	private BufferedImage image;
	
	//Variavel para deffinir Entidades
	public static List<Entity> entities;
	
	//Chamada  de dados de imagem com seu metodo construtor
	public static Spritsheet spritesheet;
	public static Spritsheet floor;
    public static Spritsheet grass;
    public static Spritsheet wall;
    public static Spritsheet asset1;
    
    
    public static World world;

	
    public static Player player;
	
    
	public Game() {
		
		//Chamando os Comandos para o Metodo
		this.addKeyListener(this);
		
		//Definindo Tamanho da Tela -  Faz parte do Void initFrame
		setPreferredSize(new Dimension(WIDTH*SCALE,SCALE*HEIGHT));
		initFrame();
		
		//Criando imagem de fundo
		image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		//Criando e Nomeando Array de Entidades
		entities = new ArrayList<Entity>();
		
		//Chamando arquivo de imagem referente a variaveis
		spritesheet = new Spritsheet("/spritesheet.png");
		grass = new Spritsheet("/grass.png");
        floor = new Spritsheet("/floor.png");
        wall = new Spritsheet("/wall.png");
        asset1 = new Spritsheet("/asset1.png");
        
        //Determinando e Adicionando Entidade
        player = new Player(14*32, 22*32, 64, 64, spritesheet.getSprite(0, 0, 64, 64));
		entities.add(player);
		
		//Apontando imagem referente ao Mapa Base
        world = new World("/map.png");
		
	}
	
	
	//Void referente aos dados da tela
	public void initFrame() {
		
		//Cria Tela
		frame=new JFrame("#test game");
		
		//Aponta o que deve ser exibido
		frame.add(this);
		
		//Determina se o usuario pode alterar ou não o tamanho da tela
		frame.setResizable(false);
		
		//Responsavel por compactar a janela para o tamanho do monitor
		frame.pack();
		
		//Determina a localização da tela no monitor
		frame.setLocationRelativeTo(null);
		
		//Faz os processos pararem assim que a tela for fechada
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Torna tela visivel
		frame.setVisible(true);
		
	}
	
	
	//Synchonized serve para garantir o sincronismo do codigo quando este executar Threads
	//Em resumo. Fazer com que mais de uma thread executar o codigo este não possa para evitar possiveis problemas.
	
	
	public synchronized void start() {
		
		thread=new Thread(this);
		isRunning=true;
		thread.start();
		
	}
	
	public synchronized void stop() {
		isRunning=false;
		try {
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
	//Chama o metodo game para ser rodado pela Thread.
	
	
	public static void main(String args[]) {
		
		Game game=new Game();
		game.start();
	}
	
	
	//Local onde colocamos as mecanicas do player
	
	
	public void tick() {
		
		//Laço feito para a adição de entidades
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			
			/*if (e instanceof Player) {
				//Estou dando tick no Player
			}*/
			
			e.tick();
		}
		
	}
	
	
	//Local onde colocamos o que será renderizado em tela
	
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//Criação da Imagem de Fundo
		Graphics g = image.getGraphics();
		g.setColor(new Color(40,40,40));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Renderização da Classe Mundo
		world.render(g);
		
		//Renderização das Entidades
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		g.dispose();
		g=bs.getDrawGraphics();
		g.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
		bs.show();
		
	}
	
	
	//Void com loop que encerra quando isRunning = false chamando a thread stop()
	
	
	public void run() {
		
		long lastTime=System.nanoTime();
		double amountOfTicks=60.0;
		double ns=1000000000/amountOfTicks;
		double delta=0;
		while(isRunning) {
			
			long now=System.nanoTime();
			delta+= (now-lastTime)/ns;
			
			if(delta>=1) {
				tick();
				render();
			}
		}
		
		stop();
		
	}
	
	
	//Inputs do Jogo
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
	}

}

