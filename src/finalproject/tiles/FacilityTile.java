package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class FacilityTile extends Tile {
    //TODO level 0: finish constructor
    public FacilityTile() {
        this.type = TileType.Facility;
        distanceCost = 1;
        timeCost = 2;
        damageCost = 0;
    }

    public FacilityTile(double dist, double time, double dmg) {
        super(dist, time, dmg);
    }
}
