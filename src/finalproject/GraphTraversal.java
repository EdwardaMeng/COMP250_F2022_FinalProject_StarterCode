package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class GraphTraversal
{

	//TODO level 1: implement BFS traversal starting from s
	public static ArrayList<Tile> BFS(Tile s)
	{
		LinkedList<Tile> queue1 = new LinkedList<>();
		ArrayList<Tile> queue2 = new ArrayList<>();
		queue1.add(s);
		while(!queue1.isEmpty()){
			Tile current = queue1.get(0);
			queue1.remove(0);
			queue2.add(current);
			for (Tile t: current.neighbors) {
				if (t.isWalkable()){
					if (!queue1.contains(t) && !queue2.contains(t))
						queue1.add(t);
				}
			}
		}
		return queue2;
	}

	//TODO level 1: implement DFS traversal starting from s
	public static ArrayList<Tile> DFS(Tile s) {
		LinkedList<Tile> stack = new LinkedList<>();
		ArrayList<Tile> queue = new ArrayList<>();

		stack.add(s);
		while (!stack.isEmpty()){
			Tile current = stack.get(stack.size() - 1);
				stack.remove(stack.size() - 1);
				queue.add(current);
				for (Tile t: current.neighbors) {
					if (t.isWalkable()){
						if (!stack.contains(t) && !queue.contains(t))
							stack.add(t);
					}
				}
		}
		return queue;
	}
}  