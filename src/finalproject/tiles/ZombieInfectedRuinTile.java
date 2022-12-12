package finalproject.tiles;

import finalproject.system.Tile;
import finalproject.system.TileType;

public class ZombieInfectedRuinTile extends Tile {
    //TODO level 0: finish constructor
    public ZombieInfectedRuinTile() {
        this.type = TileType.ZombieInfectedRuin;
        this.distanceCost = 1;
        this.timeCost = 3;
        this.damageCost = 5;
    }

    public ZombieInfectedRuinTile(double dist, double time, double dmg) {
        super(dist, time, dmg);

    }
}
