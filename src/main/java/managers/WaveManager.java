package managers;

import entities.Arena;
import entities.enemies.EnemyType;
import entities.waves.Portal;
import entities.waves.Wave;
import entities.waves.WaveText;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;

public class WaveManager {

    private static final int NUMBER_OF_WAVES = 6;

    private ArrayList<Wave> waves;
    private Wave currentWave;
    private int currentWaveInt;
    private WaveText waveSplash;
    private long waveTimerStart;
    private Point target;


    public void init(GameContainer gc, Point target) throws SlickException {
        waves = new ArrayList<Wave>();
        this.target = target;

        constructWave0(gc);
        constructWave1(gc);
        constructWave2(gc);
        constructWave3(gc);
        constructWave4(gc);
        constructWave5(gc);
        constructWave6(gc);
    }

    public void update(GameContainer gc, float delta) throws SlickException {
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

    private void constructWave0(GameContainer gc) throws SlickException {
        Portal p1 = new Portal(Arena.WIDTH/2, Arena.HEIGHT/2);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);

        waves.add(new Wave(portals));
    }

    private void constructWave1(GameContainer gc) throws SlickException {
        Portal p1 = new Portal(100,100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p1.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p2 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p2.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);

        waves.add(new Wave(portals));
    }

    private void constructWave2(GameContainer gc) throws SlickException {
        Portal p1 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, 100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p2 = new Portal(100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);

        waves.add(new Wave(portals));
    }

    private void constructWave3(GameContainer gc) throws SlickException {
        Portal p1 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, 100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p2 = new Portal(100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p3 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p3.init(gc);
        p3.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p3.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p4 = new Portal(100, 100);
        p4.init(gc);
        p4.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p4.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);
        portals.add(p3);
        portals.add(p4);

        waves.add(new Wave(portals));
    }

    private void constructWave4(GameContainer gc) throws SlickException {
        Portal p1 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, 100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p1.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p2 = new Portal(100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p2.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p3 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p3.init(gc);
        p3.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p3.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p3.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p4 = new Portal(100, 100);
        p4.init(gc);
        p4.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p4.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p4.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p5 = new Portal(Arena.WIDTH/2, Arena.HEIGHT/2);
        p5.init(gc);
        p5.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p5.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p5.addEnemy(gc, EnemyType.GREEN_GOBLIN, target);
        p5.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p5.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);
        portals.add(p3);
        portals.add(p4);
        portals.add(p5);

        waves.add(new Wave(portals));
    }

    private void constructWave5(GameContainer gc) throws SlickException {
        Portal p1 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, 100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p1.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p2 = new Portal(100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p2.addEnemy(gc, EnemyType.YELLOW_YAK, target);

        Portal p3 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p3.init(gc);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p3.addEnemy(gc, EnemyType.YELLOW_YAK, target);


        Portal p4 = new Portal(100, 100);
        p4.init(gc);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK, target);
        p4.addEnemy(gc, EnemyType.YELLOW_YAK, target);



        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);
        portals.add(p3);
        portals.add(p4);

        waves.add(new Wave(portals));
    }

    private void constructWave6(GameContainer gc) throws SlickException {
        Portal p1 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, 100);
        p1.init(gc);
        p1.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p1.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p1.addEnemy(gc, EnemyType.PURPLE_PIE, target);

        Portal p2 = new Portal(100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p2.init(gc);
        p2.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p2.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p2.addEnemy(gc, EnemyType.PURPLE_PIE, target);

        Portal p3 = new Portal(Arena.WIDTH - Portal.PORTAL_WIDTH - 100, Arena.HEIGHT - Portal.PORTAL_HEIGHT - 100);
        p3.init(gc);
        p3.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p3.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p3.addEnemy(gc, EnemyType.PURPLE_PIE, target);

        Portal p4 = new Portal(100, 100);
        p4.init(gc);
        p4.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p4.addEnemy(gc, EnemyType.PURPLE_PIE, target);
        p4.addEnemy(gc, EnemyType.PURPLE_PIE, target);

        ArrayList<Portal> portals = new ArrayList<Portal>();
        portals.add(p1);
        portals.add(p2);
        portals.add(p3);
        portals.add(p4);

        waves.add(new Wave(portals));
    }

    public void reset(GameContainer gc) throws SlickException{
        init(gc, target);
    }
}
