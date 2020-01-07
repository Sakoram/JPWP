package stronghold.entities;
/**
 * Klasa, której obiekty to pojedyncze pola wykorzystywanego w znajdowaniu drogi.
 * @author a
 *
 */
public class GridNode {
	private int tileId;
	private int x;
	private int y;
	private int entranceLv; // 0 ground. 1,2 stairs , gate. 3 walls. 4 towers. 6 buildings
	public GridNode cameFromTile;
	public int gCost;
	public int hCost;
	public int fCost;

	public GridNode(int tileId, int x, int y) {
		this.tileId = tileId;
		this.entranceLv = 0;
		this.x = x;
		this.y = y;
	}

	public void CalculateFCost() {
		this.fCost = gCost + hCost;
	}

	public int getEntrenceLv() {
		return entranceLv;
	}

	public int getTileId() {
		return tileId;
	}

	public void setTileId(int tileId) {
		this.tileId = tileId;
	}

	public void setEntranceLv(int Lv) {
		this.entranceLv = Lv;

	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}
}
