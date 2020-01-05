package stronghold.entities.statics.buildings;

import java.awt.Graphics;
import java.awt.Rectangle;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
import stronghold.ui.BuildingMenuUI;
import stronghold.ui.UIManager;

public class Barracks extends Building {
	public static final int DEFAULT_HEALTH = 500;
	public Barracks(Handler handler, int x, int y) {
		super(handler, x, y, Tile.TILEWIDTH*3, Tile.TILEHEIGHT*3,DEFAULT_HEALTH);
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				handler.getWorld().setGridNodeEntranceLv(x+i, y+j, 6);

	
		
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			g.drawImage(Assets.planks1, (int) (x - handler.getGameCamera().getxOffset()+i*Tile.TILEWIDTH), 
					(int) (y - handler.getGameCamera().getyOffset()+j*Tile.TILEHEIGHT), Tile.TILEHEIGHT, Tile.TILEWIDTH, null);
		if(this.isSelected) drawSelection(g);
			
		
	}

	@Override
	public void die() {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH))+i,( (int)(y/Tile.TILEHEIGHT))+j, 0);
	}
	@Override
	public boolean select(Rectangle selection) {
	if(super.select(selection)) {
		UIManager.setUIManager(handler.getGame().barracksMenuUI);
		return true;
	}
	return false;
	}
	

}