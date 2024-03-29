package entities.enemies;

import ai.AI;
import entities.Entity;
import entities.EntityType;
import entities.emitters.EctoEmitter;
import managers.EntityManager;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Entity {

    private final EntityManager entityManager = EntityManager.getInstance();
    private float angle, lastAngle;
    protected int life;
    protected Image image;
    protected Color color;
    protected int ectoSize;

    protected SpriteSheet sheet;
    private float sheetCount;

    protected AI ai;

    private List<DeathListener> deathListeners;


    public Enemy(float x, float y) {
        setX(x);
        setY(y);
        ectoSize = 25;

        this.isCollidable = true;

        deathListeners = new ArrayList<>();
    }

    public abstract void init(GameContainer gc);

    public void update(GameContainer gc, float delta) throws SlickException {
        ai.action(delta);


        body = (Polygon) body.transform(Transform.createRotateTransform(angle - lastAngle));

        body.setCenterX(getX());
        body.setCenterY(getY());

        image = sheet.getSprite((int)sheetCount/50, 0);
        image.setRotation((float) Math.toDegrees(angle));

        if(sheetCount > 150)
            sheetCount = 0;

        sheetCount += 0.4*delta;



        if(life <= 0) {
            for (DeathListener deathListener : deathListeners) {
                deathListener.notifyOfDeath(this);
            }
            entityManager.removeEntity(this);
        }
    }


    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(image, getX()-image.getWidth()/2, getY()-image.getHeight()/2);
        //g.draw(body);
    }

    public void damage(float amount){
        life -= amount;
        entityManager.addEntity(new EctoEmitter(getX(), getY(), color, ectoSize));
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

    public void addDeathListener(DeathListener deathListener){
        deathListeners.add(deathListener);
    }

    public void removeDeathListener(DeathListener deathListener){
        deathListeners.remove(deathListener);
    }

    public EntityType getType(){
        return EntityType.ENEMY;
    }
}
