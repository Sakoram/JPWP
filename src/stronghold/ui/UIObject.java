package stronghold.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected Rectangle bounds;
	protected boolean hovering = false;

	public UIObject(float x, float y, int width, int height) {

		bounds = new Rectangle((int) x, (int) y, width, height);
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void onClick();

	public void onMouseMove(MouseEvent e) {
		if (bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
	}

	public void onMouseRelease(MouseEvent e) {
		if (hovering)
			onClick();
	}

	// Getters and setters

	public float getX() {
		return bounds.x;
	}

	public void setX(int x) {
		this.bounds.x = x;
	}

	public float getY() {
		return bounds.y;
	}

	public void setY(int y) {
		this.bounds.y = y;
	}

	public int getWidth() {
		return bounds.width;
	}

	public void setWidth(int width) {
		this.bounds.width = width;
	}

	public int getHeight() {
		return bounds.height;
	}

	public void setHeight(int height) {
		this.bounds.height = height;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}

}
