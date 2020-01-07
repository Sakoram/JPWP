package stronghold.states;

import java.awt.Graphics;

import stronghold.Handler;
import stronghold.worlds.World;
/**
 * Stan gry podczas rozgrywki, po stworzeniu jego obiektu tworzony jest świat.
 * @author a
 *
 */
public class GameState extends State {

	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		// uiManager = new StandardGameUI(handler);
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
