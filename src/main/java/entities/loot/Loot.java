package entities.loot;

import entities.Entity;
import entities.EntityType;
import managers.EntityManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class Loot extends Entity {

    private EntityManager entityManager = EntityManager.getInstance();

    private final float SIZE_OF_BOB = 10;
    private final float SPEED_OF_BOB = 0.008f;

    private float visualY;
    private double a;
    private final Image image;

    public Loot(float x, float y, Image image) throws SlickException {
        this.image = image;
        setX(x - image.getWidth()/2);
        setY(y - image.getHeight()/2);
        this.isCollidable = true;

        this.width = image.getWidth();
        this.height = image.getHeight();
        float[] vertices = new float[]{
                getX(),getY(),
                getX(),getY()+height,
                getX()+width,getY()+height,
                getX()+width,getY()
        };
        body = new Polygon(vertices);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

    }

    @Override
    public void update(GameContainer gc, float delta) throws SlickException {
        a += SPEED_OF_BOB;
        if(a > 360)
            a = 0;
        visualY = getY() + (SIZE_OF_BOB * (float) Math.sin(a));
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(image, getX(), visualY);
    }

    @Override
    public EntityType getType() {
        return EntityType.LOOT;
    }
}
