package stronghold.entities.units;

import java.awt.Color;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Spearman extends Unit {
	private static final int TICKS_TO_ATTACK = handler.getGame().getFPS();
	public static final int DEFAULT_HEALTH = 300;
	public static final int RANGE = 2;
	public static final int DAMAGE = 50;
	public Spearman(Handler handler, float x, float y, boolean isPlayers) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT,DEFAULT_HEALTH,isPlayers,RANGE);
		this.speed = Tile.TILEHEIGHT/16*3;
		// TODO Auto-generated constructor stub
	}



	@Override
	public void render(Graphics g) {
		if(this.isSelected) drawHP(g,health,DEFAULT_HEALTH,isPlayers);
		g.drawImage(Assets.spear, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		

	}


	@Override
	public void atack(Entity enemy) {
		enemy.hurt(DAMAGE);
		
	}
	@Override
	public void tick() {
		super.tick(TICKS_TO_ATTACK);
		
	}

}
