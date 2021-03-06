package stronghold.ui;

import stronghold.Handler;
import stronghold.gfx.Assets;
/**
 * Menu wyświetlane po zakończeniu gry.
 * @author a
 *
 */
public class GameOverUI extends UIManager {

	public GameOverUI(Handler handler) {
		super(handler);
		addObject(new UIImageButton(512, 384, 128, 64, Assets.btn_again, new ClickListener() {
			@Override
			public void onClick() {
				UIManager.setUIManager(handler.getGame().menuUI);
			}
		}));
	}

}
