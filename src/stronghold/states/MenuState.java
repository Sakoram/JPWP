package stronghold.states;

import java.awt.Graphics;


import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.ui.ClickListener;
import stronghold.ui.UIImageButton;
import stronghold.ui.UIManager;

public class MenuState extends State {

	

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		
	}

	@Override
	public void tick() {
		UIManager.getUIManager().tick();
		
		// Temporarily just go directly to the GameState, skip the menu state!
		//handler.getMouseManager().setUIManager(null);
		//State.setState(handler.getGame().gameState);
	}

	@Override
	public void render(Graphics g) {
		UIManager.getUIManager().render(g);
	}

}
