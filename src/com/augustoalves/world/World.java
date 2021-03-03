package com.augustoalves.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
  
  private Tile[] tiles;
  public static int WIDTH,HEIGHT;
  
  public World(String path) {
    try {
      BufferedImage map = ImageIO.read(getClass().getResource(path));
      int[] pixels = new int[map.getWidth() * map.getHeight()];
      WIDTH = map.getWidth();
      HEIGHT = map.getHeight();
      tiles = new Tile [map.getWidth() * map.getHeight()];
      map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
      for(int xx = 0; xx < map.getWidth(); xx++) {
        for(int yy = 0; yy < map.getHeight(); yy++) {
          int newPixel = pixels[xx + (yy * map.getWidth())];
          if(newPixel == 0xFF000000) {
            //Floor/Chão
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
          }
          
          
          //WALL TILES AREA
          
          
          else if(newPixel == 0xFFA5A5A5) {
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL_RIGHT);
          }
          else if(newPixel == 0xFFF2F2F2) {
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL_LEFT);
          }
          else if(newPixel == 0xFFBFBFBF) {
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL_DOWN);
          }
          else if(newPixel == 0xFFD8D8D8) {
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL_UP);
          }
          else if(newPixel == 0xFFE5E5E5) {
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL_CORNERLD);
          }
          else if(newPixel == 0xFFFFFFFF) {
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL_CORNERLU);
          }
          else if(newPixel == 0xFF999999) {
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL_CORNERRD);
          }
          else if(newPixel == 0xFFB2B2B2) {
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL_CORNERRU);
          }
          else if(newPixel == 0xFFCCCCCC) {
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_WALL_MIDDLE);
          }
          
          //END OF WALL TILES AREA
          
          
          else if(newPixel == 0xFF0026FF) {
            //Player
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
          }
          else {
            //Floor/Chão
            tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
          }
        }
      }
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  public void render(Graphics g) {
    for (int xx = 0; xx < WIDTH; xx++) {
      for(int yy = 0; yy < HEIGHT; yy++) {
        Tile tile = tiles[xx + (yy*WIDTH)];
        tile.render(g);
      }
    }
  }
}
