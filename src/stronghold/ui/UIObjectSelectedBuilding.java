package stronghold.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import stronghold.Handler;
import stronghold.entities.statics.buildings.Building;
import stronghold.entities.statics.buildings.Gate;
import stronghold.tiles.Tile;

public class UIObjectSelectedBuilding extends UIObject {
	private Building selectedBuilding;
	private Handler handler;

	public UIObjectSelectedBuilding(Handler handler, Building selectedBuilding) {
		super(handler.getWidth() + 1, 0, selectedBuilding.getWidth(), selectedBuilding.getHeight());
		this.selectedBuilding = selectedBuilding;
		this.handler = handler;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		selectedBuilding.render(g,
				((int) ((this.bounds.x + handler.getGameCamera().getxOffset()) / Tile.TILEWIDTH)) * Tile.TILEWIDTH,
				((int) ((this.bounds.y + handler.getGameCamera().getyOffset()) / Tile.TILEHEIGHT)) * Tile.TILEHEIGHT);

	}

	@Override
	public void onClick() {
		selectedBuilding.setX(
				((int) ((this.bounds.x + handler.getGameCamera().getxOffset()) / Tile.TILEWIDTH)) * Tile.TILEWIDTH);
		selectedBuilding.setY(
				((int) ((this.bounds.y + handler.getGameCamera().getyOffset()) / Tile.TILEHEIGHT)) * Tile.TILEHEIGHT);
		selectedBuilding.setLvUnder();
		handler.getWorld().getEntityManager().addEntity(selectedBuilding);
		try {
			selectedBuilding = (Building) selectedBuilding.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// handler.getGame().standardGameUI.removeObject(this);

	}

	@Override
	public void onMouseMove(MouseEvent e) {
		this.bounds.x = e.getX();
		this.bounds.y = e.getY();
		super.onMouseMove(e);
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			handler.getGame().standardGameUI.removeObject(this);
			return;
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			selectedBuilding.flip();
		} else
			super.onMouseRelease(e);

	}

}
