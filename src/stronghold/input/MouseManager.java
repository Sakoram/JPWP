package stronghold.input;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import stronghold.entities.EntityManager;
import stronghold.ui.UIManager;
/**
 * Klasa której obiekt nasłuchuje ruchów myszki i naciśnięć jej przycisków.
 * Wysyła informacje o tym co się dzieje z myszką dalej.
 * @author a
 *
 */
public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY, pressedX, pressedY;
	private EntityManager entityManager;
	private Rectangle selection;

	public MouseManager() {
		;
	}



	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressedX = e.getX();
		pressedY = e.getY();
		if (e.getButton() == MouseEvent.BUTTON1) {
			selection = new Rectangle(pressedX, pressedY, 0, 0);
			leftPressed = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = true;
			if (entityManager != null) {
				entityManager.MoveSelectedUnits(new Point(pressedX, pressedY));
			}

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
			if (entityManager != null && selection.y < entityManager.getHandler().getHeight() - 100) {
				selection.height = selection.height == 0 ? 1 : selection.height;
				selection.width = selection.width == 0 ? 1 : selection.width;
				entityManager.selectEntities(selection);

			}
			selection = null;

		}

		else if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;

		if (UIManager.getUIManager() != null)
			UIManager.getUIManager().onMouseRelease(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if (UIManager.getUIManager() != null)
			UIManager.getUIManager().onMouseMove(e);
	}

	private void updateSelection() {
		selection.x = pressedX < mouseX ? pressedX : mouseX;
		selection.y = pressedY < mouseY ? pressedY : mouseY;
		selection.height = Math.abs(mouseY - pressedY);
		selection.width = Math.abs(mouseX - pressedX);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if (selection != null)
			updateSelection();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Rectangle getSelection() {
		return selection;
	}

}
