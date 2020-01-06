package stronghold.tiles;

import stronghold.gfx.Assets;

public class BedrockTile extends Tile {

	public BedrockTile(int id) {
		super(Assets.bedrock, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}