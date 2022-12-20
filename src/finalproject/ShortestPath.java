package finalproject;


import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.MetroTile;

public class ShortestPath extends PathFindingService {
    //TODO level 4: find distance prioritized path
    public ShortestPath(Tile start) {
        super(start);
    }

	@Override
	public void generateGraph() {
		// TODO Auto-generated method stub
        g = new Graph(vertices);
        for(Tile t : vertices) {
            for(Tile s : g.getNeighbors(t)){
                if(t.type == TileType.Metro && s.type == TileType.Metro){
                    ((MetroTile)s).metroDistanceCost = getManhattanDistance(t, s)/((MetroTile) s).metroCommuteFactor;
                    g.addEdge(t,s,((MetroTile) s).metroDistanceCost);
                }
                else
                    g.addEdge(t, s, s.distanceCost);
            }
        }
	}
    
}