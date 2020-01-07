package stronghold.entities.statics.buildings;

import java.awt.Graphics;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
/**
 * Klasa budynku schodów.
 * @author a
 *
 */
public class Stairs extends Building {
	public static final int DEFAULT_HEALTH = 200;
	private int rotation;

	public Stairs(Handler handler, int x, int y, int rotation) {
		super(handler, x, y, rotation >= 2 ? Tile.TILEWIDTH * 3 : Tile.TILEWIDTH,
				rotation < 2 ? Tile.TILEHEIGHT * 3 : Tile.TILEHEIGHT, DEFAULT_HEALTH);
		this.rotation = rotation;
		setLvUnder();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		render(g, x, y);
		if (this.isSelected)
			drawSelection(g);
	}

	@Override
	public int getMaxHealth() {
		return DEFAULT_HEALTH;
	}

	@Override
	public void render(Graphics g, float x, float y) {
		if (rotation == 0) {
			g.drawImage(Assets.bricks1, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()) + Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT,
					null);
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()) + Tile.TILEHEIGHT * 2, Tile.TILEWIDTH,
					Tile.TILEHEIGHT, null);
		} else if (rotation == 1) {
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()) + Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT,
					null);
			g.drawImage(Assets.bricks1, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()) + Tile.TILEHEIGHT * 2, Tile.TILEWIDTH,
					Tile.TILEHEIGHT, null);

		} else if (rotation == 2) {
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()) + Tile.TILEWIDTH,
					(int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
			g.drawImage(Assets.bricks1, (int) (x - handler.getGameCamera().getxOffset()) + Tile.TILEWIDTH * 2,
					(int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);

		} else if (rotation == 3) {
			g.drawImage(Assets.bricks1, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()) + Tile.TILEWIDTH,
					(int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()) + Tile.TILEWIDTH * 2,
					(int) (y - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
		}

	}

	@Override
	public void setLvUnder() {
		if (rotation == 0) {
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)), ((int) (y / Tile.TILEHEIGHT)) + 0,
					2);
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)), ((int) (y / Tile.TILEHEIGHT)) + 1,
					2);
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)), ((int) (y / Tile.TILEHEIGHT)) + 2,
					1);
		} else if (rotation == 1) {
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)), ((int) (y / Tile.TILEHEIGHT)) + 0,
					1);
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)), ((int) (y / Tile.TILEHEIGHT)) + 1,
					2);
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)), ((int) (y / Tile.TILEHEIGHT)) + 2,
					2);
		} else if (rotation == 2) {
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)) + 0, ((int) (y / Tile.TILEHEIGHT)),
					1);
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)) + 1, ((int) (y / Tile.TILEHEIGHT)),
					2);
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)) + 2, ((int) (y / Tile.TILEHEIGHT)),
					2);
		} else if (rotation == 3) {
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)) + 0, ((int) (y / Tile.TILEHEIGHT)),
					2);
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)) + 1, ((int) (y / Tile.TILEHEIGHT)),
					2);
			handler.getWorld().setGridNodeEntranceLv(((int) (x / Tile.TILEWIDTH)) + 2, ((int) (y / Tile.TILEHEIGHT)),
					1);

		}

	}

	@Override
	public void flip() {
		rotation = (rotation + 1) % 4;
		width = (rotation >= 2 ? Tile.TILEWIDTH * 3 : Tile.TILEWIDTH);
		height = (rotation < 2 ? Tile.TILEHEIGHT * 3 : Tile.TILEHEIGHT);

	}

}
