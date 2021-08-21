package entities.waves;

import managers.EntityManager;
import org.newdawn.slick.GameContainer;

import java.util.ArrayList;
import java.util.List;

public class Wave {

    private final List<Portal> portals;
    private boolean isDone;
    private final EntityManager entityManager = EntityManager.getInstance();

    public Wave(List<Portal> portals) {
        this.portals = portals;
    }

    public void start(){
        for(Portal p : portals)
            entityManager.addEntity(p);
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
            entityManager.removeEntity(p);
    }
}
