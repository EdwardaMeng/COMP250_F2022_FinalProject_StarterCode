package finalproject;

import java.util.ArrayList;
import java.util.Arrays;


import finalproject.system.Tile;

public class TilePriorityQ {
	// TODO level 3: Add fields that can help you implement this data type
	private Tile[] Heap;
	private int index;
	private int size;

	// TODO level 3: implement the constructor for the priority queue
	public TilePriorityQ (ArrayList<Tile> vertices) {
		Heap = new Tile[vertices.size()];
		size = vertices.size();
		for (Tile vertex : vertices)
		{
			insert(vertex);
		}
	}

	private void insert(Tile t)
	{
		Heap[index] = t;
		int current = index;

		while (Heap[current].costEstimate < Heap[parent(current)].costEstimate)
		{
			swap(current, parent(current));
			current = parent(current);
		}
		index++;
	}

	private int parent(int pos) { return pos / 2; }

	private int leftChild(int pos) { return (2 * pos); }

	private int rightChild(int pos) { return (2 * pos) + 1; }

	private boolean isLeaf(int pos)
	{

		if (rightChild(pos) >= size || leftChild(pos) >= size) {
			return true;
		}
		return false;
	}

	private void swap(int fpos, int spos)
	{

		Tile tmp;
		tmp = Heap[fpos];

		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}
	
	// TODO level 3: implement remove min as seen in class
	public Tile removeMin() {
		Tile ret = Heap[0];
		Heap[0] = Heap[--size];
		minHeapify(0);
		return ret;
	}

	private void minHeapify(int i)
	{
		if (!isLeaf(i)) {
			if (Heap[i].costEstimate > Heap[leftChild(i)].costEstimate ||
					Heap[i].costEstimate > Heap[rightChild(i)].costEstimate)
			{
				if (Heap[leftChild(i)].costEstimate < Heap[rightChild(i)].costEstimate)
				{
					swap(i, leftChild(i));
					minHeapify(leftChild(i));
				}
				else
				{
					swap(i, rightChild(i));
					minHeapify(rightChild(i));
				}
			}
		}
	}
	
	// TODO level 3: implement updateKeys as described in the pdf
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {
		for (int i = 0; i < Heap.length; i++)
		{
			Tile node = Heap[i];
			if (node == t)
			{
				node.costEstimate = newEstimate;
				node.predecessor = newPred;
				while (Heap[i].costEstimate < Heap[parent(i)].costEstimate)
				{
					swap(i, parent(i));
					i = parent(i);
				}
				index++;
				break;
			}
		}
	}

	public boolean empty()
	{
		return size == 0;
	}
	
}
