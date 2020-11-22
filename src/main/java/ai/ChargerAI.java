package ai;

import entities.Player;
import entities.enemies.Enemy;

public class ChargerAI extends AIimp implements AI{

    private float velX, velY, thisToPlayerAngle;
    private float accel, maxAccel, rotationalAccel;
    private float speedThresholdForRotationSpeedChange;
    private float speed, maxSpeed, minSpeed, rotationalSpeed, maxRotationalSpeed, minRotationalSpeed;
    private float chargeConeAngle;

    public ChargerAI(Enemy enemy, Player player) {
        super(enemy, player);
    }

    public void init() {
        x = me.getX();
        y = me.getY();

        speed = 0.3f;
        maxSpeed = 0.7f;
        minSpeed = 0.2f;

        rotationalSpeed = 0.0005f;
        minRotationalSpeed = 0.00001f;
        maxRotationalSpeed = 0.01f;

        speedThresholdForRotationSpeedChange = 0.6f;

        rotationalAccel = 0.00005f;

        maxAccel = 0.001f;
        accel = 0;

        chargeConeAngle = 2;

        angle = (float) Math.atan2(player.getX() - x, y - player.getY());
        updateEnemy();
    }

    public void action(float delta) {
        x = me.getX();
        y = me.getY();
        angle = me.getAngle();
        lastAngle = me.getLastAngle();

        updateAngle(delta);
        updatePosition(delta);
        updateEnemy();
    }

//    public void action2(float delta){
//        x = me.getX();
//        y = me.getY();
//
//        angle = (float) Math.atan2(player.getX() - x, y - player.getY());
//        speed += accel;
//
//        if(speed >= maxSpeed)
//            speed = maxSpeed;
//        else if(speed <= minSpeed)
//            speed = minSpeed;
//
//        velX = (float) (speed * Math.cos(angle - Math.toRadians(90)))*delta;
//        velY = (float) (speed * Math.sin(angle - Math.toRadians(90)))*delta;
//
//        x += velX;
//        y += velY;
//
//        lastAngle = angle;
//        updateEnemy();
//    }

    private void updateAngle(float delta){
        thisToPlayerAngle = (float) Math.atan2(player.getX() - x, y - player.getY());

        double a = thisToPlayerAngle - angle;
        double b = thisToPlayerAngle - angle + 2*Math.PI;
        double c = thisToPlayerAngle - angle - 2*Math.PI;

        double a1 = Math.abs(a);
        double b1 = Math.abs(b);
        double c1 = Math.abs(c);

        double z = Math.min(Math.min(a1, b1), c1);

        if(speed <= speedThresholdForRotationSpeedChange){
            rotationalSpeed += rotationalAccel;
            if(rotationalSpeed >= maxRotationalSpeed)
                rotationalSpeed = maxRotationalSpeed;
        }
        else{
            rotationalSpeed -= rotationalAccel;
            if(rotationalSpeed <= minRotationalSpeed)
                rotationalSpeed = minRotationalSpeed;
        }


        if(z == a1) z = a;
        else if(z == b1) z = b;
        else if(z == c1) z = c;

        if(z <= 0)
            angle -= rotationalSpeed;
        else
            angle += rotationalSpeed;

        if(angle >= Math.PI)
            angle = (float) -Math.PI;
        else if(angle <= -Math.PI)
            angle = (float) Math.PI;
    }


    private void updatePosition(float delta){
        if(angle - thisToPlayerAngle < chargeConeAngle/2 && angle - thisToPlayerAngle > -chargeConeAngle/2)
            accel = maxAccel;
        else
            accel = -maxAccel;

        speed += accel;
        if(speed >= maxSpeed)
            speed = maxSpeed;
        else if(speed <= minSpeed)
            speed = minSpeed;

        velX = (float) (speed * Math.cos(angle - Math.toRadians(90)))*delta;
        velY = (float) (speed * Math.sin(angle - Math.toRadians(90)))*delta;

        x += velX;
        y += velY;

        lastAngle = angle;
    }
}
