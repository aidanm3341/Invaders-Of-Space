package ai;

import entities.enemies.Enemy;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import util.Vector2D;

public abstract class AIimp implements AI{

    protected Enemy me;
    protected Point player;
    protected float x, y, angle, lastAngle;

    protected Vector2D position, velocity, acceleration;
    protected float maxSpeed;

    public AIimp(Enemy enemy, Point player){
        this.me = enemy;
        this.player = player;

        position = new Vector2D(enemy.getX(), enemy.getY());
        velocity = new Vector2D(0, 0);
        acceleration = new Vector2D(0, 0);
    }

    public void init() {}

    public abstract void action(float delta);

    public void applyForce(Vector2f force){
        acceleration.add(force);
    }

    protected void updateEnemy(){
        me.setX(x);
        me.setY(y);
        me.setAngle(angle);
        me.setLastAngle(lastAngle);
    }

    protected void updateNew(){
        velocity.add(acceleration);
        velocity.limit(maxSpeed);
        position.add(velocity);
        acceleration.scale(0);

        me.setX(position.x);
        me.setY(position.y);
        me.setAngle(angle);
    }
}
