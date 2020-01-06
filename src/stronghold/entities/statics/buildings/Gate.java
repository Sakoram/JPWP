package stronghold.entities.statics.buildings;

import java.awt.Graphics;
import java.awt.Rectangle;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
import stronghold.ui.UIManager;

public class Gate extends Building {
	public static final int DEFAULT_HEALTH = 900;
	private boolean isVertical;
	private boolean isLocked;
	public Gate(Handler handler, int x, int y, boolean isVertical) {
		super(handler, x, y, Tile.TILEWIDTH*3, Tile.TILEHEIGHT*3,DEFAULT_HEALTH);
	
		this.isVertical = isVertical;
		unLockGate();
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
		lockGate(0);
		super.die();

	}
	private void lockGate(int lv) {
		isLocked=true;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH))+i,( (int)(y/Tile.TILEHEIGHT))+j, lv);
	}
	private void unLockGate() {
		lockGate(3);
		isLocked=false;
		if(isVertical) {
			handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH))+1, ((int)(y/Tile.TILEHEIGHT)), 1);
			handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH))+1, ((int)(y/Tile.TILEHEIGHT))+1, 2);
			handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH))+1, ((int)(y/Tile.TILEHEIGHT))+2, 1);
		} else {
			handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH)), ((int)(y/Tile.TILEHEIGHT))+1, 1);
			handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH))+1, ((int)(y/Tile.TILEHEIGHT))+1, 2);
			handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH))+2, ((int)(y/Tile.TILEHEIGHT))+1, 1);
		}
		
	}
	public void changeState() {
		if(isLocked) unLockGate();
		else lockGate(3);
	}
	@Override
	public boolean select(Rectangle selection) {
	if(super.select(selection)) {
		UIManager.setUIManager(handler.getGame().gateMenuUI);
		return true;
	}
	return false;
	}
	@Override
	public int getMaxHealth() {
		return DEFAULT_HEALTH;
	}

	@Override
	public void render(Graphics g, float x, float y) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			g.drawImage(Assets.bricks2, (int) (x - handler.getGameCamera().getxOffset()+i*Tile.TILEWIDTH), 
					(int) (y - handler.getGameCamera().getyOffset()+j*Tile.TILEHEIGHT), Tile.TILEHEIGHT, Tile.TILEWIDTH, null);
		if(isVertical) {
			int max = 1;
			if(isLocked) max = 0;
			for(int i=max;i<2;i++)
				for(int j=1;j<5;j++) {
					g.drawImage(Assets.bars, (int) (x - handler.getGameCamera().getxOffset()+j*Tile.TILEWIDTH/2), 
							(int) (y - handler.getGameCamera().getyOffset()+i*Tile.TILEHEIGHT/2), Tile.TILEHEIGHT/2, Tile.TILEWIDTH/2, null);
					g.drawImage(Assets.bars, (int) (x - handler.getGameCamera().getxOffset()+j*Tile.TILEWIDTH/2), 
							(int) (y - handler.getGameCamera().getyOffset()-(i+1)*Tile.TILEHEIGHT/2+Tile.TILEHEIGHT*3), Tile.TILEHEIGHT/2, Tile.TILEWIDTH/2, null);
				}
			
		}
		else {
			int max = 1;
			if(isLocked) max = 0;
			for(int i=max;i<2;i++)
				for(int j=1;j<5;j++) {
					g.drawImage(Assets.bars, (int) (x - handler.getGameCamera().getxOffset()+i*Tile.TILEWIDTH/2), 
							(int) (y - handler.getGameCamera().getyOffset()+j*Tile.TILEHEIGHT/2), Tile.TILEHEIGHT/2, Tile.TILEWIDTH/2, null);
					g.drawImage(Assets.bars, (int) (x - handler.getGameCamera().getxOffset()-(i+1)*Tile.TILEWIDTH/2+Tile.TILEWIDTH*3), 
							(int) (y - handler.getGameCamera().getyOffset()+j*Tile.TILEHEIGHT/2), Tile.TILEHEIGHT/2, Tile.TILEWIDTH/2, null);
				}
			
		}
		
	}

}
