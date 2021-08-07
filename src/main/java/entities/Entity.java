package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;

public abstract class Entity{

    protected Point pos;
    protected float width, height;
    protected Polygon body;
    protected boolean isCollidable = false;

    public Entity(){
        pos = new Point(0, 0);
    }

    public abstract void init(GameContainer gc) throws SlickException;

    public abstract void update(GameContainer gc, float i) throws SlickException;

    public abstract void render(GameContainer gc, Graphics g) throws SlickException;

    public boolean collidesWith(Entity e){
        if(isCollidable && e.isCollidable)
            return body.intersects(e.body);
        else
            return false;
    }

    public float getX() {
        return pos.getX();
    }

    public float getY() {
        return pos.getY();
    }

    public void setX(float x){ pos.setX(x); }

    public void setY(float y){ pos.setY(y); }

    public Point getPos(){
        return pos;
    }


    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public abstract EntityType getType();

}
