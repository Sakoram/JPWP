package stronghold.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import stronghold.Handler;
import stronghold.entities.statics.buildings.Building;
import stronghold.entities.units.*;

import stronghold.tiles.Tile;
import stronghold.ui.UIManager;

public class EntityManager {
	
	private Handler handler;

	private ArrayList<Entity> entities;
	private PathFinding pathFinding;
	private int ticksToControlEnemies;
	private int TICKS_TO_CONTROL_ENEMIES;
	private King king;
	/*private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b) {
			if(a instanceof Unit)
				return 1;
			return -1;
		}
	};*/
	
	public EntityManager(Handler handler,int spawnX, int spawnY){
		this.handler = handler;
		entities = new ArrayList<Entity>();
		this.pathFinding = new PathFinding(handler);
		this.king = new King(handler, spawnX,spawnY,true);
		addEntity(king);
		TICKS_TO_CONTROL_ENEMIES =handler.getGame().getFPS()*2;
	}
	
	public synchronized void tick(){
		ticksToControlEnemies++;
		for(int i = 0;i<entities.size();i++){
			Entity e = entities.get(i);
			if(!e.isActive()) {
				entities.remove(e);
				continue;
			}
			if(ticksToControlEnemies > TICKS_TO_CONTROL_ENEMIES) {
				if((e instanceof Unit) && !((Unit)e).getIsPlayers()) {
					List<Point> path = pathFinding.findPath(new Point((int)e.getX(), (int)e.getY()), 
							new Point((int)king.getX(), (int)king.getY()));
					if(path!=null)
						((Unit)e).setPath(path);
					else {
						GridNode[][] grid = handler.getWorld().getGrid();
						if(isBuildingClose(grid, (int)(e.getX()/Tile.TILEWIDTH),(int)(e.getY()/Tile.TILEHEIGHT)) && damageWall(e));
						{
							
							GridNode nearestWall = grid[0][0];
							for (int x = 0; x < handler.getWorld().getWidth(); x++) {
					            for (int y = 0; y < handler.getWorld().getWidth(); y++) {
					            	if(grid[x][y].fCost < nearestWall.fCost && grid[x][y].hCost <= nearestWall.hCost)
					            		nearestWall= grid[x][y];
					            }
							}
							path = pathFinding.findPath(new Point((int)e.getX(), (int)e.getY()), 
									new Point(nearestWall.getx()*Tile.TILEWIDTH, nearestWall.gety()*Tile.TILEHEIGHT));
							((Unit)e).setPath(path);
						}
					}
				}
			}
			
			e.tick();
		}
		if(ticksToControlEnemies > TICKS_TO_CONTROL_ENEMIES) ticksToControlEnemies=0;
		//entities.sort(renderSorter);
	}

	private boolean isBuildingClose(GridNode[][] grid, int x, int y) {
		if(x-1 >= 0) {
			if( grid[x-1][y].getEntrenceLv()>0)  return true;
		}
		if(x+1 < handler.getWorld().getWidth() ) {
			if(grid[x+1][y].getEntrenceLv()>0) return true;
		}
		if(y+1 < handler.getWorld().getHeight() ) {
			if(grid[x][y+1].getEntrenceLv()>0)  return true;
		}
		if(y-1 >= 0) {
			if( grid[x][y-1].getEntrenceLv()>0) return true;
		}
		return false;
	}

	public synchronized void render(Graphics g){
		for(Entity e : entities){
			if(!(e instanceof Unit)) e.render(g);
		}
		for(Entity e : entities){
			if(e instanceof Unit) e.render(g);
		}
		Rectangle selection = handler.getMouseManager().getSelection();
		if(selection != null) {
			g.setColor(new Color(0,250,0,110));
			g.fillRect(selection.x ,selection.y,selection.width, selection.height);
		}
		
	}
	
