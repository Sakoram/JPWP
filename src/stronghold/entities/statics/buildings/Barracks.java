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
		setLvUnder();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		render( g, x,  y);
		if(this.isSelected) drawSelection(g);
			
		
	}
	@Override
	public void render(Graphics g, float x, float y) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			g.drawImage(Assets.planks1, (int) (x - handler.getGameCamera().getxOffset()+i*Tile.TILEWIDTH), 
					(int) (y - handler.getGameCamera().getyOffset()+j*Tile.TILEHEIGHT), Tile.TILEHEIGHT, Tile.TILEWIDTH, null);
		
	}

	@Override
	public boolean select(Rectangle selection) {
	if(super.select(selection)) {
		UIManager.setUIManager(handler.getGame().barracksMenuUI);
		return true;
	}
	return false;
	}
	@Override
	public int getMaxHealth() {
		return DEFAULT_HEALTH;
	}

	@Override
	public void setLvUnder() {
		setLvUnder(6);
	}

	

}
