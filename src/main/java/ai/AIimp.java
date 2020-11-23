package ai;

import entities.enemies.Enemy;
import org.newdawn.slick.geom.Point;

public abstract class AIimp implements AI{

    protected Enemy me;
    protected Point player;
    protected float x, y, angle, lastAngle;

    public AIimp(Enemy enemy, Point player){
        this.me = enemy;
        this.player = player;
    }

    public abstract void init();

    public abstract void action(float delta);

    protected void updateEnemy()
    {
        me.setX(x);
        me.setY(y);
        me.setAngle(angle);
        me.setLastAngle(lastAngle);
    }
}
