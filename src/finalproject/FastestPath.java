package finalproject;

import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.MetroTile;

import java.util.ArrayList;

public class FastestPath extends PathFindingService {
    //TODO level 6: find time prioritized path
    public FastestPath(Tile start) {
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
                    ((MetroTile)neighbor).metroTimeCost = getManhattanDistance(vertex, neighbor) * ((MetroTile)vertex).metroCommuteFactor;
                    g.addEdge(vertex, neighbor, getManhattanDistance(vertex, neighbor) * ((MetroTile)vertex).metroCommuteFactor);
                }
                else
                {
                    g.addEdge(vertex, neighbor, neighbor.timeCost);
                }
            }
        }
	}

}
