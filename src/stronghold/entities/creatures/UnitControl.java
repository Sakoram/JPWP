package stronghold.entities.creatures;

//import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import stronghold.Handler;
//import stronghold.entities.Entity;
//import stronghold.tiles.Tile;

public class UnitControl {
protected Handler handler;

public UnitControl(Handler handler) {
	this.handler = handler;
	System.out.println("pressed on x, y: ");
}
public void onMouseMove(MouseEvent e){
	System.out.println("pressed on x, y: " + e.getX() +", "+ e.getY() );
	
}
}