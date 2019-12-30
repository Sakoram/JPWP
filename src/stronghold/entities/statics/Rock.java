package stronghold.entities.statics;


import java.awt.Graphics;
import java.awt.Rectangle;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Rock extends StaticEntity {

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		//bounds.x = 3;
		//bounds.y = (int) (height / 2f);
		//bounds.width = width - 6;
		//bounds.height = (int) (height - height / 2f);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.water, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//g.setColor(new Color(250,0,0,120));
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				//(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				//bounds.width, bounds.height);
	}

	@Override
	public void select(Rectangle selection) {
		// TODO Auto-generated method stub
		
	}

}
