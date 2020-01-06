package stronghold.entities.statics.buildings;

import java.awt.Color;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Stairs extends Building {
	public static final int DEFAULT_HEALTH = 200;
	public Stairs(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*3,DEFAULT_HEALTH);
		handler.getWorld().setGridNodeEntranceLv(x, y, 2);
		handler.getWorld().setGridNodeEntranceLv(x, y+1, 2);
		handler.getWorld().setGridNodeEntranceLv(x, y+2, 1);
		
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

	@Override
	public void die() {
		for(int i=0;i<3;i++)
				handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH)),( (int)(y/Tile.TILEHEIGHT))+i, 0);
		super.die();

	}
	@Override
	public int getMaxHealth() {
		return DEFAULT_HEALTH;
	}

	@Override
	public void render(Graphics g, float x, float y) {
		g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}

}
