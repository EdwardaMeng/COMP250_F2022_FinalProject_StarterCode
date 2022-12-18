package finalproject;


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

	
	public void generateGraph() {
		// TODO Auto-generated method stub
		ArrayList<Tile> vertices = GraphTraversal.BFS(source);
		costGraph = new Graph(vertices);
		for (Tile vertex: vertices)
		{
			for (Tile neighbor :costGraph.getNeighbors(vertex))
			{
				costGraph.addEdge(vertex, neighbor, neighbor.distanceCost);
			}
		}

		damageGraph = new Graph(vertices);
		for (Tile vertex: vertices)
		{
			for (Tile neighbor :damageGraph.getNeighbors(vertex))
			{
				damageGraph.addEdge(vertex, neighbor, neighbor.damageCost);
			}
		}

		aggregatedGraph = new Graph(vertices);
		for (Tile vertex: vertices)
		{
			for (Tile neighbor :aggregatedGraph.getNeighbors(vertex))
			{
				aggregatedGraph.addEdge(vertex, neighbor, neighbor.damageCost);
			}
		}

		g = costGraph;
	}

//	@Override
//	public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){
//		g = costGraph;
//		ArrayList<Tile> pc = findPath(start);
//		double cpd = 0;
//		double cpc = 0;
//		for (int i = 1; i < pc.size(); i++)
//		{
//			Tile t = pc.get(i);
//			cpd += t.damageCost;
//			cpc += t.distanceCost;
//		}
//		if (cpd < health)
//		{
//			return pc;
//		}
//
//		g = damageGraph;
//		double dpd = 0;
//		double dpc = 0;
//		ArrayList<Tile> pd = findPath(start);
//
//		for (int i = 1; i < pd.size(); i++)
//		{
//			Tile t = pd.get(i);
//			dpd += t.damageCost;
//			dpc += t.distanceCost;
//		}
//		if (dpd >= health)
//		{
//			return null;
//		}
//
//		while (true)
//		{
//			cpd = 0;
//			cpc = 0;
//			for (int i = 1; i < pc.size(); i++)
//			{
//				Tile t = pc.get(i);
//				cpd += t.damageCost;
//				cpc += t.distanceCost;
//			}
//
//			dpd = 0;
//			dpc = 0;
//
//			for (int i = 1; i < pd.size(); i++)
//			{
//				Tile t = pd.get(i);
//				dpd += t.damageCost;
//				dpc += t.distanceCost;
//			}
//
//			double lambda = (cpc - cpd) / (dpd - dpc);
//			ArrayList<Graph.Edge> edges = aggregatedGraph.getAllEdges();
//			for (Graph.Edge edge: edges)
//			{
//				edge.weight = edge.destination.distanceCost + lambda * edge.destination.damageCost;
//			}
//
//			g = aggregatedGraph;
//			ArrayList<Tile> pr = findPath(start);
//			if (Math.abs(getAggregatedCost(pr) - getAggregatedCost(pc)) <= 1e-6)
//			{
//				return pr;
//			}
//			else if (getDmgCost(pr) <= health)
//			{
//				pd = pr;
//			}
//			else
//			{
//				pc = pr;
//			}
//		}
//	}
//
//	private double getAggregatedCost(ArrayList<Tile> path)
//	{
//		double total = 0;
//		for (int i = 0; i < path.size() - 1; i++)
//		{
//			Graph.Edge edge = g.getEdge(path.get(i), path.get(i+1));
//			total += edge.weight;
//		}
//
//		return total;
//	}
//
//	private double getDmgCost(ArrayList<Tile> path)
//	{
//		double total = 0;
//		for (int i = 1; i < path.size(); i++)
//		{
//			total += path.get(i).damageCost;
//		}
//
//		return total;
//	}
}
