package finalproject;

import finalproject.system.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;



public abstract class PathFindingService{
	public static Tile source;
	public static Graph g;

	public PathFindingService(Tile start) {
    	source = start;
        generateGraph();
    }


    public abstract void generateGraph();

    public static ArrayList<Tile> DijkstraTraverse(Tile s){
        LinkedList<Tile> queue1 = new LinkedList<>();
        LinkedList<Tile> queue2 = new LinkedList<>();
        if(s.isWalkable())
            queue1.add(s);
        while(!queue1.isEmpty()){
            Tile current  = queue1.poll();
            queue2.add(current);
            for(Tile t : current.neighbors){
                if(!queue1.contains(t) && !queue2.contains(t) && t.isWalkable()){
                    queue1.add(t);
                }
            }
            ArrayList<Tile> al = new ArrayList<>(queue2);
            for(Tile tile : al){
                tile.neighbors.removeIf(t -> !t.isWalkable());
            }
        }
        return new ArrayList<>(queue2);
    }

    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination
    public ArrayList<Tile> findPath(Tile startNode) {
        LinkedList<Tile> path = new LinkedList<>();
        ArrayList<Tile> vertices = g.getVertices();
        for (Tile t : vertices) {
            t.predecessor = null;
            if (t == startNode)
                t.costEstimate = 0;
            else
                t.costEstimate = Integer.MAX_VALUE;

        }
        TilePriorityQ tpq = new TilePriorityQ(vertices);
        Tile t;
        Tile finalTile = null;
        while (!tpq.isEmpty()) {
            t = tpq.removeMin();

//            System.out.println("current tile is " + t + " and cost is " + t.costEstimate + " and pred is " + t.predecessor);
            for (Tile tile : t.neighbors) {
//                System.out.println("current neighbor is " + tile);
                if (tile.costEstimate == Integer.MAX_VALUE) {
                    for (Graph.Edge edge : g.getAllEdges()) {
                        if (edge.getStart() == t && edge.getEnd() == tile) {
                            tile.costEstimate = t.costEstimate + edge.weight;
                            tpq.updateKeys(tile, t, tile.costEstimate);
//                            System.out.println("update tile " + tile + " with predecessor " + t + " and cost is " + tile.costEstimate);
                        }
                    }
                } else {
                    if(tile != t.predecessor){
                        double oldCost = tile.costEstimate;
                        double newCost = Integer.MAX_VALUE;
                        for (Graph.Edge edge : g.getAllEdges()) {
                            if (edge.getStart() == t && edge.getEnd() == tile) {
                                newCost = t.costEstimate + edge.weight;
                            }
                        }
//                        System.out.println("new cost is " + newCost + " and old cost is " + oldCost);
                        if (oldCost > newCost) {
                            tile.costEstimate = newCost;
                            tpq.updateKeys(tile, t, newCost);
//                            System.out.println("new cost is less and update tile " + tile + " with predecessor " + t + " and cost is " + tile.costEstimate);

                        }
                        else{
//                            System.out.println("Comparator not pass");
                        }
                    }
                }

            }
            if(t.isDestination){
                finalTile = t;
            }
//            System.out.println("--------------------------");

        }
        while(finalTile != null){
            path.addFirst(finalTile);
            finalTile = finalTile.predecessor;
        }
        System.out.println(path.size());

        for(Tile tt : path)
            System.out.println(tt);
        return new ArrayList<>(path);
    }
    
    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public static ArrayList<Tile> findPath(Tile start, Tile end) {
        LinkedList<Tile> path = new LinkedList<>();
        ArrayList<Tile> vertices = g.getVertices();
        for (Tile t : vertices) {
            t.predecessor = null;
            if (t == start)
                t.costEstimate = 0;
            else
                t.costEstimate = Integer.MAX_VALUE;

        }
        TilePriorityQ tpq = new TilePriorityQ(vertices);
        Tile t;
        Tile finalTile = null;
        while (!tpq.isEmpty()) {
            t = tpq.removeMin();

//            System.out.println("current tile is " + t + " and cost is " + t.costEstimate + " and pred is " + t.predecessor);
            for (Tile tile : t.neighbors) {
//                System.out.println("current neighbor is " + tile);
                if (tile.costEstimate == Integer.MAX_VALUE) {
                    for (Graph.Edge edge : g.getAllEdges()) {
                        if (edge.getStart() == t && edge.getEnd() == tile) {
                            tile.costEstimate = t.costEstimate + edge.weight;
                            tpq.updateKeys(tile, t, tile.costEstimate);
//                            System.out.println("update tile " + tile + " with predecessor " + t + " and cost is " + tile.costEstimate);
                        }
                    }
                } else {
                    if(tile != t.predecessor){
                        double oldCost = tile.costEstimate;
                        double newCost = Integer.MAX_VALUE;
                        for (Graph.Edge edge : g.getAllEdges()) {
                            if (edge.getStart() == t && edge.getEnd() == tile) {
                                newCost = t.costEstimate + edge.weight;
                            }
                        }
//                        System.out.println("new cost is " + newCost + " and old cost is " + oldCost);
                        if (oldCost > newCost) {
                            tile.costEstimate = newCost;
                            tpq.updateKeys(tile, t, newCost);
//                            System.out.println("new cost is less and update tile " + tile + " with predecessor " + t + " and cost is " + tile.costEstimate);

                        }

                    }
                }

            }
            if(t == end){
                finalTile = t;
            }
//            System.out.println("--------------------------");

        }
        while(finalTile != null){
            path.addFirst(finalTile);
            finalTile = finalTile.predecessor;
        }
//        System.out.println(path.size());

//        for(Tile tt : path)
//            System.out.println(tt);
        return new ArrayList<>(path);
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public static ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){
        ArrayList<Tile> path = new ArrayList<>();
        ArrayList<Tile> vertices = g.getVertices();
        waypoints.addFirst(start);
        for(Tile t : vertices)
            if(t.isDestination)
                waypoints.addLast(t);
        for(int i = 0; i < waypoints.size()-1; i++){
            ArrayList<Tile> current = findPath(waypoints.get(i), waypoints.get(i+1));
            for(Tile t : current){
                if(!path.contains(t))
                    path.add(t);
            }
        }
        return path;

    }

    public double getManhattanDistance(Tile t1, Tile t2){
        return Math.abs(t1.xCoord - t2.xCoord) + Math.abs(t1.yCoord + t2.yCoord);
    }

        
}

