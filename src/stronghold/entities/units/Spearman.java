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
	public Spearman(Handler handler, float x, float y, boolean isPlayers) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT,DEFAULT_HEALTH,isPlayers,RANGE);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void render(Graphics g) {
		if(this.isSelected) drawHP(g,health,DEFAULT_HEALTH,isPlayers);
		g.drawImage(Assets.spear, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		

	}


	@Override
	public void atack(Entity enemy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tick() {
		super.tick(TICKS_TO_ATTACK);
		
	}

}
