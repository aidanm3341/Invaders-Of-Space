package managers;

import entities.Arena;
import entities.enemies.EnemyType;
import entities.waves.Portal;
import entities.waves.Wave;
import entities.waves.WaveText;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

public class WaveManager {

    private static final int NUMBER_OF_WAVES = 5;

    private ArrayList<Wave> waves;
    private Wave currentWave;
    private int currentWaveInt;
    private WaveText waveSplash;
    private long waveTimerStart;


    public void init(GameContainer gc) throws SlickException
    {
        waves = new ArrayList<Wave>();
        constructWave0(gc);
        constructWave1(gc);
        constructWave2(gc);
        constructWave3(gc);
        constructWave4(gc);
    }

    public void update(GameContainer gc, float delta) throws SlickException
    {
        currentWave.update(gc, delta);
        if(currentWave.isDone()) {
            if(waveTimerStart == 0)
                waveTimerStart = System.currentTimeMillis();
            if(System.currentTimeMillis() - waveTimerStart >= 5000 && currentWaveInt+1 < NUMBER_OF_WAVES) {
                startNextWave(gc);
            }
        }
        waveSplash.update(gc, delta);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        waveSplash.render(gc, g);
    }

    public void start(GameContainer gc, int wave) throws SlickException
    {
        currentWaveInt = wave;
        currentWave = waves.get(wave);
        currentWave.start();
        waveSplash = new WaveText(currentWaveInt + 1);
        waveSplash.init(gc);
        waveTimerStart = 0;
    }

    private void startNextWave(GameContainer gc) throws SlickException{
        currentWave.cleanUp();
        start(gc, currentWaveInt + 1);
    }

    private void constructWave0(GameContainer gc) throws SlickException
    {
        Portal p1 = new Portal(Arena.WIDTH/2, Arena.HEIGHT/2);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);

        waves.add(new Wave(0, portals));
    }

    private void constructWave1(GameContainer gc) throws SlickException
    {
        Portal p1 = new Portal(100,100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p1.addEnemy(gc, EnemyType.PURPLE_PIE);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK);

        Portal p2 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p2.addEnemy(gc, EnemyType.PURPLE_PIE);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK);

        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);

        waves.add(new Wave(1, portals));
    }

    private void constructWave2(GameContainer gc) throws SlickException
    {
        Portal p1 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, 100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK);

        Portal p2 = new Portal(100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK);

        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);

        waves.add(new Wave(2, portals));
    }

    private void constructWave3(GameContainer gc) throws SlickException
    {
        Portal p1 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, 100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK);

        Portal p2 = new Portal(100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK);

        Portal p3 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p3.init(gc);
        p3.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p3.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK);

        Portal p4 = new Portal(100, 100);
        p4.init(gc);
        p4.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p4.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK);

        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);
        portals.add(p3);
        portals.add(p4);

        waves.add(new Wave(3, portals));
    }

    private void constructWave4(GameContainer gc) throws SlickException
    {
        Portal p1 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, 100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.PURPLE_PIE);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK);

        Portal p2 = new Portal(100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.PURPLE_PIE);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK);

        Portal p3 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p3.init(gc);
        p3.addEnemy(gc, EnemyType.PURPLE_PIE);
        p3.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p3.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK);

        Portal p4 = new Portal(100, 100);
        p4.init(gc);
        p4.addEnemy(gc, EnemyType.PURPLE_PIE);
        p4.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p4.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK);

        Portal p5 = new Portal(Arena.WIDTH/2, Arena.HEIGHT/2);
        p5.init(gc);
        p5.addEnemy(gc, EnemyType.PURPLE_PIE);
        p5.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p5.addEnemy(gc, EnemyType.GREEN_GOBLIN);
        p5.addEnemy(gc, EnemyType.YELLOW_YAK);
        p5.addEnemy(gc, EnemyType.YELLOW_YAK);

        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);
        portals.add(p3);
        portals.add(p4);
        portals.add(p5);

        waves.add(new Wave(4, portals));
    }

    public void reset(GameContainer gc) throws SlickException{
        init(gc);
    }
}