	public synchronized void addEntity(Entity e){
		entities.add(e);
	}
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	public synchronized void selectEntities(Rectangle selection) {
		selection.x+=handler.getGameCamera().getxOffset();
		selection.y+=handler.getGameCamera().getyOffset();
		boolean isAnyBuildingSelected = false;
		for(Entity e : entities){
			isAnyBuildingSelected = e.select(selection) || isAnyBuildingSelected;
		}
		if(!isAnyBuildingSelected) UIManager.setUIManager(handler.getGame().standardGameUI);
	}
	public synchronized void deselectEntities() {
		for(Entity e : entities){
			e.isSelected=false;
		}
		
	}
	public synchronized void MoveSelectedUnits(Point dest) {
		dest.x+=handler.getGameCamera().getxOffset();
		dest.y+=handler.getGameCamera().getyOffset();
		List<Entity> unitsToMove = new ArrayList<Entity>();
		for(Entity e : entities){
			if(e instanceof Unit && e.isSelected && ((Unit) e).getIsPlayers())
				unitsToMove.add(e);
		MoveUnits(dest,unitsToMove);
		}
		
		
		
	}
	private void MoveUnits(Point dest, List<Entity> unitsToMove) {
		List<Point> locations = pathFinding.findLocations(dest,unitsToMove.size());
		if(locations==null) return;
		int i=0;
		for(Entity unit : unitsToMove)
		{
			((Unit)unit).setPath(pathFinding.findPath(new Point( (int)unit.getX(), (int)unit.getY() ), locations.get(i)));
			if(i+1<locations.size()) i++;
		}
	}
	public void damageUnits(int damage, Rectangle area, boolean isDmgFromPlayersUnit){
		for(Entity e : entities){
			if(!(e instanceof Unit)) continue;
			if(((Unit)e).getIsPlayers() == isDmgFromPlayersUnit) continue;
			Rectangle UnitArea = new Rectangle((int)e.getX(),(int)e.getY(),e.getWidth(),e.getHeight());
			if(UnitArea.intersects(area)) {
				e.hurt(damage);
				return;
			}
		}
		
		
	}
	//public boolean toprint= true;
	public Unit enemyInRange(Unit attacker) {
		
		//if (toprint ==false) return null;
		Rectangle area = new Rectangle();
		//System.out.print("attacker x: " + attacker.getX() +"y: "+ attacker.getY() );
		area.x = (int) (attacker.getX() - attacker.getRange());
		area.y = (int) (attacker.getY() - attacker.getRange());
		area.height = attacker.getHeight() + attacker.getRange()*2;
		area.width = attacker.getWidth() + attacker.getRange()*2;

		for(Entity e : entities){
			if(!(e instanceof Unit)) continue;
			if(((Unit)e).getIsPlayers() == attacker.getIsPlayers()) continue;
			if(e.equals(attacker)) continue;
			//System.out.print("x: "+ (e.getX()+e.getWidth()/2)+"y: "+(e.getY()+e.getHeight()/2));
		//	System.out.print("area:  " + area);
			//toprint = false;
			if(area.contains(e.getX()+e.getWidth()/2,e.getY()+(e.getHeight()/2))) {
				GridNode[][] grid = handler.getWorld().getGrid();
				int eY = (int)((e.getX()+e.getWidth()/2)/Tile.TILEWIDTH);
				int eX = (int)((e.getY()+e.getHeight()/2)/Tile.TILEHEIGHT);
				int attackerX = (int)((attacker.getX()+attacker.getWidth()/2)/Tile.TILEWIDTH);
				int attackerY = (int)((attacker.getY()+attacker.getHeight()/2)/Tile.TILEHEIGHT);
				if((!(attacker instanceof Archer)) && 
						(Math.abs(grid[eY][eX].getEntrenceLv() 
								- grid[attackerX][attackerY].getEntrenceLv()) > 1 )) continue;
					
				return (Unit)e;
			}
		}
		
		return null;
	}
	public boolean damageWall(Entity attacker) {
		
		Rectangle area1 = new Rectangle();
		Rectangle area2 = new Rectangle();
		//vertical
		area1.x = (int) (attacker.getX());
		area1.y = (int) (attacker.getY())-attacker.getHeight();
		area1.height = attacker.getHeight()*3;
		area1.width = attacker.getWidth();
		//horizontal
		area2.x = (int) (attacker.getX())-attacker.getWidth();;
		area2.y = (int) (attacker.getY());
		area2.height = attacker.getHeight();
		area2.width = attacker.getWidth()*3;
		
		for(Entity e : entities){
			if(!(e instanceof Building)) continue;
			Rectangle building = new Rectangle(e.bounds);
			building.x += e.getX();
			building.y += e.getY();
			if(area1.intersects(building) || area2.intersects(building)) {
				e.hurt(25);
				
				return false;
				
			}
		
		}
		return true;
	}
	public void spawnEnemies(int x, int y, int difficulty) {
		List<Point> locations = pathFinding.findLocations(new Point(x*Tile.TILEWIDTH,y*Tile.TILEHEIGHT), difficulty*6);
		if(locations==null) return;
		int i=0;
		Point location;
		for(int j = 0; j<difficulty; j++)
		{
			location = locations.get(i);
			if(i+1<locations.size()) i++;
			addEntity(new Knight(handler,location.x/Tile.TILEWIDTH,location.y/Tile.TILEHEIGHT,false));
			location = locations.get(i);
			if(i+1<locations.size()) i++;
			addEntity(new Gimli(handler,location.x/Tile.TILEWIDTH,location.y/Tile.TILEHEIGHT,false));
			location = locations.get(i);
			if(i+1<locations.size()) i++;
			addEntity(new Archer(handler,location.x/Tile.TILEWIDTH,location.y/Tile.TILEHEIGHT,false));
			location = locations.get(i);
			if(i+1<locations.size()) i++;
			addEntity(new Archer(handler,location.x/Tile.TILEWIDTH,location.y/Tile.TILEHEIGHT,false));
			location = locations.get(i);
			if(i+1<locations.size()) i++;
			addEntity(new Spearman(handler,location.x/Tile.TILEWIDTH,location.y/Tile.TILEHEIGHT,false));
			location = locations.get(i);
			if(i+1<locations.size()) i++;
			addEntity(new Spearman(handler,location.x/Tile.TILEWIDTH,location.y/Tile.TILEHEIGHT,false));
			
		}
		
	}


}
