package stronghold.entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
/**
 * Klasa drzewa.
 * @author a
 *
 */
public class Tree extends Entity {
	public static final int DEFAULT_HEALTH = 50;

	public Tree(Handler handler, int x, int y) {
		super(handler, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT, DEFAULT_HEALTH);

		bounds.x = Tile.TILEWIDTH / 4;
		bounds.y = Tile.TILEHEIGHT / 4;
		bounds.width = width - Tile.TILEWIDTH / 2;
		bounds.height = height - Tile.TILEHEIGHT / 2;
		handler.getWorld().setGridNodeEntranceLv(x, y, 6);
	}

	@Override
	public void tick() {

	}

	@Override
	public void die() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public boolean select(Rectangle selection) {
		return false;

	}

	@Override
	public int getMaxHealth() {
		return DEFAULT_HEALTH;
	}

}
