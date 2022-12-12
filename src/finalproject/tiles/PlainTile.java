package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class PlainTile extends Tile {
    //TODO level 0: finish constructor
    public PlainTile() {
        this.type = TileType.Plain;
        this.distanceCost = 3;
        this.timeCost = 1;
        this.damageCost = 0;
    }

    public PlainTile(double dist, double time, double dmg) {
        super(dist, time, dmg);
    }
}
