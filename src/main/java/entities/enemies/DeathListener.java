package entities.enemies;

import org.newdawn.slick.SlickException;

public interface DeathListener {
    void notifyOfDeath(Enemy enemy) throws SlickException;
}
