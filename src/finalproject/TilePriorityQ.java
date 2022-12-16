package finalproject;

import java.util.ArrayList;
import java.util.LinkedList;


import finalproject.system.Tile;

public class TilePriorityQ {
	// TODO level 3: Add fields that can help you implement this data type
	public static LinkedList<Tile> pq;


	// TODO level 3: implement the constructor for the priority queue
	public TilePriorityQ(ArrayList<Tile> vertices) {
		LinkedList<Tile> pq = new LinkedList<>();
		for (Tile t : vertices) {
//			System.out.println("adding " + t.costEstimate);
			if (pq.isEmpty())
				pq.add(t);
			else if (t.costEstimate < pq.getFirst().costEstimate) {
				pq.addFirst(t);
			} else if (t.costEstimate > pq.getLast().costEstimate) {
				pq.addLast(t);
			} else {
				for (int i = 0; i < pq.size(); i++) {
					if (t.costEstimate < pq.get(i).costEstimate) {
						pq.add(i, t);
						break;
					}

				}
			}
		}
//		for(Tile t : pq){
//			System.out.println(t.costEstimate);
//		}
		TilePriorityQ.pq = pq;
	}

	// TODO level 3: implement remove min as seen in class
	public Tile removeMin() {
		return pq.removeFirst();
	}

	// TODO level 3: implement updateKeys as described in the pdf
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {
		t.costEstimate = newEstimate;
		t.predecessor = newPred;
		if(pq.isEmpty() || !pq.contains(t))
			pq.add(t);
		else{
			pq.removeIf(tile -> tile == t);

//		System.out.println("adding " + t.costEstimate);
			if (pq.isEmpty())
				pq.add(t);
			else if (t.costEstimate < pq.getFirst().costEstimate) {
				pq.addFirst(t);
			} else if (t.costEstimate > pq.getLast().costEstimate) {
				pq.addLast(t);
			} else {
				for (int i = 0; i < pq.size(); i++) {
					if (t.costEstimate < pq.get(i).costEstimate) {
						pq.add(i, t);
						break;
					}

				}
			}
		}


	}

	public boolean isEmpty(){
		return pq.isEmpty();
	}
	
}
