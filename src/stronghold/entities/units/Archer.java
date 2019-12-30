package stronghold.entities.units;

import java.awt.Color;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Archer extends Unit {

	public Archer(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		this.speed = 10;
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void render(Graphics g) {
		if(this.isSelected) {
			g.setColor(new Color(250,0,0,100));
		
			g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
		}
		g.drawImage(Assets.bow, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

}
