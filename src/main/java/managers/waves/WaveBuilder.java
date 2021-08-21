package managers.waves;

import entities.enemies.EnemyType;
import entities.waves.Portal;
import entities.waves.Wave;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;
import java.util.List;

public class WaveBuilder {

    private GameContainer gc;
    private List<Portal> portals;
    private final Point target;

    public WaveBuilder(GameContainer gc, Point target){
        this.gc = gc;
        this.target = target;
        portals = new ArrayList<>();
    }

    public void addPortal(float x, float y, List<EnemyType> enemies){
        Portal portal = new Portal(x, y);
        portal.init(gc);

        for(EnemyType e : enemies)
            portal.addEnemy(gc, e, target);

        portals.add(portal);
    }

    public void addPortal(Point point, List<EnemyType> enemies){
        addPortal(point.getX(), point.getY(), enemies);
    }

    public Wave getWaveAndReset(){
        Wave wave = new Wave(portals);
        portals = new ArrayList<>();
        return wave;
    }
}
