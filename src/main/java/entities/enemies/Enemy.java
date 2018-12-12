package entities.enemies;

import entities.Entity;
import managers.EntityManager;
import entities.Player;
import entities.emitters.EctoEmitter;
import messaging.Message;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public abstract class Enemy extends Entity {

    private float velX, velY, angle, lastAngle;
    protected int life;
    protected Image image;
    protected Color color;
    protected int ectoSize;

    protected SpriteSheet sheet;
    private int sheetCount, hitCount;

    private Player player;

    public Enemy(float x, float y, Player player) throws SlickException
    {
        this.player = player;

        this.x = x;
        this.y = y;

        ectoSize = 25;

        this.isCollidable = true;

        velX = (float) (0.5 * Math.cos(angle));
        velY = (float) (0.5 * Math.sin(angle));
    }

    public abstract void init(GameContainer gc) throws SlickException;

    public void update(GameContainer gc, float delta) throws SlickException
    {
        updateMovement(gc, delta);
        if(life <= 0)
            EntityManager.removeEntity(this);
    }

    private void updateMovement(GameContainer gc, float delta)
    {
        delta *= 0.4f;
        angle = (float) Math.atan2(player.getX() - x, y - player.getY());

        velX = (float) (0.4 * Math.cos(angle - Math.toRadians(90)))*delta;
        velY = (float) (0.4 * Math.sin(angle - Math.toRadians(90)))*delta;

        x += velX;
        y += velY;

        body = (Polygon) body.transform(Transform.createRotateTransform(angle - lastAngle));

        body.setCenterX(x);
        body.setCenterY(y);

        image = sheet.getSprite(sheetCount/50, 0);
        image.setRotation((float) Math.toDegrees(angle));

        if(hitCount > 0){
            image.setImageColor(255, 0, 0);
            hitCount--;
        }

        if(sheetCount > 150)
            sheetCount = 0;

        sheetCount++;
        lastAngle = angle;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawImage(image, x-image.getWidth()/2, y-image.getHeight()/2);
        //g.draw(body);
    }

    public void onMessage(Message msg)
    {
        if(msg.getType().equals("damage")){
            life -= Integer.parseInt(msg.getData());
            EntityManager.addEntity(new EctoEmitter(x, y, color, ectoSize));
        }
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }
}
