package entities;

import messaging.Message;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entity{

    private static final float MAX_VEL = 1f;//0.7f;
    private static final int MAX_LIFE = 100;

    private float velX, velY;
    private float angle, angularVelocity;
    private  float accel, maxAccel;

    private int life;

    private Image image;
    private Weapon weapon;
    private StateBasedGame sbg;

    public Player(StateBasedGame sbg){
        this.sbg = sbg;
    }

    public void init(GameContainer gc) throws SlickException {
        maxAccel = 0.004f;
        life = MAX_LIFE;

        width = 60;
        height = 60;

        x = 200;
        y = 200;

        this.isCollidable = true;

        image = new Image("ship.png");

        float[] vertices = new float[]{
                0,0,
                width,0,
                width,height,
                0,height
        };
        body = new Polygon(vertices);
        body.setCenterX(x);
        body.setCenterY(y);

        weapon = new Weapon(this);
        weapon.init(gc);
    }

    public void update(GameContainer gc, float i) throws SlickException
    {
        updateX(gc, i);
        updateY(gc, i);
        weapon.update(gc, i);
    }

    public void updateX(GameContainer gc, float delta) {
        delta *= 0.4f;
        accel = maxAccel * delta;
        if(gc.getInput().isKeyDown(Input.KEY_A))
        {
            velX -= accel;
            if(velX < -MAX_VEL)
                velX = -MAX_VEL;
            if(angle > Math.toRadians(-20))
                angularVelocity = -0.01f;
            else
                angularVelocity = 0;

        }
        else {
            if(gc.getInput().isKeyDown(Input.KEY_D))
            {
                velX += accel;
                if(velX > MAX_VEL)
                    velX = MAX_VEL;
                if(angle < Math.toRadians(20))
                    angularVelocity = 0.01f;
                else
                    angularVelocity = 0;
            }
            else
            {
                if(velX > 0)
                {
                    velX -= accel;
                    if(velX <= 0)
                        velX = 0;
                    if(angle > 0)
                        angularVelocity = -0.01f;
                    else
                        angularVelocity = 0;
                }
                else
                {
                    velX += accel;
                    if(velX >= 0)
                        velX = 0;
                    if(angle < 0)
                        angularVelocity = 0.01f;
                    else
                        angularVelocity = 0;
                }
            }
        }

        angle += angularVelocity;
        image.setRotation((float) Math.toDegrees(angle));

        body.setCenterX(x);
        x += velX*delta;
    }

    public void updateY(GameContainer gc, float delta) {
        delta *= 0.4f;
        accel = maxAccel* delta;

        gc.getInput();
        if(gc.getInput().isKeyDown(Input.KEY_W))
        {
            velY -= accel;
            if(velY < -MAX_VEL)
                velY = -MAX_VEL;
        }
        else {
            if(gc.getInput().isKeyDown(Input.KEY_S))
            {
                velY += accel;
                if(velY > MAX_VEL)
                    velY = MAX_VEL;
            }
            else
            {
                if(velY > 0){
                    velY -= accel;
                    if(velY <= 0)
                        velY = 0;
                }
                else{
                    velY += accel;
                    if(velY >= 0)
                        velY = 0;
                }
            }
        }

        body.setCenterY(y);
        y += velY*delta;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2);
        weapon.render(gc, g);
        //g.draw(body);
    }

    public void onMessage(Message msg) {
        if(msg.getType().equals("damage")){
            life -= Integer.parseInt(msg.getData());
            if(life <= 0)
                sbg.enterState(0);
        }
    }

    public void reset(GameContainer gc) throws SlickException{
        init(gc);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public int getLife(){
        return life;
    }
}
