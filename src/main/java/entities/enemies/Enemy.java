package entities.enemies;

import ai.AI;
import entities.Entity;
import managers.EntityManager;
import entities.Player;
import entities.emitters.EctoEmitter;
import messaging.Message;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public abstract class Enemy extends Entity {

    private float angle, lastAngle;
    protected int life;
    protected Image image;
    protected Color color;
    protected int ectoSize;

    protected SpriteSheet sheet;
    private int sheetCount;

    protected AI ai;


    public Enemy(float x, float y)
    {
        setX(x);
        setY(y);
        ectoSize = 25;

        this.isCollidable = true;
    }

    public abstract void init(GameContainer gc) throws SlickException;

    public void update(GameContainer gc, float delta) throws SlickException
    {
        ai.action(delta);


        body = (Polygon) body.transform(Transform.createRotateTransform(angle - lastAngle));

        body.setCenterX(getX());
        body.setCenterY(getY());

        image = sheet.getSprite(sheetCount/50, 0);
        image.setRotation((float) Math.toDegrees(angle));

        if(sheetCount > 150)
            sheetCount = 0;

        sheetCount++;



        if(life <= 0)
            EntityManager.removeEntity(this);
    }


    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawImage(image, getX()-image.getWidth()/2, getY()-image.getHeight()/2);
        //g.draw(body);
    }

    public void onMessage(Message msg)
    {
        if(msg.getType().equals("damage")){
            life -= Integer.parseInt(msg.getData());
            EntityManager.addEntity(new EctoEmitter(getX(), getY(), color, ectoSize));
        }
    }

    public boolean isDead(){
        return life <= 0;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
    public void setLastAngle(float lastAngle) {
        this.lastAngle = lastAngle;
    }
    public float getAngle() {
        return angle;
    }
    public float getLastAngle() {
        return lastAngle;
    }
}
