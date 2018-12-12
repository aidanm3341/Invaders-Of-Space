package entities.emitters;

import java.util.ArrayList;

import messaging.Message;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class BulletEmitter extends ParticleEmitter{

    private float bulletMaxCount = 100;
    private float bulletTimeCounter = 2;

    public BulletEmitter() {}

    public void init(GameContainer gc) throws SlickException { }

    public void render(GameContainer gc, Graphics g) throws SlickException { }

    public void onMessage(Message msg) { }

    public void tryShoot(double angle, float delta) throws SlickException
    {
        if(bulletTimeCounter*delta >= bulletMaxCount*delta){
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
