package stronghold.entities.units;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;


import stronghold.Handler;
import stronghold.entities.Entity;

public abstract class Unit extends Entity {
	protected List<Point> path;
	protected Iterator<Point> pathIterator;
	protected Point destination;
	protected int speed;
	protected Point a;
	public Unit(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}



@Override
public void select(Rectangle selection) {
	if(selection.intersects(new Rectangle((int)this.x+this.bounds.x,(int)this.y+this.bounds.y,this.bounds.width,this.bounds.height)))
		this.isSelected =true;
	else 
		this.isSelected = false;
	
}
public void setPath(List<Point> path) {
	this.path = path;
	this.pathIterator = this.path.iterator();
}
private void move(Point dest) {
	//dest.sub(new Point(x,y));
	//dest.normalize();
	//dest.scale(speed);
}
@Override
public void tick() {
	move(new Point(30,30));
	//if(pathIterator.hasNext())
		//pathIterator.next()
		//this.x

}
}