package stronghold.states;

import java.awt.Graphics;


import stronghold.Handler;
import stronghold.ui.UIManager;


public abstract class State {
	protected static Handler handler;
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
		State.handler.getMouseManager().setUIManager(state.getUiManager());
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	
	protected UIManager uiManager;
	
	
	public State(Handler handler){
		State.handler = handler;
	}
	public UIManager getUiManager() {
		return uiManager;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
