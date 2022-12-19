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
            for(Tile s : t.neighbors){
//                if(s.getTileType() == TileType.Metro && t.getTileType() == TileType.Metro){
//                    MetroTile tmp = (MetroTile) s;
//                    g.addEdge(t, s, tmp.metroDistanceCost);
//                }
//                else
                    g.addEdge(t, s, s.distanceCost);
            }
        }
	}
    
}