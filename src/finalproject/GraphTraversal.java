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
		ArrayList<Tile> ret = new ArrayList<>();
		HashSet<Tile> visited = new HashSet<>();
		LinkedList<Tile> queue = new LinkedList<>();
		queue.addLast(s);
		while (!queue.isEmpty())
		{
			Tile curr = queue.removeFirst();
			addToLinkedList(ret, visited, queue, curr);
		}
		return ret;
	}

	private static void addToLinkedList(ArrayList<Tile> ret, HashSet<Tile> visited, LinkedList<Tile> queue, Tile curr) {
		if (!visited.contains(curr))
		{
			ret.add(curr);
			visited.add(curr);

			for (Tile neighbor : curr.neighbors)
			{
				if (!visited.contains(neighbor) && neighbor.isWalkable())
				{
					queue.addLast(neighbor);
				}
			}
		}
	}


	//TODO level 1: implement DFS traversal starting from s
	public static ArrayList<Tile> DFS(Tile s) {
		ArrayList<Tile> ret = new ArrayList<>();
		HashSet<Tile> visited = new HashSet<>();
		LinkedList<Tile> stack = new LinkedList<>();
		stack.addLast(s);
		while (!stack.isEmpty())
		{
			Tile curr = stack.removeLast();
			addToLinkedList(ret, visited, stack, curr);
		}
		return ret;
	}

}  