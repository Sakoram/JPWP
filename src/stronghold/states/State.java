package stronghold.states;

import java.awt.Graphics;


import stronghold.Handler;
import stronghold.ui.MenuUI;
import stronghold.ui.UIManager;


public abstract class State {
	protected static Handler handler;
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
		handler.getMouseManager().setUIManager(UIManager.getUIManager());
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	
	protected MenuUI uiManager;
	
	
	public State(Handler handler){
		State.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
