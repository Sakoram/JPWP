package stronghold.ui;

import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import stronghold.Handler;
/**
 * Abstrakcyjna klasa po której dziedziczą klasy odpowiedzialne za obsługe obiektów interface'u.
 * @author a
 *
 */
public abstract class UIManager {
	private static UIManager currentUIManager = null;

	public static void setUIManager(UIManager uiManager) {
		currentUIManager = uiManager;

	}

	public static UIManager getUIManager() {
		return currentUIManager;
	}

	protected Handler handler;
	private ArrayList<UIObject> objects;

	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}

	public synchronized void tick() {
		for (int i = 0; i < objects.size(); i++) {
			UIObject o = objects.get(i);
			o.tick();
		}
	}

	public synchronized void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			UIObject o = objects.get(i);
			o.render(g);
		}

	}

	public synchronized void onMouseMove(MouseEvent e) {
		for (int i = 0; i < objects.size(); i++) {
			UIObject o = objects.get(i);
			o.onMouseMove(e);
		}
	}

	public synchronized void onMouseRelease(MouseEvent e) {
		for (int i = 0; i < objects.size(); i++) {
			UIObject o = objects.get(i);
			o.onMouseRelease(e);
		}
	}

	public synchronized void addObject(UIObject o) {
		objects.add(o);
	}

	public synchronized void removeObject(UIObject o) {
		objects.remove(o);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}

}
