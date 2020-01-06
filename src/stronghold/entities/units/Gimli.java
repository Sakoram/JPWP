package stronghold.entities.units;

import java.awt.Color;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Gimli extends Unit {
	private static final int TICKS_TO_ATTACK = (int)(handler.getGame().getFPS()*1.5);
	public static final int DEFAULT_HEALTH = 900;
	public static final int DAMAGE = 250;
	public static final int RANGE = 1;
	public Gimli(Handler handler, float x, float y, boolean isPlayers) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT,DEFAULT_HEALTH,isPlayers,RANGE);
		this.speed = Tile.TILEHEIGHT/32*2;
		// TODO Auto-generated constructor stub
	}



	@Override
	public void render(Graphics g) {
		if(this.isSelected) drawHP(g,isPlayers);
		g.drawImage(Assets.axe, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		

	}




	@Override
	public void atack(Entity enemy) {
		enemy.hurt(DAMAGE);
		
	}
	@Override
	public void tick() {
		super.tick(TICKS_TO_ATTACK);
		
	}
	@Override
	public int getMaxHealth() {
		return DEFAULT_HEALTH;
	}

}
