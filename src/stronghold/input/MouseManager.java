package stronghold.input;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import stronghold.entities.EntityManager;
import stronghold.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY, pressedX, pressedY;
	private UIManager uiManager;
	private EntityManager entityManager;
	private Rectangle selection;
	
	public MouseManager(){
;
	}
	
	public void setUIManager(UIManager uiManager){
		this.uiManager = uiManager;
	}
	
	// Getters
	
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	// Implemented methods
	
	@Override
	public void mousePressed(MouseEvent e) {
		pressedX = e.getX();
		pressedY = e.getY();
		if(e.getButton() == MouseEvent.BUTTON1) {
			selection = new Rectangle(pressedX,pressedY,0,0);
			leftPressed = true;
			//System.out.println("rect x, y: " + selection.x +", "+ selection.y +", hi " + selection.height + ", wi " + selection.width);
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			rightPressed = true;
			if(entityManager != null)
			{
				entityManager.MoveSelectedUnits(new Point(pressedX, pressedY));
				//entityManager.deselectEntities();
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			//System.out.println("released on x, y: " + x +", "+ y );
			leftPressed = false;
			if(uiManager != null)
				uiManager.onMouseRelease(e);
			if(entityManager != null)
			{
				selection.height = selection.height == 0 ? 1 : selection.height;
				selection.width = selection.width == 0 ? 1 : selection.width;
				entityManager.selectEntities(selection);
				selection = null;
			}
			
				
		}
			
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
		
		
	}
	

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		
		if(uiManager != null)
			uiManager.onMouseMove(e);
	}
	private void updateSelection() {
		selection.x = pressedX < mouseX ? pressedX : mouseX;
		selection.y = pressedY< mouseY ? pressedY : mouseY;
		selection.height = Math.abs(mouseY - pressedY);
		selection.width = Math.abs(mouseX - pressedX);
		//System.out.println("rect x, y: " + selection.x +", "+ selection.y +", hi " + selection.height + ", wi " + selection.width);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if(selection != null)
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
