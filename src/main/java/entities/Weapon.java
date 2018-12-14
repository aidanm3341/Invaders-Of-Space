package entities;

import java.util.ArrayList;

import entities.emitters.Bullet;
import entities.emitters.BulletEmitter;
import entities.emitters.Particle;
import messaging.Message;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class Weapon extends Entity{
    private static final int RADIUS_FROM_SHIP = 120;

    private float mouseX, mouseY;
    double angle, lastAngle;
    private Player player;

    private Image image;

    private BulletEmitter bulletEmitter;

    public Weapon(Player player)
    {
        this.player = player;
    }

    public void init(GameContainer gc) throws SlickException {
        image = new Image("laser.png");

        x = player.getX();
        y = player.getY();

        float[] vertices = new float[] {
                x,y,
                x,y-60,
                x+25,y-60,
                x+25,y};
        body = new Polygon(vertices);


        bulletEmitter = new BulletEmitter();
    }

    public void update(GameContainer gc, float delta) throws SlickException
    {
        updateMovement(gc);

        if(gc.getInput().isMouseButtonDown(gc.getInput().MOUSE_LEFT_BUTTON))
        {
            bulletEmitter.tryShoot(angle, delta);
        }
        bulletEmitter.setX(x);
        bulletEmitter.setY(y);
        bulletEmitter.update(gc, delta);
    }

    private void updateMovement(GameContainer gc)
    {
        x = player.getX();
        y = player.getY();

        mouseX = (gc.getInput().getMouseX());
        mouseY = (gc.getInput().getMouseY());

        //angle = Math.atan2(mouseX - (gc.getWidth()/2 - player.getWidth()/4), (gc.getHeight()/2 - player.getHeight()/4) - mouseY);
//        angle = Math.atan2(mouseX - Camera.getFocusRelativeToScreenX(gc), Camera.getFocusRelativeToScreenY(gc) - mouseY);
        angle = Math.atan2(Camera.convertActualXToGameX(mouseX) - Camera.getX(), Camera.getY() - Camera.convertActualYToGameY(mouseY));

        body = (Polygon) body.transform(Transform.createRotateTransform((float) (angle - lastAngle), x, y));

        image.setRotation((float) Math.toDegrees(angle));

        body.setCenterX(x+(float) (RADIUS_FROM_SHIP*Math.cos(angle-Math.toRadians(90))));
        body.setCenterY(y+(float) (RADIUS_FROM_SHIP*Math.sin(angle-Math.toRadians(90))));

        lastAngle = angle;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        bulletEmitter.render(gc, g);
        g.drawImage(image, body.getCenterX()-image.getWidth()/2, body.getCenterY()-image.getHeight()/2);
    }

    public void onMessage(Message msg) {

    }
}
