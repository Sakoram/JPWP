package stronghold.entities.units;

import java.awt.Graphics;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.entities.objects.Arrow;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
/**
 * Klasa jednostki łucznika.
 * @author a
 *
 */
public class Archer extends Unit {
	private static final int TICKS_TO_ATTACK = (int) (handler.getGame().getFPS() * 1.5);
	public static final int RANGE = 8;
	public static final int DEFAULT_HEALTH = 300;

	public Archer(Handler handler, float x, float y, boolean isPlayers) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, DEFAULT_HEALTH, isPlayers, RANGE);
		this.speed = Tile.TILEHEIGHT / 32 * 3;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		super.render(g, Assets.bow, isPlayers);

	}

	@Override
	public void atack(Entity enemy) {
		handler.getWorld().getEntityManager()
				.addEntity(new Arrow(handler, x, y, enemy.getX(), enemy.getY(), isPlayers));
	}

	@Override
	public void tick() {
		super.tick(TICKS_TO_ATTACK);
	}

	@Override
	public int getMaxHealth() {
		return DEFAULT_HEALTH;
	}

}
