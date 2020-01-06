package stronghold.worlds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import stronghold.Handler;
import stronghold.entities.EntityManager;
import stronghold.entities.GridNode;

import stronghold.entities.statics.Tree;
import stronghold.entities.statics.buildings.*;
import stronghold.entities.units.*;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
import stronghold.utils.Utils;


public class World {
	
	private Handler handler;
	private int width, height;
	private GridNode[][] grid;
	//private int[][] grid;
	private EntityManager entityManager;
	private int spawnX, spawnY;
	private Point[] spawnPoints;
	private int ticksToSpawnEnemies = 0, TICKS_TO_SPAWN_ENEMIES;
	private int difficultyLv=1;
	private int waveNum=0; //first wave without enemies
	
	public World(Handler handler, String path){
		this.handler = handler;
		loadWorld(path);
	}
	
	public void init() {
		
		entityManager = new EntityManager(handler,spawnX,spawnY);
		handler.getMouseManager().setEntityManager(entityManager);
		TICKS_TO_SPAWN_ENEMIES = handler.getGame().getFPS()*15;//60;
		//entityManager.init(new King(handler, spawnX, spawnY, true));
		//entityManager.addEntity(new King(handler, spawnX, spawnY, true));
		// Temporary entity code!
		entityManager.addEntity(new Tree(handler, 2, 2));
		//entityManager.addEntity(new Arrow(handler, 3, 2));
		//entityManager.addEntity(new Knight(handler, 150, 500));
		entityManager.addEntity(new Gimli(handler, 5, 2,true));
		entityManager.addEntity(new Archer(handler, 5, 3,true));
		entityManager.addEntity(new Worker(handler, 5, 4,true));
		entityManager.addEntity(new Spearman(handler, 6, 2,true));
		entityManager.addEntity(new Knight(handler, 9, 9,true));
		for(int i=3;i<9;i++) {
			entityManager.addEntity(new Wall(handler, i, 3));
			entityManager.addEntity(new Wall(handler, i, 8));
			//entityManager.addEntity(new Archer(handler, i, 8,true));
			//entityManager.addEntity(new Archer(handler, 8, i,true));
			entityManager.addEntity(new Wall(handler, 9, i));
			entityManager.addEntity(new Wall(handler, 2, i));
		}
		entityManager.addEntity(new Stairs(handler, 6, 4,3));
		entityManager.addEntity(new Gate(handler, 7, 10,true));
		entityManager.addEntity(new Tower(handler, 10, 10));
		entityManager.addEntity(new Barracks(handler, 4, 10));
		
		//System.out.print(new Wall(handler, 20, 20).getHealth());
		//entityManager.spawnEnemies(2,2,2);
		
	}
	public void tick(){
		handler.getGameCamera().adjustGameCamera();
		entityManager.tick();
		ticksToSpawnEnemies++;
		if(ticksToSpawnEnemies > TICKS_TO_SPAWN_ENEMIES) {
			ticksToSpawnEnemies=0;
			spawnWave();
		}
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()-100) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Entities
		entityManager.render(g);
		for(int x = 0;x<handler.getWidth();x=x+Tile.TILEWIDTH*2 )
			for(int y = handler.getHeight()-100; y < handler.getHeight(); y=y+Tile.TILEHEIGHT*2)
				g.drawImage(Assets.bricks3,  x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2, null);

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
		spawnPoints = new Point[3];
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		spawnPoints[0] = new Point(width-2, height-2);
		spawnPoints[1] = new Point(2, height-2);
		spawnPoints[2] = new Point(width-2, 2);
		//grid = new int[width][height];
		grid = new GridNode[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				//grid[x][y]=Utils.parseInt(tokens[(x + y * width) + 4]);
				grid[x][y]= new GridNode(Utils.parseInt(tokens[(x + y * width) + 4]),x,y);
			}
		}
	}
	public void spawnWave() {
		System.out.print("spawn");
		if(waveNum > 5) handler.getGame().gameOver(true);
		for(int i = 0; i < difficultyLv; i++) {
			entityManager.spawnEnemies(spawnPoints[i].x, spawnPoints[i].y, waveNum);
		}
		waveNum++;
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
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void setGridNodeEntranceLv(int x,int y,int Lv) {
		grid[x][y].setEntranceLv(Lv);
	}
	public GridNode[][] getGrid(){
		return grid;
	}

	public void setDifficultyLv(int Lv) {
		difficultyLv = Lv;
		
	}
}








