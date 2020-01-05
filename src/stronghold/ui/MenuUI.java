package stronghold.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.gfx.Assets;
import stronghold.states.GameState;
import stronghold.states.State;

public class MenuUI extends UIManager {

	public MenuUI(Handler handler) {
		super(handler);
		addObject(new UIImageButton(512, 384, 128, 64, Assets.btn_hard, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().gameState = new GameState(handler);
				State.setState(handler.getGame().gameState);
				UIManager.setUIManager(handler.getGame().standardGameUI);
				handler.getWorld().setDifficultyLv(3);
			}
		}));
		addObject(new UIImageButton(512, 512, 128, 64, Assets.btn_medium, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().gameState = new GameState(handler);
				State.setState(handler.getGame().gameState);
				UIManager.setUIManager(handler.getGame().standardGameUI);
				handler.getWorld().setDifficultyLv(2);
			}
		}));
		addObject(new UIImageButton(512, 640, 128, 64, Assets.btn_easy, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().gameState = new GameState(handler);
				State.setState(handler.getGame().gameState);
				UIManager.setUIManager(handler.getGame().standardGameUI);
				handler.getWorld().setDifficultyLv(1);
			}
		}));
	}

	

}
