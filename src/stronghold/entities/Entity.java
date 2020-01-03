package stronghold.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.vecmath.Vector2d;

import stronghold.Handler;

public abstract class Entity {

	//public static final int DEFAULT_HEALTH = 10;
	protected static Handler handler;
	protected float x;
	protected float y;
	protected int width, height;
	protected int health;
	protected boolean active = true;
	protected Rectangle bounds;
	protected boolean isSelected = false;
	
	public Entity(Handler handler, float x, float y, int width, int height, int health){
		Entity.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public abstract void select(Rectangle selection);
	
	public void hurt(int amt){
		health -= amt;
		if(health <= 0){
			active = false;
			die();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	public void drawSelection(Graphics g) {
		g.setColor(new Color(0,250,0,100));
		
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			bounds.width, bounds.height);
	}
	public void drawHP(Graphics g, int currentHP, int maxHP, boolean isPlayer) {
		if(isPlayer) g.setColor(Color.green);
		else g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset())-bounds.height/16,
				bounds.width*currentHP/maxHP, bounds.height/16);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
