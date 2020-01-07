package stronghold.gfx;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.tiles.Tile;
/**
 * Klasa kamery. 
 * Przechowyje ofset kamery i ma metody pozwalające je ustawiać.
 * @author a
 *
 */
public class GameCamera {

	private Handler handler;
	private float xOffset;
	private float yOffset;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}

		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight() + 100) {
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight() + 100;
		}
	}

	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}

	public void adjustGameCamera() {
		int distance;
		distance = handler.getMouseManager().getMouseX() - 20;
		if (distance < 0)
			move((distance) / 2, 0);

		distance = handler.getMouseManager().getMouseX() - handler.getWidth() + 20;
		if (distance > 0)
			move(distance / 2, 0);

		distance = handler.getMouseManager().getMouseY() - 20;
		if (distance < 0)
			move(0, (distance) / 2);

		distance = handler.getMouseManager().getMouseY() - handler.getHeight() + 20;
		if (distance > 0)
			move(0, distance / 2);
	}

	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
