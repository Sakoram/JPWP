package stronghold.entities.statics.buildings;

import java.awt.Rectangle;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.tiles.Tile;

public abstract class Building extends Entity {

	public Building(Handler handler, float x, float y, int width, int height, int health) {
		super(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, width, height,health);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean select(Rectangle selection) {
		if((selection.getHeight()<3) && (selection.getWidth()<3) && 
				(this.getX() < selection.x && this.getX()+this.width > selection.x && 
						this.getY() < selection.y  && this.getY() +this.height > selection.y )) {
			this.isSelected =true;
			
			return true;
		}
			
		else {
			this.isSelected = false;
			return false;
		}
			
		
	}
	

}
