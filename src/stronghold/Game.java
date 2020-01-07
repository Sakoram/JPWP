package stronghold;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import stronghold.display.Display;
import stronghold.gfx.Assets;
import stronghold.gfx.GameCamera;
//import stronghold.input.KeyManager;
import stronghold.input.MouseManager;
import stronghold.states.MenuState;
import stronghold.states.State;
import stronghold.ui.BarracksMenuUI;
import stronghold.ui.BuildingMenuUI;
import stronghold.ui.GameOverUI;
import stronghold.ui.GateMenuUI;
import stronghold.ui.MenuUI;
import stronghold.ui.StandardGameUI;
import stronghold.ui.UIManager;
/**
 * Główna klasa programu. Tworzy okno gry.
 * @author a
 *
 */
public class Game implements Runnable {
	private int FPS = 60;

	private Display display;
	private int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	// States
	public State gameState;
	public State menuState;

	// Input
	//private KeyManager keyManager;
	private MouseManager mouseManager;

	public UIManager menuUI;
	public UIManager gameOverUI;
	public UIManager standardGameUI;
	public UIManager buildingMenuUI;
	public UIManager barracksMenuUI;
	public UIManager gateMenuUI;

	// Camera
	private GameCamera gameCamera;

	// Handler
	private Handler handler;
/**
 * Konstruktor klasy Game.
 * @param title Tytuł wyświetlany na oknie gry.
 * @param width Szerokość okna.
 * @param height Wysokość okna.
 */
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		//keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
/**
 * Metoda inicjalizująca.
 */
	private void init() {
		display = new Display(title, width, height);
		//display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();

		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);

		menuState = new MenuState(handler);
		// gameState = new GameState(handler);

		menuUI = new MenuUI(handler);
		gameOverUI = new GameOverUI(handler);
		standardGameUI = new StandardGameUI(handler);
		buildingMenuUI = new BuildingMenuUI(handler);
		barracksMenuUI = new BarracksMenuUI(handler);
		gateMenuUI = new GateMenuUI(handler);

		State.setState(menuState);
		UIManager.setUIManager(menuUI);
	}
/**
 * Metoda odpalana regularnie z zadaną częstotliwością. Wykonuje ona całą logike.
 */
	private void tick() {
		//keyManager.tick();

		if (State.getState() != null)
			State.getState().tick();
	}
/**
 * Metoda renderująca obraz.
 */
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!

		if (State.getState() != null)
			State.getState().render(g);
		if (UIManager.getUIManager() != null)
			UIManager.getUIManager().render(g);

		// End Drawing!
		bs.show();
		g.dispose();
	}
/**
 * Metoda zawierająca główną pętle gry.
 */
	public void run() {

		init();

		double timePerTick = 1000000000 / FPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}

//	public KeyManager getKeyManager() {
//		return keyManager;
//	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
/**
 * Metoda wykonynawa po zakończeniu rozgrywki.
 * Jest ona wywoływana po śmierci króla, lub po przeżyciu wszystkich fal przeciwników.
 * @param isGameWon true == gra wygrana, false == gra przegrana
 */
	public void gameOver(boolean isGameWon) {
		State.setState(menuState);
		UIManager.setUIManager(gameOverUI);
		mouseManager.setEntityManager(null);
		// stop();
	}
/**
 * Metoda startująca wątek.
 */
	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
/**
 * Metoda zatrzymująca wątek.
 */
	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getFPS() {
		return FPS;
	}

}
