package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class PathFindingService {
	Tile source;
	Graph g;
	
	public PathFindingService(Tile start) {
    	this.source = start;
        generateGraph();
    }

	public abstract void generateGraph();
    
    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination
    public ArrayList<Tile> findPath(Tile startNode) {
        ArrayList<Tile> vertices = g.getVertices();
        for (Tile vertex: vertices)
        {
            vertex.costEstimate = Double.MAX_VALUE;
        }
        startNode.costEstimate = 0.0;
        TilePriorityQ q = new TilePriorityQ(vertices);

        Tile dest = null;
        while (!q.empty())
        {
            Tile u = q.removeMin();
            if (u.isDestination)
            {
                dest = u;
            }
            for (Tile v : g.getNeighbors(u))
            {
                Graph.Edge e = g.getEdge(u, v);
                if (e == null)
                {
                    continue;
                }
                double alt = u.costEstimate + e.weight;
                if (alt < v.costEstimate)
                {
                    q.updateKeys(v, u, alt);
                }
            }
        }
        ArrayList<Tile> ret = new ArrayList<>();
        while (dest != null)
        {
            ret.add(dest);
            dest = dest.predecessor;
        }
        ret = reverse(ret);
    	return ret;
    }

    public ArrayList<Tile> reverse(ArrayList<Tile> src)
    {
        ArrayList<Tile> ret = new ArrayList<>();
        for (int i = 1; i <= src.size(); i++)
        {
            ret.add(src.get(src.size() - i));
        }

        return ret;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public ArrayList<Tile> findPath(Tile start, Tile end) {
        ArrayList<Tile> vertices = g.getVertices();
        for (Tile vertex: vertices)
        {
            vertex.costEstimate = Double.MAX_VALUE;
        }
        start.costEstimate = 0.0;
        TilePriorityQ q = new TilePriorityQ(vertices);

        while (!q.empty())
        {
            Tile u = q.removeMin();
            boolean found = false;
            for (Tile v : g.getNeighbors(u))
            {
                Graph.Edge e = g.getEdge(u, v);
                if (e == null)
                {
                    continue;
                }
                double alt = u.costEstimate + e.weight;
                if (alt < v.costEstimate)
                {
                    q.updateKeys(v, u, alt);
                }

                if (u.equals(end))
                {
                    found = true;
                    break;
                }
            }
            if (found)
            {
                break;
            }
        }
        ArrayList<Tile> ret = new ArrayList<>();
        while (!end.equals(start))
        {
            ret.add(end);
            end = end.predecessor;
        }
        ret.add(start);
        ret = reverse(ret);
        return ret;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){
        ArrayList<Tile> ret = new ArrayList<>();
        Tile currStart = start;
        Tile currDest = null;
        while (!waypoints.isEmpty())
        {
            if (ret.size() > 0)
            {
                ret.remove(ret.size()-1);
            }
            currDest = waypoints.removeFirst();
            ret.addAll(findPath(currStart, currDest));
            currStart = currDest;
        }

        if (ret.size() > 0)
        {
            ret.remove(ret.size()-1);
        }
        ret.addAll(findPath(currStart, g.getDestination()));
    	return ret;
    }

    public double getManhattanDistance(Tile a, Tile b)
    {
        return Math.abs(a.xCoord - b.xCoord) + Math.abs(a.yCoord - b.yCoord);
    }
}

