package finalproject;

import java.util.ArrayList;

import finalproject.system.Tile;

public class Graph {
	// TODO level 2: Add fields that can help you implement this data type
    public static ArrayList<Edge> edges;
    public static ArrayList<Tile> vertices;

    // TODO level 2: initialize and assign all variables inside the constructor
	public Graph(ArrayList<Tile> vertices) {
        Graph.vertices = vertices;
        edges = new ArrayList<>();
	}
	
    // TODO level 2: add an edge to the graph
    public void addEdge(Tile origin, Tile destination, double weight){
//    	Edge edge = new Edge(origin, destination, weight);
//        edges.add(edge);
//        if(!origin.neighbors.contains(destination)){
//            origin.neighbors.add(destination);
//        }
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
    	return t.neighbors;
    }
	
	// TODO level 2: return total cost for the input path
	public double computePathCost(ArrayList<Tile> path) {
        double totalCost = 0;
        for(int i = 0; i < path.size()-1;i++){
            for(Edge e : edges){
                if(e.getStart() == path.get(i) && e.getEnd() == path.get(i+1)){
                    totalCost += e.weight;
//                    System.out.println(path.get(i) + " and " + path.get(i+1) + "'s weight is " + e.weight);
                }
            }
        }
//        System.out.println("total cost is " + totalCost);
		return totalCost;
	}

    public ArrayList<Tile> getVertices(){
        return vertices;
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