package stronghold.ui;

import stronghold.Handler;
import stronghold.entities.units.*;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
/**
 * Menu wyświetlane po wybraniu baraków.
 * @author a
 *
 */
public class BarracksMenuUI extends BuildingMenuUI {

	public BarracksMenuUI(Handler handler) {
		super(handler);
		addObject(new UIImageButton(128 * 3, handler.getHeight() - 80, 64, 64, Assets.spear, new ClickListener() {
			@Override
			public void onClick() {
				if (BuildingMenuUI.building != null)
					handler.getWorld().getEntityManager()
							.addEntity(new Spearman(handler, BuildingMenuUI.building.getX() / Tile.TILEWIDTH + 1,
									BuildingMenuUI.building.getY() / Tile.TILEHEIGHT + 3, true));
			}
		}));
		addObject(new UIImageButton(128 * 4, handler.getHeight() - 80, 64, 64, Assets.bow, new ClickListener() {
			@Override
			public void onClick() {
				if (BuildingMenuUI.building != null)
					handler.getWorld().getEntityManager()
							.addEntity(new Archer(handler, BuildingMenuUI.building.getX() / Tile.TILEWIDTH + 1,
									BuildingMenuUI.building.getY() / Tile.TILEHEIGHT + 3, true));
			}
		}));
		addObject(new UIImageButton(128 * 5, handler.getHeight() - 80, 64, 64, Assets.sword, new ClickListener() {
			@Override
			public void onClick() {
				if (BuildingMenuUI.building != null)
					handler.getWorld().getEntityManager()
							.addEntity(new Knight(handler, BuildingMenuUI.building.getX() / Tile.TILEWIDTH + 1,
									BuildingMenuUI.building.getY() / Tile.TILEHEIGHT + 3, true));
			}
		}));
		addObject(new UIImageButton(128 * 6, handler.getHeight() - 80, 64, 64, Assets.axe, new ClickListener() {
			@Override
			public void onClick() {
				if (BuildingMenuUI.building != null)
					handler.getWorld().getEntityManager()
							.addEntity(new Gimli(handler, BuildingMenuUI.building.getX() / Tile.TILEWIDTH + 1,
									BuildingMenuUI.building.getY() / Tile.TILEHEIGHT + 3, true));
			}
		}));
	}

}
