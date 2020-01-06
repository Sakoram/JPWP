package stronghold.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import stronghold.gfx.Assets;
import stronghold.tiles.Tile;

public class UIImageButton extends UIObject {

	private ClickListener clicker;
	private BufferedImage[] image;

	public UIImageButton(int x, int y, int width, int height, BufferedImage[] image, ClickListener clicker) {
		super(x, y, width, height);
		this.clicker = clicker;
		this.image = image;
	}

	public UIImageButton(int x, int y, int width, int height, BufferedImage image, ClickListener clicker) {
		super(x, y, width, height);
		this.clicker = clicker;
		this.image = new BufferedImage[2];
		this.image[0] = image;
		this.image[1] = image;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		if (hovering)

			g.drawImage(image[1], bounds.x, bounds.y, bounds.width, bounds.height, null);
		else

			g.drawImage(image[0], bounds.x, bounds.y, bounds.width, bounds.height, null);

	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
