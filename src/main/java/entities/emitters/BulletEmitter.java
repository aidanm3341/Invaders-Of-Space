package entities.emitters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.List;

public class BulletEmitter extends ParticleEmitter{

    private final float ticksBetweenBullets;
    private float bulletTimeCounter = 2;
    private final float bulletLife = 2000;

    public BulletEmitter(float ticksBetweenBullets) {
        this.ticksBetweenBullets = ticksBetweenBullets;
    }

    public void init(GameContainer gc) throws SlickException { }

    public void render(GameContainer gc, Graphics g) throws SlickException { }

    public void tryShoot(double angle, float delta) throws SlickException {
        if(bulletTimeCounter >= ticksBetweenBullets){
            Bullet newBullet = new Bullet(x, y, (float) angle, (int) bulletLife, 10);
            addParticle(newBullet);
            bulletTimeCounter = 0;
        }

        bulletTimeCounter += delta;
    }

    public List<Particle> getParticles()
    {
        return particles;
    }
}
