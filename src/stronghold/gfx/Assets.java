package stronghold.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage bedrock, dirt, grass1, stone, tree, iron, sand, grass2, 
	bricks1, bricks2, water, planks1,planks2,planks3, 
	crown, axe, bow, spear, sword, worker;

	public static BufferedImage[] btn_start;

	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		bedrock = sheet.crop(0, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass1 = sheet.crop(width * 2, 0, width, height);
		grass2 = sheet.crop(width * 2, height, width, height);
		bricks1 = sheet.crop(width * 3, 0, width, height);
		bricks2 = sheet.crop(0, width, width, height);
		iron = sheet.crop(width, width, width, height);
		stone = sheet.crop(width, height*2, width, height);
		tree = sheet.crop(width*3, height*2, width, height);
		water = sheet.crop(width*3, height*3, width, height);
		sand = sheet.crop(width*3, height, width, height);
		planks3 = sheet.crop(width, height*3, width, height);
		planks2 = sheet.crop(width*2, height*3, width, height);
		planks1 = sheet.crop(width*2, height*2, width, height);
		crown = sheet.crop(width*4, 0, width, height);
		axe = sheet.crop(width*4, height, width, height);
		bow = sheet.crop(width*5, 0, width, height);
		spear = sheet.crop(width*6, 0, width, height);
		sword  = sheet.crop(width*5, height, width, height);
		worker = sheet.crop(width*5, height*2, width, height);
	}
	
}
