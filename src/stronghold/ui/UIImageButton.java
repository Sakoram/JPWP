package stronghold.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Klasa przycisku na ekranie.
 * @author a
 *
 */
public class UIImageButton extends UIObject {

	private ClickListener clicker;
	private BufferedImage[] image;
/**
 * Konstruktor przycisku
 * @param x koordynat x na ekranie.
 * @param y koordynat x na ekranie.
 * @param width szerokość na ekranie.
 * @param height wysokość na ekranie.
 * @param image textura przycisku po najechaniu i standardowa.
 * @param clicker funcja odpalana po kliknięciu przycisku.
 */
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
