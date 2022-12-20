package finalproject;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import finalproject.system.Tile;

public class SafestShortestPath extends ShortestPath {
	public int health;
	public Graph costGraph;
	public Graph damageGraph;
	public Graph aggregatedGraph;

	//TODO level 8: finish class for finding the safest shortest path with given health constraint
	public SafestShortestPath(Tile start, int health) {
		super(start);
		this.health = health;
		generateGraph();
	}

	@Override
	public void generateGraph() {
		System.out.println("call generateGraph");
		ArrayList<Tile> costGraphVertices = vertices;
		ArrayList<Tile> damageGraphVertices = vertices;
		ArrayList<Tile> aggregatedGraphVertices = vertices;
		this.costGraph = new Graph(costGraphVertices);
		this.damageGraph = new Graph(damageGraphVertices);
		this.aggregatedGraph = new Graph(aggregatedGraphVertices);

		for(Tile t : vertices) {
			for(Tile s : t.neighbors){
				this.costGraph.addEdge(t, s, s.distanceCost);
				System.out.println(t + " " + s + " " + s.distanceCost);
				for(Graph.Edge e : costGraph.getAllEdges()){
					if(e.getStart() == t && e.getEnd() == s)
						System.out.println(e.weight);
				}
			}
		}

		for(Tile t : vertices) {
			for(Tile s : t.neighbors){
				damageGraph.addEdge(t, s, s.damageCost);
				System.out.println(costGraph.getAllEdges().size());
			}
		}
		for(Tile t : vertices) {
			for(Tile s : t.neighbors){
				damageGraph.addEdge(t, s, s.damageCost);
				System.out.println(costGraph.getAllEdges().size());

			}
		}

		for(Tile t : vertices) {
			for(Tile s : t.neighbors){
				System.out.println(t + " 111 " + s + " 111 " + s.distanceCost);
				for(Graph.Edge e : costGraph.getAllEdges()){
					if(e.getStart() == t && e.getEnd() == s)
						System.out.println(e.weight);
				}
			}
		}
	}

	public ArrayList<Tile> findPath(Tile startNode, ArrayList<Tile> waypoints){
		PathFindingService.g = costGraph;
		LinkedList<Tile> ThisWaypoints = new LinkedList<>(waypoints);
		ArrayList<Tile> pathC = findPath(startNode, ThisWaypoints);
		double damageCostPathC = damageGraph.computePathCost(pathC);
		double distanceCostPathC = costGraph.computePathCost(pathC);
		if(damageCostPathC < health)
			return pathC;
		PathFindingService.g = damageGraph;
		ArrayList<Tile> pathD = findPath(startNode, ThisWaypoints);
		double damageCostPathD = damageGraph.computePathCost(pathD);
		double distanceCostPathD = costGraph.computePathCost(pathD);
		if(damageCostPathD > health)
			return null;
		double lambda = (distanceCostPathC -distanceCostPathD) / (damageCostPathC - damageCostPathD);

		return null;
	}

}
