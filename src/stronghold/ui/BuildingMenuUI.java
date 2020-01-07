package stronghold.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.entities.statics.buildings.Building;
import stronghold.gfx.Assets;
/**
 * Menu wyświetlane po wybraniu budynku.
 * @author a
 *
 */
public class BuildingMenuUI extends UIManager {
	protected static Building building = null;

	public BuildingMenuUI(Handler handler) {
		super(handler);
		addObject(new UIImageButton(handler.getWidth() - 96, handler.getHeight() - 96, 32, 32, Assets.worker,
				new ClickListener() {
					@Override
					public void onClick() {
						if (BuildingMenuUI.building != null)
							building.setHealth(building.getMaxHealth());
					}
				}));
		addObject(new UIImageButton(handler.getWidth() - 96, handler.getHeight() - 48, 32, 32, Assets.axe,
				new ClickListener() {
					@Override
					public void onClick() {
						if (BuildingMenuUI.building != null)
							building.hurt(100);

					}
				}));

	}

	public void setBuilding(Building building) {

		BuildingMenuUI.building = building;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SERIF, Font.BOLD, 48));
		g.drawString(building.getClass().getSimpleName(), 32, handler.getHeight() - 16);

		g.fillRect((31), handler.getHeight() - 97, 260, 18);
		g.setColor(Color.red);
		g.fillRect((32), handler.getHeight() - 96, 258, 16);
		g.setColor(Color.green);
		g.fillRect((32), handler.getHeight() - 96, 258 * building.getHealth() / building.getMaxHealth(), 16);

	}

}
