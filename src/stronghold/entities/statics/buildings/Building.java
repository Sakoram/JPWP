package stronghold.entities.statics.buildings;

import java.awt.Rectangle;

import stronghold.Handler;
import stronghold.entities.statics.StaticEntity;
import stronghold.tiles.Tile;

public abstract class Building extends StaticEntity {

	public Building(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void select(Rectangle selection) {
		if((selection.getHeight()<3) && (selection.getWidth()<3) && 
				(this.getX() < selection.x && this.getX()+this.width > selection.x && this.getY() < selection.y  && this.getY() +this.height > selection.y ))
			this.isSelected =true;
		else 
			this.isSelected = false;
		
	}

}
