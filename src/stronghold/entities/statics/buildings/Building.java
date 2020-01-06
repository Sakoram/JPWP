package stronghold.entities.statics.buildings;

import java.awt.Graphics;
import java.awt.Rectangle;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.tiles.Tile;
import stronghold.ui.BuildingMenuUI;
import stronghold.ui.UIManager;

public abstract class Building extends Entity implements Cloneable {

	public Building(Handler handler, float x, float y, int width, int height, int health) {
		super(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, width, height,health);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean select(Rectangle selection) {
		if((selection.getHeight()<3) && (selection.getWidth()<3) && 
				(this.getX() < selection.x && this.getX()+this.width > selection.x && 
						this.getY() < selection.y  && this.getY() +this.height > selection.y )) {
			this.isSelected = true;
			((BuildingMenuUI) handler.getGame().buildingMenuUI).setBuilding(this);
			UIManager.setUIManager(handler.getGame().buildingMenuUI);
			return true;
		}
			
		else {
			this.isSelected = false;
			return false;
		}
			
		
	}
	@Override
	public void die() {
		setLvUnder(0);
		super.die();
	}
	public abstract void render(Graphics g, float x, float y);
	 public Object clone() throws CloneNotSupportedException 
	{ 
		return super.clone(); 
	} 
	 public void setLvUnder(int lv) {
			for(int i=0;i<width/Tile.TILEWIDTH;i++)
				for(int j=0;j<height/Tile.TILEHEIGHT;j++)
					handler.getWorld().setGridNodeEntranceLv(((int)(x/Tile.TILEWIDTH))+i,( (int)(y/Tile.TILEHEIGHT))+j, lv);

					
	 }
	 public abstract void setLvUnder();
	 public void flip() {}

}
