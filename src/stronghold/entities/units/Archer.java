package stronghold.entities.units;

import java.awt.Color;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.entities.objects.Arrow;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Archer extends Unit {
	private static final int TICKS_TO_ATTACK = (int) (handler.getGame().getFPS()*1.5);
	public static final int RANGE = 8;
	public static final int DEFAULT_HEALTH = 300;
	
	public Archer(Handler handler, float x, float y, boolean isPlayers) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT,DEFAULT_HEALTH,isPlayers,RANGE);
		this.speed = Tile.TILEHEIGHT/16*3;
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void render(Graphics g) {
		super.render(g, Assets.bow, DEFAULT_HEALTH, isPlayers);
		//if(this.isSelected) 
		//	drawHP(g,health,DEFAULT_HEALTH,isPlayers);
		
		//g.drawImage(Assets.bow, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}



	@Override
	public void atack(Entity enemy) {
		handler.getWorld().getEntityManager().addEntity(new Arrow(handler, x ,y, enemy.getX(), enemy.getY(), isPlayers));
	}
	@Override
	public void tick() {
		super.tick(TICKS_TO_ATTACK);
	}

}
