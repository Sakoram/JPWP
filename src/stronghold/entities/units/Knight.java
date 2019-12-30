package stronghold.entities.units;

import java.awt.Color;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Knight extends Unit {

	public Knight(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		if(this.isSelected) drawSelection(g);
		g.drawImage(Assets.sword, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

}
