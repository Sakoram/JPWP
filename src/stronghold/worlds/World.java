package stronghold.worlds;

import java.awt.Graphics;
import java.awt.Point;
import java.util.List;


import stronghold.Handler;
import stronghold.entities.EntityManager;
import stronghold.entities.GridNode;
import stronghold.entities.creatures.Player;
import stronghold.entities.objects.Arrow;
import stronghold.entities.statics.Tree;
import stronghold.entities.statics.buildings.*;
import stronghold.entities.units.*;
import stronghold.tiles.Tile;
import stronghold.utils.Utils;


public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private GridNode[][] grid;
	//private int[][] grid;
	private EntityManager entityManager;
	
	public World(Handler handler, String path){
		this.handler = handler;
		loadWorld(path);
		
	}
	
	public void init() {
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		handler.getMouseManager().setEntityManager(entityManager);
		// Temporary entity code!
		entityManager.addEntity(new Tree(handler, 2, 2));
		entityManager.addEntity(new Arrow(handler, 3, 2));
		//entityManager.addEntity(new Knight(handler, 150, 500));
		entityManager.addEntity(new Gimli(handler, 5, 2,true));
		entityManager.addEntity(new Archer(handler, 5, 3,true));
		entityManager.addEntity(new Worker(handler, 5, 4,true));
		entityManager.addEntity(new Spearman(handler, 6, 2,true));
		entityManager.addEntity(new King(handler, 6, 3,true));
		entityManager.addEntity(new Knight(handler, 9, 9,true));
		entityManager.addEntity(new Knight(handler, 12, 12,false));
		for(int i=3;i<9;i++) {
			entityManager.addEntity(new Wall(handler, i, 3));
			entityManager.addEntity(new Worker(handler, i, 8,true));
			entityManager.addEntity(new Worker(handler, 8, i,true));
			entityManager.addEntity(new Wall(handler, 8, i));
		}
		entityManager.addEntity(new Stairs(handler, 4, 4));
		entityManager.addEntity(new Gate(handler, 7, 10,true));
		entityManager.addEntity(new Tower(handler, 10, 10));
		
		//System.out.print(new Wall(handler, 20, 20).getHealth());
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
	}
	public void tick(){
		handler.getGameCamera().adjustGameCamera();
		entityManager.tick();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.BedrockTile;
		
		//Tile t = Tile.tiles[grid[x][y]]; //.getTileId()
		Tile t = Tile.tiles[grid[x][y].getTileId()];
		if(t == null)
			return Tile.BedrockTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		//grid = new int[width][height];
		grid = new GridNode[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				//grid[x][y]=Utils.parseInt(tokens[(x + y * width) + 4]);
				grid[x][y]= new GridNode(Utils.parseInt(tokens[(x + y * width) + 4]),x,y);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setGridNodeEntranceLv(int x,int y,int Lv) {
		grid[x][y].setEntranceLv(Lv);
	}
	public GridNode[][] getGrid(){
		return grid;
	}
}








