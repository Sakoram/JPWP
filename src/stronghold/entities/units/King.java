package stronghold.entities.units;

import java.awt.Color;
import java.awt.Graphics;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class King extends Unit {
	private static int TICKS_TO_ATTACK;
	public static final int DEFAULT_HEALTH = 2000;
	public static final int RANGE = 1;
	public static final int DAMAGE = 500;
	public King(Handler handler, float x, float y, boolean isPlayers) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT,DEFAULT_HEALTH,isPlayers,RANGE);
		this.speed = Tile.TILEHEIGHT/16*1;
		TICKS_TO_ATTACK = handler.getGame().getFPS();
	}



	@Override
	public void render(Graphics g) {
		if(this.isSelected) drawHP(g,isPlayers);
		g.drawImage(Assets.crown, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		

	}

	@Override
	public void die() {
		super.die();
		handler.getGame().gameOver(false);

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
