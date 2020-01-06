package stronghold.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	// STATIC STUFF HERE

	public static Tile[] tiles = new Tile[10];
	public static Tile grass1Tile = new Grass1Tile(0);
	public static Tile grass2Tile = new Grass2Tile(1);
	public static Tile BedrockTile = new BedrockTile(2);
	public static Tile DirtTile = new DirtTile(3);
	public static Tile ironTile = new IronTile(4);
	public static Tile SandTile = new SandTile(5);
	public static Tile StoneTile = new StoneTile(6);
	public static Tile WaterTile = new WaterTile(7);

	// CLASS

	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;

	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;

		tiles[id] = this;
	}

	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public boolean isSolid() {
		return false;
	}

	public int getId() {
		return id;
	}

}
