package stronghold.entities.statics.buildings;

import java.awt.Color;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Wall extends Building {
	public static final int DEFAULT_HEALTH = 100;
	public Wall(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT,DEFAULT_HEALTH);
		handler.getWorld().setGridNodeEntranceLv(x, y, 3);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		render(g,x,y);
		if(this.isSelected) drawSelection(g);
	}
	public void render(Graphics g,float x, float y) {
		g.drawImage(Assets.bricks1, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		super.die();
		handler.getWorld().setGridNodeEntranceLv((int)(x/Tile.TILEWIDTH), (int)(y/Tile.TILEHEIGHT), 0);

	}

	@Override
	public int getMaxHealth() {
		return DEFAULT_HEALTH;
	}

}
