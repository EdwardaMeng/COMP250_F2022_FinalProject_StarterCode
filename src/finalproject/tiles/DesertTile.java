package finalproject.tiles;

import finalproject.system.Logger;
import finalproject.system.Tile;
import finalproject.system.TileType;

public class DesertTile extends Tile {
    //TODO level 0: finish constructor
    public DesertTile() {
        this.type = TileType.Desert;
        this.damageCost = 3;
        this.distanceCost = 2;
        this.timeCost = 6;
    }

    public DesertTile(double dist, double time, double dmg) {
        super(dist, time, dmg);
    }
}
