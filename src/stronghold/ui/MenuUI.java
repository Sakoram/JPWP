package stronghold.ui;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.states.GameState;
import stronghold.states.State;

public class MenuUI extends UIManager {

	public MenuUI(Handler handler) {
		super(handler);
		addObject(new UIImageButton(200, 200, 64, 64, Assets.water, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().gameState = new GameState(handler);
				State.setState(handler.getGame().gameState);
				handler.getWorld().setDifficultyLv(1);
			}
		}));
	}

}
