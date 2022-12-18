package finalproject;


import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.MetroTile;

import java.util.ArrayList;

public class ShortestPath extends PathFindingService {
    //TODO level 4: find distance prioritized path
    public ShortestPath(Tile start) {
        super(start);
    }

	@Override
	public void generateGraph() {
		// TODO Auto-generated method stub
        ArrayList<Tile> vertices = GraphTraversal.BFS(source);
        g = new Graph(vertices);
        for (Tile vertex: vertices)
        {
            for (Tile neighbor :g.getNeighbors(vertex))
            {
                if (vertex.type == TileType.Metro && neighbor.type == TileType.Metro)
                {
                    ((MetroTile)vertex).metroDistanceCost = getManhattanDistance(vertex, neighbor) / ((MetroTile)vertex).metroCommuteFactor;
                    g.addEdge(vertex, neighbor, getManhattanDistance(vertex, neighbor) / ((MetroTile)vertex).metroCommuteFactor);
                }
                else
                {
                    g.addEdge(vertex, neighbor, neighbor.distanceCost);
                }
            }
        }
	}
}