package finalproject;


import finalproject.system.Tile;

import java.util.ArrayList;

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
        ArrayList<Tile> vertices = GraphTraversal.BFS(source);
        Graph g = new Graph(vertices);
        for(Tile t : vertices) {
            for(Tile s : t.neighbors){
                g.addEdge(t, s, s.distanceCost);
            }
        }
	}
    
}