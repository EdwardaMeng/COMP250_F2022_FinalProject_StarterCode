package finalproject;

import finalproject.system.Tile;

import java.lang.reflect.Array;
import java.util.*;

public class GraphTraversal
{


	//TODO level 1: implement BFS traversal starting from s
	public static ArrayList<Tile> BFS(Tile s)
	{
		Queue<Tile> queue = BFS_helper(s);
		return new ArrayList<>(queue);
	}

	public static Queue<Tile> BFS_helper(Tile s){
		Queue<Tile> queue1 = new LinkedList<>();
		Queue<Tile> queue2 = new LinkedList<>();
		if(s.isWalkable())
			queue1.add(s);
		while(!queue1.isEmpty()){
			Tile current  = queue1.poll();
			queue2.add(current);
			for(Tile t : current.neighbors){
				if(!queue1.contains(t) && !queue2.contains(t))
					queue1.add(t);
			}
		}
		return queue2;
	}


	//TODO level 1: implement DFS traversal starting from s
	public static ArrayList<Tile> DFS(Tile s) {
		Queue<Tile> queue = DFS_helper(s);
		return new ArrayList<>(queue);
	}

	public static Queue<Tile> DFS_helper(Tile s){
		Stack<Tile> stack = new Stack<>();
		Queue<Tile> queue = new LinkedList<>();
		stack.push(s);
		while(!stack.isEmpty()){
			Tile current = stack.pop();
			queue.add(current);
			for(Tile t : current.neighbors){
				if(!stack.contains(t) && !queue.contains(t))
					stack.push(t);
			}
		}
		return queue;
	}

}  