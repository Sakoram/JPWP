package stronghold.entities.units;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

import javax.vecmath.Vector2d;
import javax.vecmath.Tuple2d;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.tiles.Tile;

public abstract class Unit extends Entity {
	public static final int TICKS_TO_ATTACK = handler.getGame().getFPS();
	protected int ticksToAttack;
	protected boolean isPlayers;
	protected List<Point> path;
	protected Iterator<Point> pathIterator;
	protected Point destination;
	protected int speed = 5;
	protected Point a;
	protected int range;
	public Unit(Handler handler, float x, float y, int width, int height, int health, boolean isPlayers, int range){
		super(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, width, height, health);
		this.isPlayers = isPlayers;
		this.range = range;
	}



	@Override
	public void select(Rectangle selection) {
		if(selection.intersects(new Rectangle((int)this.x+this.bounds.x,(int)this.y+this.bounds.y,this.bounds.width,this.bounds.height)))
			this.isSelected =true;
		else 
			this.isSelected = false;
		
	}
	public void setPath(List<Point> path) {
		if(path!=null) {
			this.path = path;
			this.pathIterator = this.path.iterator();
		}
	
	}
	private boolean moveToDest(Point dest) {
		Vector2d temp = new Vector2d(dest.getX(),dest.getY());
		temp.sub(new Vector2d(x,y));
		if(temp.length() <= speed) {
			this.x = dest.x;
			this.y = dest.y;
			return true;
		}
		temp.normalize();
		temp.scale(speed);
		this.x+=temp.x;
		this.y+=temp.y;
		return false;
	}
	@Override
	public void tick() {
		ticksToAttack++;
		if(ticksToAttack >TICKS_TO_ATTACK && active) {
			ticksToAttack=0;
			Unit enemy = null;
			if(!(this instanceof Worker)) {
				enemy = handler.getWorld().getEntityManager().enemyInRange(this);
				if(enemy != null) {
					atack(enemy);
				}
			}
		}
		if(pathIterator!=null && pathIterator.hasNext())
		{
			if( destination == null || moveToDest(destination) )  destination = pathIterator.next();
		} else {
			if( destination == null || moveToDest(destination) ) destination = null;
		}
			
			//this.x
	
	}
	public abstract void atack(Entity enemy);
	
	public int getRange() {
		return this.range*Tile.TILEHEIGHT;
	}
	public boolean getIsPlayers() {
		return isPlayers;
	}


}