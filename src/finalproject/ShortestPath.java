package finalproject;


import finalproject.system.Tile;

import static finalproject.Graph.edges;
import static finalproject.Graph.vertices;

public class ShortestPath extends PathFindingService {
    //TODO level 4: find distance prioritized path
    public ShortestPath(Tile start) {
        super(start);
    }

	@Override
	public void generateGraph() {
		// TODO Auto-generated method stub
        for(Tile t : vertices) {
            for(Tile s : t.neighbors){
                edges.add(new Graph.Edge(t, s, s.costEstimate));
            }
        }

	}
    
}