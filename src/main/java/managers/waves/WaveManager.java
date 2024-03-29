package managers.waves;

import entities.Arena;
import entities.enemies.EnemyType;
import entities.waves.Portal;
import entities.waves.PortalPosition;
import entities.waves.Wave;
import entities.waves.WaveText;
import main.Main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import shop.MyFont;

import java.util.ArrayList;
import java.util.List;

public class WaveManager {

    private static final int NUMBER_OF_WAVES = 6;

    private ArrayList<Wave> waves;
    private Wave currentWave;
    private int currentWaveInt;
    private WaveText waveSplash;
    private long waveTimerStart;
    private Vector2f target;

    private String shopCountDownTimerString;
    private UnicodeFont waveTimerFont;


    public void init(GameContainer gc, Vector2f target) {
        waves = new ArrayList<>();
        this.target = target;
        WaveBuilder waveBuilder = new WaveBuilder(gc, target);

        constructWave0(waveBuilder);
        constructWave1(waveBuilder);
        constructWave2(waveBuilder);
        constructWave3(waveBuilder);
        constructWave4(waveBuilder);
        constructWave5(waveBuilder);
        constructWave6(waveBuilder);

        waveTimerFont = new MyFont(15).getUniFont();
        shopCountDownTimerString = "";
    }

    public void update(GameContainer gc, StateBasedGame sbg, float delta) throws SlickException {
        currentWave.update(gc, delta);
        if(currentWave.isDone()) {
            if(waveTimerStart == 0)
                waveTimerStart = System.currentTimeMillis();
            if(System.currentTimeMillis() - waveTimerStart >= 3000 && currentWaveInt < NUMBER_OF_WAVES) {
                sbg.enterState(Main.SHOP);
                startNextWave(gc);
            }
            shopCountDownTimerString = "The shop will open in " + (4-((((System.currentTimeMillis() - waveTimerStart)/1000) % 3) + 1)) + " seconds";
        }
        else{
            shopCountDownTimerString = "";
        }
        waveSplash.update(gc, delta);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        waveSplash.render(gc, g);
        g.setFont(waveTimerFont);
        g.drawString(shopCountDownTimerString,
                gc.getWidth()/2 - waveTimerFont.getWidth(shopCountDownTimerString)/2,
                gc.getHeight() - waveTimerFont.getHeight(shopCountDownTimerString) - 20);
    }

    public void start(GameContainer gc, int wave) {
        currentWaveInt = wave;
        currentWave = waves.get(wave);
        currentWave.start();
        waveSplash = new WaveText(currentWaveInt + 1);
        waveSplash.init(gc);
        waveTimerStart = 0;
    }

    private void startNextWave(GameContainer gc) {
        currentWave.cleanUp();
        start(gc, currentWaveInt + 1);
    }

    private void constructWave0(WaveBuilder waveBuilder) {
        waveBuilder.addPortal(PortalPosition.MIDDLE, List.of(EnemyType.GREEN_GOBLIN, EnemyType.GREEN_GOBLIN));
        waves.add(waveBuilder.getWaveAndReset());
    }

    private void constructWave1(WaveBuilder waveBuilder) {
        List<EnemyType> enemies = List.of(EnemyType.GREEN_GOBLIN, EnemyType.PURPLE_PIE, EnemyType.YELLOW_YAK);
        waveBuilder.addPortal(PortalPosition.TOP_LEFT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_RIGHT, enemies);
        waves.add(waveBuilder.getWaveAndReset());
    }

    private void constructWave2(WaveBuilder waveBuilder) {
        List<EnemyType> enemies = List.of(EnemyType.GREEN_GOBLIN, EnemyType.GREEN_GOBLIN, EnemyType.YELLOW_YAK, EnemyType.YELLOW_YAK);
        waveBuilder.addPortal(PortalPosition.TOP_RIGHT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_LEFT, enemies);
        waves.add(waveBuilder.getWaveAndReset());
    }

    private void constructWave3(WaveBuilder waveBuilder) {
        List<EnemyType> enemies = List.of(EnemyType.GREEN_GOBLIN, EnemyType.GREEN_GOBLIN, EnemyType.YELLOW_YAK, EnemyType.YELLOW_YAK);
        waveBuilder.addPortal(PortalPosition.TOP_RIGHT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_RIGHT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_LEFT, enemies);
        waveBuilder.addPortal(PortalPosition.TOP_LEFT, enemies);
        waves.add(waveBuilder.getWaveAndReset());
    }

    private void constructWave4(WaveBuilder waveBuilder) {
        List<EnemyType> enemies = List.of(EnemyType.PURPLE_PIE, EnemyType.GREEN_GOBLIN, EnemyType.GREEN_GOBLIN, EnemyType.YELLOW_YAK, EnemyType.YELLOW_YAK);
        waveBuilder.addPortal(PortalPosition.TOP_RIGHT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_LEFT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_RIGHT, enemies);
        waveBuilder.addPortal(PortalPosition.TOP_LEFT, enemies);
        waveBuilder.addPortal(PortalPosition.MIDDLE, enemies);
        waves.add(waveBuilder.getWaveAndReset());
    }

    private void constructWave5(WaveBuilder waveBuilder) {
        List<EnemyType> enemies = List.of(EnemyType.YELLOW_YAK, EnemyType.YELLOW_YAK, EnemyType.YELLOW_YAK);
        waveBuilder.addPortal(PortalPosition.TOP_RIGHT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_LEFT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_RIGHT, enemies);
        waveBuilder.addPortal(PortalPosition.TOP_LEFT, enemies);
        waves.add(waveBuilder.getWaveAndReset());
    }

    private void constructWave6(WaveBuilder waveBuilder) {
        List<EnemyType> enemies = List.of(EnemyType.PURPLE_PIE, EnemyType.PURPLE_PIE, EnemyType.PURPLE_PIE);
        waveBuilder.addPortal(PortalPosition.TOP_RIGHT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_LEFT, enemies);
        waveBuilder.addPortal(PortalPosition.BOTTOM_RIGHT, enemies);
        waveBuilder.addPortal(PortalPosition.TOP_LEFT, enemies);
        waves.add(waveBuilder.getWaveAndReset());
    }

    public void reset(GameContainer gc){
        init(gc, target);
    }
}
