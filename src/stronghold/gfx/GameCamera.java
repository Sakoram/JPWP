package stronghold.gfx;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.tiles.Tile;

public class GameCamera {
	
	private Handler handler;
	private double xOffset;
	private double yOffset;
	
	public GameCamera(Handler handler, float xOffset, float yOffset){
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace(){
		if(xOffset < 0){
			xOffset = 0;
		}else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		
		if(yOffset < 0){
			yOffset = 0;
		}else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()+100){
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()+100;
		}
	}
	
	public void centerOnEntity(Entity e){
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	public void adjustGameCamera() {
		int distance;
		distance = handler.getMouseManager().getMouseX()-50;
		if(distance<0) move((distance)/5,0);
		
		distance = handler.getMouseManager().getMouseX()-handler.getWidth()+50;
		if(distance>0) move(distance/5,0);
		
		distance = handler.getMouseManager().getMouseY()-50;
		if(distance<0) move(0,(distance)/5);
		
		distance = handler.getMouseManager().getMouseY()-handler.getHeight()+50;
		if(distance>0) move(0,distance/5);
	}
	
	public void move(float xAmt, float yAmt){
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public double getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public double getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
