package entities.emitters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class PortalEmitter extends ParticleEmitter{

    private static final int PARTICLE_DELAY = 100;
    private int timeToNextParticle;

    public PortalEmitter(float x, float y){
        setX(x);
        setY(y);
    }

    @Override
    public void init(GameContainer gc) {

    }

    public void update(GameContainer gc, float delta) throws SlickException {
        super.update(gc, delta);
        timeToNextParticle++;
        if(timeToNextParticle >= PARTICLE_DELAY){
            Particle p = new PortalParticle(x, y);
            p.init(gc);
            addParticle(p);
            timeToNextParticle = 0;
        }
    }
}
