package finalproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


import finalproject.system.Logger;
import finalproject.system.Tile;

public class TilePriorityQ {
	// TODO level 3: Add fields that can help you implement this data type
	public static PriorityQueue<Tile> pq;

	// TODO level 3: implement the constructor for the priority queue
	public TilePriorityQ (ArrayList<Tile> vertices) {
		pq.addAll(vertices);
	}
	
	// TODO level 3: implement remove min as seen in class
	public Tile removeMin() {
		return pq.remove();
	}
	
	// TODO level 3: implement updateKeys as described in the pdf
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {
		if(pq.contains(t)){
			t.predecessor = newPred;
			t.costEstimate = newEstimate;
		}
		else
			Logger.getInstance().logErrorMessage("Key does not exist!");
	}
	
}
