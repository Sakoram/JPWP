package stronghold.ui;

import stronghold.Handler;
import stronghold.entities.statics.buildings.Building;


public class BuildingMenuUI extends UIManager {
	protected static Building building=null;

	public BuildingMenuUI(Handler handler) {
		super(handler);

	}
	public void setBuilding(Building building) {
		BuildingMenuUI.building=building;
	}

}
