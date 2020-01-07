package stronghold.gfx;

import java.awt.image.BufferedImage;
/**
 * Klasa przechowująca tekstury.
 * @author a
 *
 */
public class Assets {

	private static final int width = 32, height = 32;

	public static BufferedImage bedrock, dirt, grass1, stone, tree, bars, iron, sand, grass2, bricks1, bricks2, bricks3,
			water, planks1, planks2, planks3, crown, axe, bow, spear, sword, worker, arrow;

	public static BufferedImage[] btn_hard, btn_easy, btn_medium, btn_again;

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

		btn_hard = new BufferedImage[2];
		btn_hard[0] = sheet.crop(0, height * 4, width * 2, height);
		btn_hard[1] = sheet.crop(width * 2, height * 4, width * 2, height);

		btn_again = new BufferedImage[2];
		btn_again[0] = sheet.crop(0, height * 5, width * 2, height);
		btn_again[1] = sheet.crop(width * 2, height * 5, width * 2, height);

		btn_easy = new BufferedImage[2];
		btn_easy[0] = sheet.crop(0, height * 6, width * 2, height);
		btn_easy[1] = sheet.crop(width * 2, height * 6, width * 2, height);

		btn_medium = new BufferedImage[2];
		btn_medium[0] = sheet.crop(0, height * 7, width * 2, height);
		btn_medium[1] = sheet.crop(width * 2, height * 7, width * 2, height);

		bedrock = sheet.crop(0, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass1 = sheet.crop(width * 2, 0, width, height);
		grass2 = sheet.crop(width * 2, height, width, height);
		bricks1 = sheet.crop(width * 3, 0, width, height);
		bricks2 = sheet.crop(0, height, width, height);
		bricks3 = sheet.crop(0, 3 * height, width, height);
		iron = sheet.crop(width, height, width, height);
		stone = sheet.crop(width, height * 2, width, height);
		tree = sheet.crop(width * 3, height * 2, width, height);
		bars = sheet.crop(width * 4, height * 2, width / 2, height / 2);
		water = sheet.crop(width * 3, height * 3, width, height);
		sand = sheet.crop(width * 3, height, width, height);
		planks3 = sheet.crop(width, height * 3, width, height);
		planks2 = sheet.crop(width * 2, height * 3, width, height);
		planks1 = sheet.crop(width * 2, height * 2, width, height);
		crown = sheet.crop(width * 4, 0, width, height);
		axe = sheet.crop(width * 4, height, width, height);
		bow = sheet.crop(width * 5, 0, width, height);
		spear = sheet.crop(width * 6, 0, width, height);
		sword = sheet.crop(width * 5, height, width, height);
		worker = sheet.crop(width * 5, height * 2, width, height);

		arrow = sheet.crop(0, height * 2, width, height);
	}

}
