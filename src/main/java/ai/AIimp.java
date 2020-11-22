package ai;

import entities.Player;
import entities.enemies.Enemy;

public abstract class AIimp implements AI{

    protected Enemy me;
    protected Player player;
    protected float x, y, angle, lastAngle;

    public AIimp(Enemy enemy, Player player){
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
