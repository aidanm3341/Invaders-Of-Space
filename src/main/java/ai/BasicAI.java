package ai;

import entities.Player;
import entities.enemies.Enemy;

public class BasicAI extends AIimp implements AI {

    private float speed;
    private float velX, velY;

    public BasicAI(Enemy enemy, Player player) {
        super(enemy, player);
        speed = 0.3f;
    }

    public void init() {
        x = me.getX();
        y = me.getY();
        angle = (float) Math.atan2(player.getX() - x, y - player.getY());
        updateEnemy();
    }

    public void action(float delta) {
        x = me.getX();
        y = me.getY();

        angle = (float) Math.atan2(player.getX() - x, y - player.getY());

        velX = (float) (speed * Math.cos(angle - Math.toRadians(90)))*delta;
        velY = (float) (speed * Math.sin(angle - Math.toRadians(90)))*delta;

        x += velX;
        y += velY;

        lastAngle = angle;
        updateEnemy();
    }
}
