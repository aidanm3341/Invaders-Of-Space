package entities.waves;

import managers.EntityManager;
import org.newdawn.slick.GameContainer;

import java.util.ArrayList;

public class Wave {

    private int waveNumber;
    private ArrayList<Portal> portals;
    private boolean isDone;

    public Wave(int waveNumber, ArrayList<Portal> portals)
    {
        this.waveNumber = waveNumber;
        this.portals = portals;

    }

    public void start(){
        for(Portal p : portals)
            EntityManager.addEntity(p);
    }

    public void update(GameContainer gc, float delta){
        for(Portal p : portals)
            if(!p.isDone())
                return;

        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public void cleanUp(){
        for(Portal p : portals)
            EntityManager.removeEntity(p);
    }
}
