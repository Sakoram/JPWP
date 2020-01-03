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
import stronghold.entities.creatures.Player;
import stronghold.entities.units.Archer;
import stronghold.entities.units.PathFinding;
import stronghold.entities.units.Unit;
import stronghold.tiles.Tile;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private PathFinding pathFinding;
	/*private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b) {
			if(a instanceof Unit)
				return 1;
			return -1;
		}
	};*/
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
		this.pathFinding = new PathFinding(handler);
	}
	
	public void tick(){
		for(int i = 0;i < entities.size();i++){
			Entity e = entities.get(i);
			if(!e.isActive())
				entities.remove(e);
			e.tick();
		}
		//entities.sort(renderSorter);
	}
	
	public void render(Graphics g){
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
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	public void selectEntities(Rectangle selection) {
		selection.x+=handler.getGameCamera().getxOffset();
		selection.y+=handler.getGameCamera().getyOffset();
		for(Entity e : entities){
			e.select(selection);
		}
		
	}
	public void deselectEntities() {
		for(Entity e : entities){
			e.isSelected=false;
		}
		
	}
	public void MoveUnits(Point dest) {
		dest.x+=handler.getGameCamera().getxOffset();
		dest.y+=handler.getGameCamera().getyOffset();
		List<Entity> unitsToMove = new ArrayList<Entity>();
		for(Entity e : entities){
			if(e instanceof Unit && e.isSelected)
				unitsToMove.add(e);
		}
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

}
