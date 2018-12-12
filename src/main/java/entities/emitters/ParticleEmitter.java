package entities.emitters;

import entities.Entity;
import managers.EntityManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public abstract class ParticleEmitter extends Entity {

    protected ArrayList<Particle> particles;
    protected float x, y;

    public ParticleEmitter(){
        particles = new ArrayList<Particle>();
    }

    public void update(GameContainer gc, float delta) throws SlickException {
        for(int i=0; i<particles.size(); i++)
        {
            Particle p = particles.get(i);
            if(p.getLife() <= 0)
            {
                particles.remove(p);
                EntityManager.removeEntity(p);
            }
        }
    }

    public void addParticle(Particle newParticle)
    {
        particles.add(newParticle);
        EntityManager.addEntity(newParticle);
    }

    public void reset()
    {
        while(!particles.isEmpty())
        {
            particles.remove(0);
        }
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }
}
