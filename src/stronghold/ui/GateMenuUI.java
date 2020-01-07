package stronghold.ui;

import stronghold.Handler;
import stronghold.entities.statics.buildings.Gate;
import stronghold.gfx.Assets;
/**
 * Menu wyświetlane podczas gry po bramy.
 * @author a
 *
 */
public class GateMenuUI extends BuildingMenuUI {

	public GateMenuUI(Handler handler) {
		super(handler);
		addObject(new UIImageButton(handler.getWidth() - 258, handler.getHeight() - 80, 64, 64, Assets.bars,
				new ClickListener() {
					@Override
					public void onClick() {
						if (BuildingMenuUI.building != null)
							((Gate) BuildingMenuUI.building).changeState();

					}
				}));

	}

}
