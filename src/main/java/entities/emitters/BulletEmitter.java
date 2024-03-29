package entities.emitters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.List;

public class BulletEmitter extends ParticleEmitter{

    private float ticksBetweenBullets;
    private float bulletTimeCounter = 2;
    private final float bulletLife = 2000;

    private float bulletDamage;
    private Image image;

    public BulletEmitter(float ticksBetweenBullets, float bulletDamage, Image image) {
        this.ticksBetweenBullets = ticksBetweenBullets;
        this.bulletDamage = bulletDamage;
        this.image = image;
    }

    public void init(GameContainer gc) { }

    public void tryShoot(double angle, float delta) throws SlickException {
        if(bulletTimeCounter >= ticksBetweenBullets){
            Bullet newBullet = new Bullet(x, y, (float) angle, (int) bulletLife, bulletDamage, image);
            addParticle(newBullet);
            bulletTimeCounter = 0;
        }

        bulletTimeCounter += delta;
    }

    public List<Particle> getParticles()
    {
        return particles;
    }

    public float getTicksBetweenBullets() {
        return ticksBetweenBullets;
    }

    public void setTicksBetweenBullets(float ticksBetweenBullets) {
        this.ticksBetweenBullets = ticksBetweenBullets;
    }

    public float getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(float bulletDamage) {
        this.bulletDamage = bulletDamage;
    }
}
