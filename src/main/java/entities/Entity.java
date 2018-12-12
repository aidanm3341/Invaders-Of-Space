package entities;

import messaging.Message;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import java.util.ArrayList;

public abstract class Entity {

    protected float x, y, width, height;
    protected Polygon body;
    protected boolean isCollidable = false;

    public abstract void init(GameContainer gc) throws SlickException;

    public abstract void update(GameContainer gc, float i) throws SlickException;

    public abstract void render(GameContainer gc, Graphics g) throws SlickException;

    public abstract void onMessage(Message msg);

    public boolean collidesWith(Entity e){
        if(isCollidable && e.isCollidable)
            return body.intersects(e.body);
        else
            return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
