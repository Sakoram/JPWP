package stronghold.entities.units;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import stronghold.Handler;
import stronghold.entities.GridNode;
import stronghold.tiles.Tile;

public class PathFinding {

    private final int MOVE_STRAIGHT_COST = 10;
    private final int MOVE_DIAGONAL_COST = 14;

    //public static PathFinding Instance;
    
    private Handler handler;
    private GridNode[][] grid;
    private List<GridNode> openList;
    private List<GridNode> closedList;

    public PathFinding(Handler handler) {
       // Instance = this;
        this.handler = handler;
        grid = handler.getWorld().getGrid();
        System.out.println("inicjacja ");
    }

    public List<Point> FindPath(Point startWorldPosition, Point endWorldPosition) {
        
    	 System.out.println("findPath ");
        List<GridNode> path = FindPath((int)startWorldPosition.x, (int)startWorldPosition.y, 
        		(int)endWorldPosition.x, (int)endWorldPosition.y);
        if (path == null) {
            return null;
            
        } else {
            List<Point> vectorPath = new ArrayList<Point>();
            for (GridNode gridNode : path) {
                vectorPath.add(new Point(gridNode.getx()*Tile.TILEHEIGHT + (int)(Tile.TILEHEIGHT * .5f), 
                		gridNode.gety()*Tile.TILEHEIGHT + (int)( Tile.TILEHEIGHT * .5f)  ));
                
            }
            return vectorPath;
        }
    }

    public List<GridNode> FindPath(int startX, int startY, int endX, int endY) { // @@@@@ todo kody nie są podzielone przez wielkosc tile
    	System.out.println("FindPath"+ startX + ", "+ startY+ ", " + endX+ ", " + endY);
    	GridNode startNode = grid[startX][startY];
        GridNode endNode = grid[endX][endY];

        if (startNode == null || endNode == null) {
            // Invalid Path
        	System.out.println("dupa2 ");
            return null;
            
        }

        openList = new ArrayList<GridNode>();
        openList.add(startNode);
        closedList = new ArrayList<GridNode>();

        for (int x = 0; x < handler.getWorld().getWidth(); x++) {
            for (int y = 0; y < handler.getWorld().getWidth(); y++) {
                
            	grid[x][y].gCost = 99999999;
                grid[x][y].CalculateFCost();
                grid[x][y].cameFromTile = null;
            }
        }

        startNode.gCost = 0;
        startNode.hCost = CalculateDistanceCost(startNode, endNode);
        startNode.CalculateFCost();
        

        System.out.println("dziendobry "+ openList.size() + ", "+ openList);
        while (!openList.isEmpty()) {
        	
            GridNode currentNode = GetLowestFCostNode(openList);
            if (currentNode == endNode) {
                // Reached final node
                return CalculatePath(endNode);
            }

            openList.remove(currentNode);
            closedList.add(currentNode);

            for(GridNode neighbourNode : GetNeighbourList(currentNode)) {
                if (closedList.contains(neighbourNode)) continue;
                if (Tile.tiles[neighbourNode.getTileId()].isSolid()) { //@@@@@@@@@@@@@@2todo
                    closedList.add(neighbourNode);
                    continue;
                }

                int tentativeGCost = currentNode.gCost + CalculateDistanceCost(currentNode, neighbourNode);
                if (tentativeGCost < neighbourNode.gCost) {
                    neighbourNode.cameFromTile = currentNode;
                    neighbourNode.gCost = tentativeGCost;
                    neighbourNode.hCost = CalculateDistanceCost(neighbourNode, endNode);
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

    private List<GridNode> GetNeighbourList(GridNode currentNode) {
        List<GridNode> neighbourList = new ArrayList<GridNode>();

        if (currentNode.getx() - 1 >= 0) {
            // Left
            neighbourList.add(grid[currentNode.getx()-1][currentNode.gety()]);
            // Left Down
            if (currentNode.gety() - 1 >= 0) neighbourList.add(grid[currentNode.getx() - 1][currentNode.gety() - 1]);
            // Left Up
            if (currentNode.gety() + 1 < handler.getGame().getHeight()) neighbourList.add(grid[currentNode.getx() - 1][currentNode.gety() + 1]);
        }
        if (currentNode.getx() + 1 < handler.getGame().getWidth()) {
            // Right
            neighbourList.add(grid[currentNode.getx()+1][currentNode.gety()]);
            // Right Down
            if (currentNode.gety() - 1 >= 0) neighbourList.add(grid[currentNode.getx()+1][currentNode.gety()-1]);
            // Right Up
            if (currentNode.gety() + 1 < handler.getGame().getHeight()) neighbourList.add(grid[currentNode.getx()+1][currentNode.gety()+1]);
        }
        // Down
        if (currentNode.gety() - 1 >= 0) neighbourList.add(grid[currentNode.getx()][currentNode.gety()-1]);
        // Up
        if (currentNode.gety() + 1 < handler.getGame().getHeight()) neighbourList.add(grid[currentNode.getx()][currentNode.gety()+1]);

        return neighbourList;
    }


    private List<GridNode> CalculatePath(GridNode endNode) {
        List<GridNode> path = new ArrayList<GridNode>();
        path.add(endNode);
        GridNode currentNode = endNode;
        while (currentNode.cameFromTile != null) {
            path.add(currentNode.cameFromTile);
            currentNode = currentNode.cameFromTile;
        }
        Collections.reverse(path);
        for(GridNode node : path) { System.out.println("path: "+ node.getx()+", "+ node.gety()); node.setTileId(6); }//debug
        return path;
    }

    private int CalculateDistanceCost(GridNode a, GridNode b) {
        int xDistance = Math.abs(a.getx() - b.getx());
        int yDistance = Math.abs(a.gety() - b.gety());
        int remaining = Math.abs(xDistance - yDistance);
        return MOVE_DIAGONAL_COST * Math.min(xDistance, yDistance) + MOVE_STRAIGHT_COST * remaining;
    }

    private GridNode GetLowestFCostNode(List<GridNode> GridNodeList) {
        GridNode lowestFCostNode = GridNodeList.get(0);
        for (int i = 1; i < GridNodeList.size(); i++) {
            if (GridNodeList.get(i).fCost < lowestFCostNode.fCost) {
                lowestFCostNode = GridNodeList.get(i);
            }
        }
        return lowestFCostNode;
    }

}
