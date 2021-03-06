package stronghold.entities.units;

import java.awt.Graphics;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
/**
 * Klasa jednostki pracownika
 * @author a
 *
 */
public class Worker extends Unit {

	public static final int DEFAULT_HEALTH = 100;
	public static final int RANGE = 0;

	public Worker(Handler handler, float x, float y, boolean isPlayers) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, DEFAULT_HEALTH, isPlayers, RANGE);
		this.speed = Tile.TILEHEIGHT / 32 * 3;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		if (this.isSelected)
			drawHP(g, isPlayers);
		g.drawImage(Assets.worker, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

	}

	@Override
	public void atack(Entity enemy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick() {
		super.tick(0);

	}

	@Override
	public int getMaxHealth() {
		return DEFAULT_HEALTH;
	}

}
