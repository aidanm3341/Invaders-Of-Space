package ai;

import entities.enemies.Enemy;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import util.Vector2D;

public class NewBasicAI extends AIimp{

    public NewBasicAI(Enemy enemy, Point player) {
        super(enemy, player);
        maxSpeed = 0.4f;
        maxSteeringForce = 0.0006f;
    }

    public void action(float delta) {
        Vector2f target = new Vector2f(player.getX(), player.getY());
        Vector2f desired = target.sub(position);
        desired.normalise();
        desired.scale(maxSpeed);

        Vector2D steer = new Vector2D(desired.sub(velocity));
        steer.limitLocal(maxSteeringForce);
        applyForce(steer);

        angle = (float) (Math.toRadians(velocity.getTheta()) + Math.PI/2);

        updateNew(delta);
    }
}
