package stronghold.worlds;

import java.awt.Graphics;
import java.awt.Point;

import stronghold.Handler;
import stronghold.entities.EntityManager;
import stronghold.entities.GridNode;

import stronghold.entities.statics.Tree;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
import stronghold.utils.Utils;
/**
 * Klasa Świata której obiekt jest tworzony po wybraniu poziomu trudności.
 * @author a
 *
 */
public class World {

	private Handler handler;
	private int width, height;
	private GridNode[][] grid;
	// private int[][] grid;
	private EntityManager entityManager;
	private int spawnX, spawnY;
	private Point[] spawnPoints;
	private int ticksToSpawnEnemies = 0, TICKS_TO_SPAWN_ENEMIES;
	private int difficultyLv = 1;
	private int waveNum = 0; // first wave without enemies
/**
 *  Konstruktor klasy World
 * @param handler "uchwyt"
 * @param path ścieżka do pliku z mapą
 */
	public World(Handler handler, String path) {
		this.handler = handler;
		loadWorld(path);
	}
/**
 * Metoda inicjalizująca rzeczy które nie mogły być inicjalizowane w konstruktorze.
 */
	public void init() {

		entityManager = new EntityManager(handler, spawnX, spawnY);
		handler.getMouseManager().setEntityManager(entityManager);
		TICKS_TO_SPAWN_ENEMIES = handler.getGame().getFPS() * 30;// 60;

		entityManager.addEntity(new Tree(handler, 9, 30));
		entityManager.addEntity(new Tree(handler, 6, 28));
		entityManager.addEntity(new Tree(handler, 8, 39));
		entityManager.addEntity(new Tree(handler, 3, 36));
		entityManager.addEntity(new Tree(handler, 13, 33));
		entityManager.addEntity(new Tree(handler, 14, 37));
		entityManager.addEntity(new Tree(handler, 5, 40));
		entityManager.addEntity(new Tree(handler, 9, 35));

		entityManager.addEntity(new Tree(handler, 2, 26));
		entityManager.addEntity(new Tree(handler, 8, 27));
		entityManager.addEntity(new Tree(handler, 5, 30));
		entityManager.addEntity(new Tree(handler, 3, 36));
		entityManager.addEntity(new Tree(handler, 2, 41));
		entityManager.addEntity(new Tree(handler, 4, 43));
		entityManager.addEntity(new Tree(handler, 9, 41));
		entityManager.addEntity(new Tree(handler, 6, 36));

	}
/**
 * Metoda sterująca całą logiką w świecie gry.
 */
	public void tick() {
		handler.getGameCamera().adjustGameCamera();
		entityManager.tick();
		ticksToSpawnEnemies++;
		if (ticksToSpawnEnemies > TICKS_TO_SPAWN_ENEMIES) {
			ticksToSpawnEnemies = 0;
			spawnWave();
		}
	}
/**
 * Metoda wyświetlająca świat.
 * @param g Obiekt klasy Graphics, który zawiera funcje render.
 */
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight() - 100) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		// Entities
		entityManager.render(g);
		for (int x = 0; x < handler.getWidth(); x = x + Tile.TILEWIDTH * 2)
			for (int y = handler.getHeight() - 100; y < handler.getHeight(); y = y + Tile.TILEHEIGHT * 2)
				g.drawImage(Assets.bricks3, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 2, null);

	}
/**
 * 
 * @param x koordynat x pola kafelów.
 * @param y koordynat y pola kafelów
 * @return Obiekt kafelka zawierający odpowiednią teksture.
 */
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.BedrockTile;

		// Tile t = Tile.tiles[grid[x][y]]; //.getTileId()
		Tile t = Tile.tiles[grid[x][y].getTileId()];
		if (t == null)
			return Tile.BedrockTile;
		return t;
	}
/**
 * Metoda wczytująca świat z pliku i zapisująca go w tablicy.
 * @param path ścieżka do pliku.
 */
	private void loadWorld(String path) {
		spawnPoints = new Point[3];
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		spawnPoints[0] = new Point(width - 2, height - 2);
		spawnPoints[1] = new Point(2, height - 2);
		spawnPoints[2] = new Point(width - 2, 2);
		// grid = new int[width][height];
		grid = new GridNode[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// grid[x][y]=Utils.parseInt(tokens[(x + y * width) + 4]);
				grid[x][y] = new GridNode(Utils.parseInt(tokens[(x + y * width) + 4]), x, y);
			}
		}
	}
/**
 * Metoda tworząca fale przeciwników.
 */
	public void spawnWave() {
		System.out.println("spawn");
		if (waveNum > 5)
			handler.getGame().gameOver(true);
		for (int i = 0; i < difficultyLv; i++) {
			entityManager.spawnEnemies(spawnPoints[i].x, spawnPoints[i].y, waveNum);
		}
		waveNum++;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setGridNodeEntranceLv(int x, int y, int Lv) {
		grid[x][y].setEntranceLv(Lv);
	}

	public GridNode[][] getGrid() {
		return grid;
	}

	public void setDifficultyLv(int Lv) {
		difficultyLv = Lv;

	}
}
