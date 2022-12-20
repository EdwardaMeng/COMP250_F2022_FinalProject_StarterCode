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
	}

	@Override
	public void generateGraph() {
		vertices = DijkstraTraverse(source);
		this.costGraph = new Graph(vertices);
		for(Tile t : vertices) {
			for(Tile s : costGraph.getNeighbors(t)){
				this.costGraph.addEdge(t, s, s.distanceCost);
			}
		}

		this.damageGraph = new Graph(vertices);
		for(Tile t : vertices) {
			for(Tile s : damageGraph.getNeighbors(t)){
				damageGraph.addEdge(t, s, s.damageCost);
			}
		}

		this.aggregatedGraph = new Graph(vertices);
		for(Tile t : vertices) {
			for(Tile s : aggregatedGraph.getNeighbors(t)){
				damageGraph.addEdge(t, s, s.damageCost);

			}
		}

		g = costGraph;
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
