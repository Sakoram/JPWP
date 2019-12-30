package stronghold.entities.statics.buildings;

import java.awt.Color;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Stairs extends Building {
	public Stairs(Handler handler, int x, int y) {
		super(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT*3);
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
		g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		if(this.isSelected) drawSelection(g);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

}
