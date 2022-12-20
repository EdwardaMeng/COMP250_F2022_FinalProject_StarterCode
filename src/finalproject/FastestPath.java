package finalproject;

import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.MetroTile;

public class FastestPath extends PathFindingService {
    //TODO level 6: find time prioritized path
    public FastestPath(Tile start) {
        super(start);
    }

	@Override
	public void generateGraph() {
		// TODO Auto-generated method stub
        g = new Graph(vertices);
        for(Tile t : vertices) {
            for(Tile s : t.neighbors){
                if(t.type == TileType.Metro && s.type == TileType.Metro){
                    ((MetroTile)s).metroTimeCost = getManhattanDistance(t, s)*((MetroTile) s).metroCommuteFactor;
                    g.addEdge(t,s,((MetroTile) s).metroTimeCost);
                }
                else
                    g.addEdge(t, s, s.timeCost);
            }
        }
	}

}
