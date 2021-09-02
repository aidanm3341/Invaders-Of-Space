package entities.emitters;

import managers.EntityManager;
import org.newdawn.slick.*;

import java.util.Random;

public class PortalParticle extends Particle{

    private Random rand;

    private Color color;

    public PortalParticle(float x, float y) {
        super(x, y, 1200);

        rand = new Random();
    }

    @Override
    public void init(GameContainer gc) {
        angle = rand.nextInt(360);
        velX = (float) Math.cos(Math.toRadians(angle));
        velY = (float) Math.sin(Math.toRadians(angle));

        float velocityMultiplier = 0.2f;

        velX *= velocityMultiplier;
        velY *= velocityMultiplier;

        accel = 0.0005f;

        color = new Color(255, 255, 255);
        color.a = 0.7f;
    }

    @Override
    public void update(GameContainer gc, float delta) throws SlickException {
        angle -= 0.15f;
        velX = (float) Math.cos(Math.toRadians(angle));
        velY = (float) Math.sin(Math.toRadians(angle));

        float velocityMultiplier = 0.2f;

        velX *= velocityMultiplier;
        velY *= velocityMultiplier;

        x += velX*delta;
        y += velY*delta;

        if(color.a > 0.05f){
            color.a -= 0.0005f*delta;
        }
        color.r -= 0.0005f*delta;
        color.g -= 0.001f*delta;
        color.b -= 0.0005f*delta;

        life -= delta;
        if(life < 0)
            EntityManager.getInstance().removeEntity(this);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 20, 20);
    }
}
