package stronghold.entities.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.vecmath.Vector2d;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Arrow extends Entity {
	private static final int speed = 10;
	private static final int areaOfDamage = Tile.TILEWIDTH;
	private static final int DAMAGE = 100;
	//private float rotation=0;
	private Point target;
	private boolean isPlayers;
	private AffineTransformOp op;

	public Arrow(Handler handler, float x, float y, float targetX, float targetY, boolean isPlayers) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, 0);
		this.target = new Point((int)targetX + (Tile.TILEWIDTH/2), (int)targetY + (Tile.TILEHEIGHT/2));
		this.isPlayers = isPlayers;
		
		Vector2d vec = new Vector2d(targetX,targetY);
		vec.sub(new Vector2d(x,y));
		Vector2d vec2 = new Vector2d(1,-1); // vector of arrow image
		double rotationRequired = Math.atan2(vec.y, vec.x) - Math.atan2(vec2.y, vec2.x);
		double locationX = width / 4;
		double locationY = height / 4;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		this.op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}

	@Override
	public void tick() {
		//rotation += 1;
		
		if(moveToDest(target,speed)) die();
		

	}

	@Override
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;
		// Drawing the rotated image at the required drawing locations
		//g2d.drawImage(op.filter(Assets.arrow, null), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
		g2d.drawImage(op.filter(Assets.arrow, null), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width/2, height/2, null);

	}

	@Override
	public void die() {
		super.die();
		handler.getWorld().getEntityManager().damageUnits(DAMAGE, new Rectangle((int)x-(areaOfDamage/2), (int)y-(areaOfDamage/2),
				areaOfDamage,areaOfDamage), isPlayers);
		

	}

	@Override
	public void select(Rectangle selection) {
		// TODO Auto-generated method stub

	}

}
