package stronghold.ui;

import java.awt.Graphics;

import stronghold.Handler;
import stronghold.entities.units.Archer;
import stronghold.entities.units.Gimli;
import stronghold.entities.units.Knight;
import stronghold.entities.units.Spearman;
import stronghold.gfx.Assets;
import stronghold.states.GameState;
import stronghold.states.State;
import stronghold.tiles.Tile;

public class StandardGameUI extends UIManager {

	public StandardGameUI(Handler handler) {
		super(handler);
		addObject(new UIImageButton(128*3, handler.getHeight()-80, 64, 64, Assets.spear, new ClickListener() {
			@Override
			public void onClick() {
//				if(barracks!=null)
//					handler.getWorld().getEntityManager().addEntity(new Spearman(handler,barracks.getX()/Tile.TILEWIDTH+1,
//							barracks.getY()/Tile.TILEHEIGHT+3,true));
			}
		}));
		addObject(new UIImageButton(128*4, handler.getHeight()-80, 64, 64, Assets.bow, new ClickListener() {
			@Override
			public void onClick() {
//				if(barracks!=null)
//					handler.getWorld().getEntityManager().addEntity(new Archer(handler,barracks.getX()/Tile.TILEWIDTH+1,
//							barracks.getY()/Tile.TILEHEIGHT+3,true));
			}
		}));
		addObject(new UIImageButton(128*5, handler.getHeight()-80, 64, 64, Assets.sword, new ClickListener() {
			@Override
			public void onClick() {
//				if(barracks!=null)
//					handler.getWorld().getEntityManager().addEntity(new Knight(handler,barracks.getX()/Tile.TILEWIDTH+1,
//							barracks.getY()/Tile.TILEHEIGHT+3,true));
			}
		}));
		addObject(new UIImageButton(128*6, handler.getHeight()-80, 64, 64, Assets.axe, new ClickListener() {
			@Override
			public void onClick() {
//				if(barracks!=null)
//					handler.getWorld().getEntityManager().addEntity(new Gimli(handler,barracks.getX()/Tile.TILEWIDTH+1,
//							barracks.getY()/Tile.TILEHEIGHT+3,true));
			}
		}));
	}


}
