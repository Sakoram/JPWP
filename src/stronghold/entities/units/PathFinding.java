package stronghold.entities.units;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import stronghold.Handler;
import stronghold.entities.GridNode;
import stronghold.tiles.Tile;
/**
 * Klasa odpowiedzialna za znajdywanie ścieżki od punktu a do punktu b.
 * @author a
 *
 */
public class PathFinding {

	private final int MOVE_STRAIGHT_COST = 10;
	private final int MOVE_DIAGONAL_COST = 14;


	private Handler handler;
	private GridNode[][] grid;
	private List<GridNode> openList;

	public PathFinding(Handler handler) {
		this.handler = handler;
		grid = handler.getWorld().getGrid();
	}
/**
 * Metoda znajdująca lokacje do których można wysłać jednostki.
 * @param dest punkt do okoła którego chcemy szukać lokacji.
 * @param amt ilość lokacji do znalezienia.
 * @return lista lokacji.
 */
	public List<Point> findLocations(Point dest, int amt) {
		openList = new ArrayList<GridNode>();
		GridNode currentNode = grid[(int) (dest.x / Tile.TILEWIDTH)][(int) (dest.y / Tile.TILEHEIGHT)];
		openList.add(currentNode);
		int i = 0, j = 0;
		while (openList.size() < amt && j < 100) {
			for (GridNode neighbourNode : getNeighbourList(currentNode)) {
				if (Tile.tiles[neighbourNode.getTileId()].isSolid()
						|| Math.abs(neighbourNode.getEntrenceLv() - currentNode.getEntrenceLv()) > 1) {
					continue;

				}
				if (!openList.contains(neighbourNode)) {
					openList.add(neighbourNode);

				}

			}
			j++;
			if (i + 1 < openList.size())
				i++;
			currentNode = openList.get(i);
		}
		List<Point> locations = new ArrayList<Point>();
		for (GridNode gridNode : openList) {
			locations.add(new Point(gridNode.getx() * Tile.TILEHEIGHT, gridNode.gety() * Tile.TILEHEIGHT));

		}
		return locations;
	}
/**
 * Metoda znajdująca drogę z punktu a do punktu b
 * @param startWorldPosition pozycja startowa
 * @param endWorldPosition pozycja do której chcemy znaleźć drogę.
 * @return lista punktów która nas doprowadzi z początku do końca.
 */
	public List<Point> findPath(Point startWorldPosition, Point endWorldPosition) {

		List<GridNode> path = findPath((int) startWorldPosition.x / Tile.TILEWIDTH,
				(int) startWorldPosition.y / Tile.TILEHEIGHT, (int) endWorldPosition.x / Tile.TILEWIDTH,
				(int) endWorldPosition.y / Tile.TILEHEIGHT);
		if (path == null) {
			return null;

		} else {
			List<Point> vectorPath = new ArrayList<Point>();
			for (GridNode gridNode : path) {
				vectorPath.add(new Point(gridNode.getx() * Tile.TILEHEIGHT, gridNode.gety() * Tile.TILEHEIGHT));

			}
			return vectorPath;
		}
	}
/**
 * Metoda znajdująca drogę z punktu a do punktu b
 * @param startX kordynat x początku drogi
 * @param startY kordynat y początku drogi
 * @param endX kordynat x końca drogi
 * @param endY kordynat y końca drogi
 * @return lista kafelków która nas doprowadzi z początku do końca.
 */
	public List<GridNode> findPath(int startX, int startY, int endX, int endY) { 
		GridNode startNode = grid[startX][startY];
		GridNode endNode = grid[endX][endY];

		if (startNode == null || endNode == null) {
			// Invalid Path
			return null;

		}

		openList = new ArrayList<GridNode>();
		openList.add(startNode);
		// closedList = new ArrayList<GridNode>();

		for (int x = 0; x < handler.getWorld().getWidth(); x++) {
			for (int y = 0; y < handler.getWorld().getWidth(); y++) {

				grid[x][y].gCost = 99999999;
				grid[x][y].hCost = 99999999;
				grid[x][y].CalculateFCost();
				grid[x][y].cameFromTile = null;
			}
		}

		startNode.gCost = 0;
		startNode.hCost = calculateDistanceCost(startNode, endNode);
		startNode.CalculateFCost();

		while (!openList.isEmpty()) {

			GridNode currentNode = getLowestFCostNode(openList);
			if (currentNode == endNode) {
				// Reached final node
				return calculatePath(endNode);
			}

			openList.remove(currentNode);

			for (GridNode neighbourNode : getNeighbourList(currentNode)) {
				if (Tile.tiles[neighbourNode.getTileId()].isSolid()
						|| Math.abs(neighbourNode.getEntrenceLv() - currentNode.getEntrenceLv()) > 1) {
					continue;

				}

				int tentativeGCost = currentNode.gCost + calculateDistanceCost(currentNode, neighbourNode);
				if (tentativeGCost < neighbourNode.gCost) {
					neighbourNode.cameFromTile = currentNode;
					neighbourNode.gCost = tentativeGCost;
					neighbourNode.hCost = calculateDistanceCost(neighbourNode, endNode);
					neighbourNode.CalculateFCost();

					if (!openList.contains(neighbourNode)) {
						openList.add(neighbourNode);
					}
				}

			}

		}
		// Out of nodes on the openList
		return null;

	}
/**
 * Metoda tworząca liste sąsiadujących kafelków.
 * @param currentNode kafelek początkowy
 * @return lista sąsiadujących kafelków.
 */
	private List<GridNode> getNeighbourList(GridNode currentNode) {
		List<GridNode> neighbourList = new ArrayList<GridNode>();

		if (currentNode.getx() - 1 >= 0) {
			// Left
			neighbourList.add(grid[currentNode.getx() - 1][currentNode.gety()]);
			// Left Down
			if (currentNode.gety() - 1 >= 0)
				neighbourList.add(grid[currentNode.getx() - 1][currentNode.gety() - 1]);
			// Left Up
			if (currentNode.gety() + 1 < handler.getWorld().getHeight())
				neighbourList.add(grid[currentNode.getx() - 1][currentNode.gety() + 1]);
		}
		if (currentNode.getx() + 1 < handler.getWorld().getWidth()) {
			// Right
			neighbourList.add(grid[currentNode.getx() + 1][currentNode.gety()]);
			// Right Down
			if (currentNode.gety() - 1 >= 0)
				neighbourList.add(grid[currentNode.getx() + 1][currentNode.gety() - 1]);
			// Right Up
			if (currentNode.gety() + 1 < handler.getWorld().getHeight())
				neighbourList.add(grid[currentNode.getx() + 1][currentNode.gety() + 1]);
		}
		// Down
		if (currentNode.gety() - 1 >= 0)
			neighbourList.add(grid[currentNode.getx()][currentNode.gety() - 1]);
		// Up
		if (currentNode.gety() + 1 < handler.getWorld().getHeight())
			neighbourList.add(grid[currentNode.getx()][currentNode.gety() + 1]);

		return neighbourList;
	}
/**
 * Metoda tworząca drogę z kafelków.
 * @param endNode końcowy kafelek
 * @return lista kafelków.
 */
	private List<GridNode> calculatePath(GridNode endNode) {
		List<GridNode> path = new ArrayList<GridNode>();
		path.add(endNode);
		GridNode currentNode = endNode;
		while (currentNode.cameFromTile != null) {
			path.add(currentNode.cameFromTile);
			currentNode = currentNode.cameFromTile;
		}
		Collections.reverse(path);
		return path;
	}
/**
 * Metoda obliczająca odległóść w lini prostej od jednego kafelka do drugiego 
 * @param a kafelek 1
 * @param b kafelek 2
 * @return odległość
 */
	private int calculateDistanceCost(GridNode a, GridNode b) {
		int xDistance = Math.abs(a.getx() - b.getx());
		int yDistance = Math.abs(a.gety() - b.gety());
		int remaining = Math.abs(xDistance - yDistance);
		return MOVE_DIAGONAL_COST * Math.min(xDistance, yDistance) + MOVE_STRAIGHT_COST * remaining;
	}
/**
 * Metoda zwracająca kafelek z najniższym FCost.
 * @param GridNodeList lista kafelków do przeszukania.
 * @return kafelek z najniższym FCost
 */
	private GridNode getLowestFCostNode(List<GridNode> GridNodeList) {
		GridNode lowestFCostNode = GridNodeList.get(0);
		for (int i = 1; i < GridNodeList.size(); i++) {
			if (GridNodeList.get(i).fCost < lowestFCostNode.fCost) {
				lowestFCostNode = GridNodeList.get(i);
			}
		}
		return lowestFCostNode;
	}

}
