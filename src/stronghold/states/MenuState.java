package stronghold.states;

import java.awt.Graphics;


import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;
import stronghold.ui.ClickListener;
import stronghold.ui.MenuUI;
import stronghold.ui.UIImageButton;
import stronghold.ui.UIManager;

public class MenuState extends State {

	
	public MenuState(Handler handler) {
		super(handler);
		//uiManager = new MenuUI(handler);
		
	}

	@Override
	public void tick() {
		//uiManager.tick();
		
		// Temporarily just go directly to the GameState, skip the menu state!
		//handler.getMouseManager().setUIManager(null);
		//State.setState(handler.getGame().gameState);
	}

	@Override
	public void render(Graphics g) {
		for(int x = 0;x<handler.getWidth();x=x+Tile.TILEWIDTH*2 )
			for(int y =0; y < handler.getHeight(); y=y+Tile.TILEHEIGHT*2)
				g.drawImage(Assets.bricks3, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2, null);
	}

}
