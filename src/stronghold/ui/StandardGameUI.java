package stronghold.ui;


import stronghold.Handler;
import stronghold.entities.statics.buildings.*;

import stronghold.gfx.Assets;
/**
 * Menu podczas gry. Zawiera budynki możliwe do budowy.
 * @author a
 *
 */
public class StandardGameUI extends UIManager {
	private Building selectedBuilding = null;
	private UIObject activeObject = null;

	public StandardGameUI(Handler handler) {
		super(handler);
		addObject(new UIImageButton(128 * 2, handler.getHeight() - 80, 64, 64, Assets.bricks1, new ClickListener() {
			@Override
			public void onClick() {
				if (activeObject != null)
					removeObject(activeObject);
				selectedBuilding = new Wall(handler, 0, 0);
				activeObject = new UIObjectSelectedBuilding(handler, selectedBuilding);
				addObject(activeObject);

			}
		}));
		addObject(new UIImageButton(128 * 3, handler.getHeight() - 80, 64, 64, Assets.bricks2, new ClickListener() {
			@Override
			public void onClick() {

				if (activeObject != null)
					removeObject(activeObject);
				selectedBuilding = new Tower(handler, 0, 0);
				activeObject = new UIObjectSelectedBuilding(handler, selectedBuilding);
				addObject(activeObject);
			}
		}));
		addObject(new UIImageButton(128 * 4, handler.getHeight() - 80, 64, 64, Assets.planks1, new ClickListener() {
			@Override
			public void onClick() {

				if (activeObject != null)
					removeObject(activeObject);
				selectedBuilding = new Barracks(handler, 0, 0);
				activeObject = new UIObjectSelectedBuilding(handler, selectedBuilding);
				addObject(activeObject);
			}
		}));
		addObject(new UIImageButton(128 * 5, handler.getHeight() - 80, 64, 64, Assets.bars, new ClickListener() {
			@Override
			public void onClick() {

				if (activeObject != null)
					removeObject(activeObject);
				selectedBuilding = new Gate(handler, 0, 0, true);
				activeObject = new UIObjectSelectedBuilding(handler, selectedBuilding);
				addObject(activeObject);
			}
		}));
		addObject(new UIImageButton(128 * 6, handler.getHeight() - 80, 32, 64, Assets.bricks2, new ClickListener() {
			@Override
			public void onClick() {

				if (activeObject != null)
					removeObject(activeObject);
				selectedBuilding = new Stairs(handler, 0, 0, 0);
				activeObject = new UIObjectSelectedBuilding(handler, selectedBuilding);
				addObject(activeObject);
				;
			}
		}));

	}

}
