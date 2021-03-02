package com.augustoalves.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.augustoalves.entities.Entity;
import com.augustoalves.entities.Player;
import com.augustoalves.graphics.Spritesheet;

public class Game extends Canvas implements Runnable,KeyListener{

  
  private static final long serialVersionUID = 1L;
  public static JFrame frame;
  private Thread thread;
  private boolean isRunning = true;
  private final int WIDTH = 300;
  private final int HEIGHT = 200;
  private final int SCALE = 3;
  
  private BufferedImage image;
  
  public List<Entity> entities;
  public Spritesheet spritesheet;
  
  public Game() {
    this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
    initFrame();
    //Inicializando objetos.
    image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    entities = new ArrayList<Entity>();
    spritesheet = new Spritesheet("/Spritesheet.png");
    
    Player player = new Player(0,0,16,16,spritesheet.getSprite(32,0,16,16));
    entities.add(player);
  }
  
  public void initFrame(){
    frame = new JFrame("game1");
    frame.add(this);
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  
  public synchronized void start() {
    thread = new Thread(this);
    isRunning = true;
    thread.start();
  }
  public synchronized void stop() {
    isRunning = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String args[]) {
    Game game = new Game();
    game.start();
  }
  
  public void tick() {
    for(int i = 0; i < entities.size(); i++){
      Entity e = entities.get(i);
      e.tick();
    }
  }
  public void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = image.getGraphics();
    g.setColor(new Color (19, 19, 19));
    g.fillRect(0, 0, WIDTH, HEIGHT);
    /*Renderiza��o do Jogo*/
    /***/
    for(int i = 0; i < entities.size(); i++){
      Entity e = entities.get(i);
      e.render(g);
    }
    g.dispose();
    g = bs.getDrawGraphics();
    g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE,null);
    bs.show();
  }
  
  public void run() {
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    int frames = 0;
    double timer = System.currentTimeMillis();
    
    while(isRunning) {
      long now = System.nanoTime();
      delta+= (now - lastTime) / ns;
      lastTime = now;
      if(delta >= 1) {
        tick();
        render();
        frames++;
        delta--;
      }
      if(System.currentTimeMillis() - timer >= 1000) {
        System.out.println("FPS: " + frames);
        frames = 0;
        timer += 1000;
      }
    }
    stop();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }
}