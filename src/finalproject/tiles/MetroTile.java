package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class MetroTile extends Tile {
	public double metroTimeCost = 100;
	public double metroDistanceCost = 100;
	public double metroCommuteFactor = 0.2;
	
    //TODO level 0: finish constructor
    public MetroTile() {
        this.type = TileType.Metro;
        this.distanceCost = 1;
        this.timeCost = 1;
        this.damageCost = 2;
    }



    public MetroTile(double metroTimeCost, double metroDistanceCost, double metroCommuteFactor) {
        this.metroTimeCost = metroTimeCost;
        this.metroDistanceCost = metroDistanceCost;
        this.metroCommuteFactor = metroCommuteFactor;
    }

    public MetroTile(double dist, double time, double dmg, double metroTimeCost, double metroDistanceCost, double metroCommuteFactor) {
        super(dist, time, dmg);
        this.metroTimeCost = metroTimeCost;
        this.metroDistanceCost = metroDistanceCost;
        this.metroCommuteFactor = metroCommuteFactor;
    }

    public double getMetroTimeCost() {
        return metroTimeCost;
    }

    public void setMetroTimeCost(double metroTimeCost) {
        this.metroTimeCost = metroTimeCost;
    }

    public double getMetroDistanceCost() {
        return metroDistanceCost;
    }

    public void setMetroDistanceCost(double metroDistanceCost) {
        this.metroDistanceCost = metroDistanceCost;
    }

    public double getMetroCommuteFactor() {
        return metroCommuteFactor;
    }

    public void setMetroCommuteFactor(double metroCommuteFactor) {
        this.metroCommuteFactor = metroCommuteFactor;
    }

    // TODO level 7: updates the distance and time cost differently between metro tiles
    public void fixMetro(Tile node) {
        double M = Math.abs(this.xCoord - node.xCoord) + Math.abs(this.yCoord + node.yCoord);
        this.metroTimeCost = M/metroCommuteFactor;
        this.metroDistanceCost = M/metroCommuteFactor;
    }
}
