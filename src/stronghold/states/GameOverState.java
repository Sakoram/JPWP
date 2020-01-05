package stronghold.states;

import java.awt.Graphics;


import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.ui.ClickListener;
import stronghold.ui.UIImageButton;
import stronghold.ui.UIManager;

public class GameOverState extends State {

	

	public GameOverState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		

		uiManager.addObject(new UIImageButton(400, 400, 64, 64, Assets.iron, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().menuState);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
		
		// Temporarily just go directly to the GameState, skip the menu state!
		//handler.getMouseManager().setUIManager(null);
		//State.setState(handler.getGame().gameState);
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
