package finalproject;

import java.util.ArrayList;
import java.util.HashSet;

import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.*;

public class Graph {
	// TODO level 2: Add fields that can help you implement this data type
    ArrayList<Edge> edges;
    ArrayList<Tile> vertices;
    // TODO level 2: initialize and assign all variables inside the constructor
	public Graph(ArrayList<Tile> vertices) {
		this.vertices = vertices;
	}

    public ArrayList<Tile> getVertices()
    {
        return vertices;
    }
    // TODO level 2: add an edge to the graph
    public void addEdge(Tile origin, Tile destination, double weight){
    	if (edges == null)
        {
            edges = new ArrayList<>();
        }

        edges.add(new Edge(origin, destination, weight));
        if (!origin.neighbors.contains(destination))
        {
            origin.neighbors.add(destination);
        }
    }
    
    // TODO level 2: return a list of all edges in the graph
	public ArrayList<Edge> getAllEdges() {
		return edges;
	}
  
	// TODO level 2: return list of tiles adjacent to t
	public ArrayList<Tile> getNeighbors(Tile t) {
        ArrayList<Tile> ret = new ArrayList<>();
        for (Tile neighbor : t.neighbors)
        {
            if (neighbor.isWalkable())
            {
                ret.add(neighbor);
            }
        }
    	return ret;
    }
	
	// TODO level 2: return total cost for the input path
	public double computePathCost(ArrayList<Tile> path) {
        double ret = 0.0;
        for (int i = 0; i < path.size() - 1; i++)
        {
            for (Edge edge: edges)
            {
                if (edge.getStart().equals(path.get(i)) && edge.getEnd().equals(path.get(i+1)))
                {
                    ret += edge.weight;
                }
            }
        }
		return ret;
	}

    public Edge getEdge(Tile src, Tile dest)
    {
        for (Edge e : edges)
        {
            if (e.origin.equals(src) && e.destination.equals(dest))
            {
                return e;
            }
        }

        return null;
    }

    public Tile getDestination()
    {
        for (Tile tile : vertices)
        {
            if (tile.isDestination)
            {
                return tile;
            }
        }
        return null;
    }
   
    public static class Edge{
    	Tile origin;
    	Tile destination;
    	double weight;

        // TODO level 2: initialize appropriate fields
        public Edge(Tile s, Tile d, double cost){
        	this.origin = s;
            this.destination = d;
            this.weight = cost;
        }
        
        // TODO level 2: getter function 1
        public Tile getStart(){
            return this.origin;
        }

        
        // TODO level 2: getter function 2
        public Tile getEnd() {
            return this.destination;
        }
        
    }
    
}