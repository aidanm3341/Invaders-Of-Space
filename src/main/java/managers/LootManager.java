package managers;

import entities.enemies.Enemy;
import entities.loot.Metal;
import org.newdawn.slick.SlickException;

public class LootManager {

    public LootManager(){

    }

    public Metal generateLoot(float x, float y) throws SlickException {
        return new Metal(x, y);
    }
}
