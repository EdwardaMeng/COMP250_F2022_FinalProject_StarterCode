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
		ArrayList<Tile> vertices = DijkstraTraverse(source);
		costGraph = new Graph(vertices);
		for(Tile t : vertices) {
			for(Tile s : costGraph.getNeighbors(t)){
				costGraph.addEdge(t, s, s.distanceCost);
			}
		}

		damageGraph = new Graph(vertices);
		for(Tile t : vertices) {
			for(Tile s : damageGraph.getNeighbors(t)){
				damageGraph.addEdge(t, s, s.damageCost);
			}
		}

		aggregatedGraph = new Graph(vertices);
		for(Tile t : vertices) {
			for(Tile s : aggregatedGraph.getNeighbors(t)){
				aggregatedGraph.addEdge(t, s, s.damageCost);

			}
		}

		g = costGraph;
	}

//	@Override
	public ArrayList<Tile> findPath(Tile startNode, LinkedList<Tile> waypoints){
		ArrayList<Tile> result =new ArrayList<>();
		ArrayList<Tile> pathC = new ArrayList<>();
		ArrayList<Tile> vertices = g.getVertices();
		waypoints.addFirst(startNode);
		for(Tile t : vertices)
			if(t.isDestination)
				waypoints.addLast(t);
		for(int i = 0; i < waypoints.size()-1; i++){
			ArrayList<Tile> current = findPath(waypoints.get(i), waypoints.get(i+1));
			for(Tile t : current){
				if(!pathC.contains(t))
					pathC.add(t);
			}
		}

		double damageCostPathC = damageGraph.computePathCost(pathC);
		double distanceCostPathC = costGraph.computePathCost(pathC);
		if(damageCostPathC < health){
//			System.out.println("damageCostPathC is " + damageCostPathC);
			result = pathC;
			return result;

		}
		g = damageGraph;
		ArrayList<Tile> pathD = new ArrayList<>();
		for(int i = 0; i < waypoints.size()-1; i++){
			ArrayList<Tile> current = findPath(waypoints.get(i), waypoints.get(i+1));
			for(Tile t : current){
				if(!pathD.contains(t))
					pathD.add(t);
			}
		}
		double damageCostPathD = damageGraph.computePathCost(pathD);
		double distanceCostPathD = costGraph.computePathCost(pathD);
		if(damageCostPathD > health)
		{
//			System.out.println("damageCostPathD is " + damageCostPathD);
			result = null;
			System.out.println("No possible path exists");
			return result;

		}
		System.out.println(distanceCostPathC + " " + distanceCostPathD + " " + damageCostPathC + " " +damageCostPathD);
		double lambda = (distanceCostPathC -distanceCostPathD) / (damageCostPathC - damageCostPathD);

		System.out.println("lambda is " + lambda);


		for(Graph.Edge e : aggregatedGraph.getAllEdges()){
			e.weight = costGraph.getEdge(e.origin, e.destination).weight + lambda * damageGraph.getEdge(e.origin, e.destination).weight;

		}

		g = aggregatedGraph;
		ArrayList<Tile> pathR = new ArrayList<>();
		for(int i = 0; i < waypoints.size()-1; i++){
			ArrayList<Tile> current = findPath(waypoints.get(i), waypoints.get(i+1));
			for(Tile t : current){
				if(!pathR.contains(t))
					pathR.add(t);
			}
		}


		while(result.isEmpty()){
			System.out.println("--------------------------------------------");
			damageCostPathC = damageGraph.computePathCost(pathC);
			distanceCostPathC = costGraph.computePathCost(pathC);
			damageCostPathD = damageGraph.computePathCost(pathD);
			distanceCostPathD = costGraph.computePathCost(pathD);
			lambda = (distanceCostPathC -distanceCostPathD) / (damageCostPathC - damageCostPathD);
			for(Graph.Edge e : aggregatedGraph.getAllEdges()){
				e.weight = costGraph.getEdge(e.origin, e.destination).weight + lambda * damageGraph.getEdge(e.origin, e.destination).weight;

			}
			pathR = new ArrayList<>();
			for(int i = 0; i < waypoints.size()-1; i++){
				ArrayList<Tile> current = findPath(waypoints.get(i), waypoints.get(i+1));
				for(Tile t : current){
					if(!pathR.contains(t))
						pathR.add(t);
				}
			}
			double aggregatedCostPathR = aggregatedGraph.computePathCost(pathR);
			double aggregatedCostPathC = aggregatedGraph.computePathCost(pathC);
			double damageCostPathR = damageGraph.computePathCost(pathR);
			if(aggregatedCostPathR == aggregatedCostPathC){
				result = pathD;
			}
			else if(damageCostPathR <= health)
				pathD = pathR;
			else
				pathC = pathR;
		}

//		for(int i = 0; i < result.size()-1; i++){
//			for(Graph.Edge edge : aggregatedGraph.getAllEdges()){
//				if(result.get(i) == edge.origin && result.get(i+1) == edge.destination){
//					System.out.println(edge.origin + " " + edge.destination + " " + edge.weight);
//				}
//			}
//
//		}

		return result;
	}

}
