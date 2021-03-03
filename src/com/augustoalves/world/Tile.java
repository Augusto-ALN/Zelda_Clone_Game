package com.augustoalves.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.augustoalves.main.Game;

public class Tile {
  
  public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
  public static BufferedImage TILE_WALL_LEFT = Game.spritesheet.getSprite(0, 64, 16, 16);
  public static BufferedImage TILE_WALL_RIGHT = Game.spritesheet.getSprite(32, 64, 16, 16);
  public static BufferedImage TILE_WALL_UP = Game.spritesheet.getSprite(16, 48, 16, 16);
  public static BufferedImage TILE_WALL_DOWN = Game.spritesheet.getSprite(16, 80, 16, 16);
  public static BufferedImage TILE_WALL_CORNERLD = Game.spritesheet.getSprite(0, 80, 16, 16);
  public static BufferedImage TILE_WALL_CORNERLU = Game.spritesheet.getSprite(0, 48, 16, 16);
  public static BufferedImage TILE_WALL_CORNERRD = Game.spritesheet.getSprite(32, 80, 16, 16);
  public static BufferedImage TILE_WALL_CORNERRU = Game.spritesheet.getSprite(32, 48, 16, 16);
  public static BufferedImage TILE_WALL_MIDDLE = Game.spritesheet.getSprite(16, 64, 16, 16);
  
  private BufferedImage sprite;
  private int x,y;
  
  public Tile(int x, int y, BufferedImage sprite) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
  }
  
  public void render(Graphics g) {
    g.drawImage(sprite, x, y, null);
  }
}
