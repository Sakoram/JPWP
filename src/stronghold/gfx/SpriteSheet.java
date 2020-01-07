package stronghold.gfx;

import java.awt.image.BufferedImage;
/**
 * Klasa wycinająca pojedyncze tekstury z pliku z teksturami.
 * @author a
 *
 */
public class SpriteSheet {

	private BufferedImage sheet;

	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

}
