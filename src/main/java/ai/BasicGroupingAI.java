package ai;

import entities.enemies.Enemy;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import util.Vector2D;

import java.util.List;

public class BasicGroupingAI extends AIimp{

    private final List<Enemy> enemies;
    private final float desiredDistance;

    public BasicGroupingAI(Enemy enemy, List<Enemy> enemies, Vector2f player) {
        super(enemy, player);
        this.enemies = enemies;

        maxSpeed = 0.4f;
        maxSteeringForce = 0.0006f;
        desiredDistance = 100;
    }

    public void action(float delta) {
        Vector2f desired = getDesiredForce();
        Vector2f separation = getSeparationForce();

        desired.scale(1f);
        separation.scale(1f);


        applyForce(desired);
        applyForce(separation);

        angle = (float) (Math.toRadians(velocity.getTheta()) + Math.PI/2);

        updateNew(delta);
    }

    private Vector2f getDesiredForce(){
        Vector2f target = new Vector2f(player.getX(), player.getY());
        Vector2f desired = target.sub(position);
        desired.normalise();
        desired.scale(maxSpeed);

        Vector2D steer = new Vector2D(desired.sub(velocity));
        steer.limitLocal(maxSteeringForce);
        return steer;
    }

    private Vector2f getSeparationForce(){
        Vector2f sum = new Vector2f();
        Vector2D steer = new Vector2D();
        float count = 0;

        for(Enemy e : enemies){
            float d = me.getPos().distance(e.getPos());
            if(d > 0 && d < desiredDistance){
                Vector2f diff = me.getPos().copy().sub(e.getPos());
                diff.normalise();
                diff.scale(1/d); // divide by 'd' in order to have a greater force the closer the thing is to us
                sum.add(diff);
                count++;
            }
            if (count > 0) {
                sum.scale(1/count);
                sum.normalise();
                sum.scale(maxSpeed);
                steer = new Vector2D( sum.copy().sub(velocity) );
                steer.limitLocal(maxSteeringForce);
            }
        }

        return steer;
    }
}
