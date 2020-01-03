package stronghold.entities.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import stronghold.Handler;
import stronghold.entities.Entity;
import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class Arrow extends Entity {
	private float rotation=0;

	public Arrow(Handler handler, float x, float y) {
		super(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		rotation += 1;

	}

	@Override
	public void render(Graphics g) {
		double rotationRequired = Math.toRadians (rotation);
		double locationX = width / 4;
		double locationY = height / 4;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		Graphics2D g2d = (Graphics2D)g;
		// Drawing the rotated image at the required drawing locations
		//g2d.drawImage(op.filter(Assets.arrow, null), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
		g2d.drawImage(op.filter(Assets.arrow, null), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width/2, height/2, null);

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	@Override
	public void select(Rectangle selection) {
		// TODO Auto-generated method stub

	}

}
