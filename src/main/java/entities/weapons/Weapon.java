package entities.weapons;

import entities.Camera;
import entities.Entity;
import entities.EntityType;
import entities.emitters.BulletEmitter;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public abstract class Weapon extends Entity {
    private static final int RADIUS_FROM_SHIP = 120;

    private float mouseX, mouseY;
    double angle, lastAngle;
    private Point origin;

    private Image image;

    private BulletEmitter bulletEmitter;

    public Weapon(Point origin) {
        this.origin = origin;
    }

    public void init(GameContainer gc){
        try {
            image = getImage();
        } catch (SlickException e) {
            e.printStackTrace();
        }

        setX(origin.getX());
        setY(origin.getY());

        float[] vertices = new float[] {
                getX(),getY(),
                getX(),getY()-60,
                getX()+25,getY()-60,
                getX()+25,getY()};
        body = new Polygon(vertices);


        try {
            bulletEmitter = createBulletEmitter();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void update(GameContainer gc, float delta) throws SlickException {
        updateMovement(gc);

        if(gc.getInput().isMouseButtonDown(gc.getInput().MOUSE_LEFT_BUTTON)) {
            bulletEmitter.tryShoot(angle, delta);
        }

        bulletEmitter.setX(getX());
        bulletEmitter.setY(getY());
        bulletEmitter.update(gc, delta);
    }

    private void updateMovement(GameContainer gc) {
        setX(origin.getX());
        setY(origin.getY());

        mouseX = (gc.getInput().getMouseX());
        mouseY = (gc.getInput().getMouseY());

        //angle = Math.atan2(mouseX - (gc.getWidth()/2 - player.getWidth()/4), (gc.getHeight()/2 - player.getHeight()/4) - mouseY);
//        angle = Math.atan2(mouseX - Camera.getFocusRelativeToScreenX(gc), Camera.getFocusRelativeToScreenY(gc) - mouseY);
        angle = Math.atan2(Camera.convertActualXToGameX(mouseX) - Camera.getX(), Camera.getY() - Camera.convertActualYToGameY(mouseY));

        body = (Polygon) body.transform(Transform.createRotateTransform((float) (angle - lastAngle), getX(), getY()));

        image.setRotation((float) Math.toDegrees(angle));

        body.setCenterX(getX()+(float) (RADIUS_FROM_SHIP*Math.cos(angle-Math.toRadians(90))));
        body.setCenterY(getY()+(float) (RADIUS_FROM_SHIP*Math.sin(angle-Math.toRadians(90))));

        lastAngle = angle;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        bulletEmitter.render(gc, g);
        g.drawImage(image, body.getCenterX()-image.getWidth()/2, body.getCenterY()-image.getHeight()/2);
    }

    protected abstract Image getImage() throws SlickException;
    protected abstract BulletEmitter createBulletEmitter() throws SlickException;

    public BulletEmitter getBulletEmitter() {
        return bulletEmitter;
    }

    @Override
    public EntityType getType() {
        return EntityType.NON_COLLIDING;
    }
}
