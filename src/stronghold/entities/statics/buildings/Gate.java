package stronghold.entities.statics.buildings;

import java.awt.Graphics;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Gate extends Building {
	public static final int DEFAULT_HEALTH = 900;
	public Gate(Handler handler, int x, int y, boolean isVertical) {
		super(handler, x, y, Tile.TILEWIDTH*3, Tile.TILEHEIGHT*3,DEFAULT_HEALTH);
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				handler.getWorld().setGridNodeEntranceLv(x+i, y+j, 3);
		if(isVertical) {
			handler.getWorld().setGridNodeEntranceLv(x+1, y, 1);
			handler.getWorld().setGridNodeEntranceLv(x+1, y+1, 2);
			handler.getWorld().setGridNodeEntranceLv(x+1, y+2, 1);
		} else {
			handler.getWorld().setGridNodeEntranceLv(x, y+1, 1);
			handler.getWorld().setGridNodeEntranceLv(x+1, y+1, 2);
			handler.getWorld().setGridNodeEntranceLv(x+2, y+1, 1);
		}
	
		
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()+i*Tile.TILEWIDTH), 
					(int) (y - handler.getGameCamera().getyOffset()+j*Tile.TILEHEIGHT), Tile.TILEHEIGHT, Tile.TILEWIDTH, null);
		if(this.isSelected) drawSelection(g);
			
		
	}

	@Override
	public void die() {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH))+i,( (int)(y/Tile.TILEHEIGHT))+j, 0);

	}

}
