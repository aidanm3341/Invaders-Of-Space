package entities.emitters;

import messaging.Message;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class BulletEmitter extends ParticleEmitter{

    private float ticksBetweenBullets = 100;
    private float bulletTimeCounter = 2;

    public BulletEmitter(float ticksBetweenBullets) {
        this.ticksBetweenBullets = ticksBetweenBullets;
    }

    public void init(GameContainer gc) throws SlickException { }

    public void render(GameContainer gc, Graphics g) throws SlickException { }

    public void onMessage(Message msg) { }

    public void tryShoot(double angle, float delta) throws SlickException
    {
        if(bulletTimeCounter*delta >= ticksBetweenBullets *delta){
            Bullet newBullet = new Bullet(x, y, (float) angle, 1000, 10);
            addParticle(newBullet);
            bulletTimeCounter = 0;
        }

        bulletTimeCounter++;
    }

    public ArrayList<Particle> getParticles()
    {
        return particles;
    }
}
