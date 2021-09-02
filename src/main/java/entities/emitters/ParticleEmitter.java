package entities.emitters;

import entities.Entity;
import entities.EntityType;
import managers.EntityManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public abstract class ParticleEmitter extends Entity {

    protected final EntityManager entityManager = EntityManager.getInstance();
    protected ArrayList<Particle> particles;
    protected float x, y;

    public ParticleEmitter(){
        particles = new ArrayList<Particle>();
    }

    public void update(GameContainer gc, float delta) throws SlickException {
        for(int i=0; i<particles.size(); i++) {
            Particle p = particles.get(i);
            if(p.getLife() <= 0) {
                removeParticle(p);
            }
        }
    }

    public void addParticle(Particle newParticle) {
        particles.add(newParticle);
        entityManager.addEntity(newParticle);
    }

    public void removeParticle(Particle p){
        particles.remove(p);
        entityManager.removeEntity(p);
    }

    public void render(GameContainer gc, Graphics g) {};

    public void reset() {
        particles.clear();
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    @Override
    public EntityType getType() {
        return EntityType.NON_COLLIDING;
    }
}
