package stronghold.states;

import java.awt.Graphics;

import stronghold.Handler;
import stronghold.ui.StandardGameUI;
import stronghold.ui.UIManager;
import stronghold.worlds.World;

public class GameState extends State {
	
	private World world;
	
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		//uiManager = new StandardGameUI(handler);
		world.init();
		
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		
	}

}
