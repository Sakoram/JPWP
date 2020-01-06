package stronghold.entities.units;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import javax.vecmath.Vector2d;
import javax.vecmath.Tuple2d;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public abstract class Unit extends Entity {

	protected int ticksToAttack;
	protected boolean isPlayers;
	protected List<Point> path;
	protected Iterator<Point> pathIterator;
	protected Point destination;
	protected int speed = 5;
	protected Point a;
	protected int range;
	protected int ticksToCheckEnemy = 0;

	public Unit(Handler handler, float x, float y, int width, int height, int health, boolean isPlayers, int range) {
		super(handler, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT, width, height, health);
		this.isPlayers = isPlayers;
		this.range = range;
	}

	@Override
	public boolean select(Rectangle selection) {
		if (selection.intersects(new Rectangle((int) this.x + this.bounds.x, (int) this.y + this.bounds.y,
				this.bounds.width, this.bounds.height)))
			this.isSelected = true;
		else
			this.isSelected = false;
		return false;

	}

	public void setPath(List<Point> path) {
		if (path != null) {
			this.path = path;
			this.pathIterator = this.path.iterator();
			if (pathIterator.hasNext())
				pathIterator.next();
		}

	}

	// @Override
	public void tick(int TICKS_TO_ATTACK) {
		ticksToAttack++;
		Unit enemy = null;
		enemy = handler.getWorld().getEntityManager().enemyInRange(this);
		if (enemy != null) {
			if (ticksToAttack > TICKS_TO_ATTACK && active) {
				ticksToAttack = 0;

				if (!(this instanceof Worker)) {

					atack(enemy);
				}
			}

		} else {
			if (pathIterator != null && pathIterator.hasNext()) {
				if (destination == null || moveToDest(destination, speed))
					destination = pathIterator.next();
			} else {
				if (destination == null || moveToDest(destination, speed))
					destination = null;
			}

		}

	}

	public abstract void atack(Entity enemy);

	public int getRange() {
		return this.range * Tile.TILEHEIGHT;
	}

	public boolean getIsPlayers() {
		return isPlayers;
	}

}