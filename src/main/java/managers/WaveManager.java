package managers;

import entities.Arena;
import entities.enemies.EnemyFactory;
import entities.enemies.EnemyType;
import entities.waves.Portal;
import entities.waves.Wave;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class WaveManager {

    private ArrayList<Wave> waves;
    private Wave currentWave;

    public WaveManager()
    {
        waves = new ArrayList<Wave>();
    }

    public void init(GameContainer gc) throws SlickException
    {
        constructWave1(gc);
    }

    public void update(GameContainer gc, float delta)
    {
        currentWave.update(gc, delta);
//        if(currentWave.isDone())
//            currentWave.cleanUp();
    }

    public void start()
    {
        currentWave = waves.get(0);
        currentWave.start();
    }

    private void constructWave1(GameContainer gc) throws SlickException
    {
        Portal portal1 = new Portal(Arena.WIDTH/2, Arena.HEIGHT/2);
        portal1.init(gc);
        portal1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        portal1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        portal1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        portal1.addEnemy(gc, EnemyType.PURPLE_PIE);
        portal1.addEnemy(gc, EnemyType.YELLOW_YAK);
        portal1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        portal1.addEnemy(gc, EnemyType.YELLOW_YAK);
        portal1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        portal1.addEnemy(gc, EnemyType.PURPLE_PIE);
        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(portal1);

        waves.add(new Wave(1, portals));
    }
}
