package entities.player;

import entities.Arena;
import entities.Entity;
import entities.EntityType;
import entities.weapons.Weapon;
import entities.weapons.WeaponFactory;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entity {

    private float velX, velY;
    private float angle, angularVelocity;
    private float accel;

    private Image image;
    private WeaponFactory weaponFactory;
    private Weapon weapon;
    private StateBasedGame sbg;
    private GameContainer gc;
    private PlayerStats stats;

    private int scrapMetal;

    public Player(StateBasedGame sbg){
        this.sbg = sbg;
    }

    public void init(GameContainer gc) {
        this.gc = gc;
        stats = new PlayerStats(1, 60, 0.004f, 60);

        width = 60;
        height = 60;

        setX(Arena.WIDTH/2);
        setY(Arena.HEIGHT/2);

        this.isCollidable = true;

        try {
            image = new Image("ship.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        float[] vertices = new float[]{
                0,0,
                width,0,
                width,height,
                0,height
        };
        body = new Polygon(vertices);
        body.setCenterX(getX());
        body.setCenterY(getY());

        weaponFactory = new WeaponFactory();
        weapon = weaponFactory.basicWeapon(getPos());
        weapon.init(gc);

        scrapMetal = 0;
    }

    public void update(GameContainer gc, float i) throws SlickException {
        updateX(gc, i);
        updateY(gc, i);
        weapon.update(gc, i);
    }

    public void updateX(GameContainer gc, float delta) {
        delta *= 0.4f;
        float maxAccel = stats.getMaxAcceleration();
        float maxVel = stats.getMaxVelocity();
        accel = maxAccel * delta;
        if(gc.getInput().isKeyDown(Input.KEY_A)) {
            velX -= accel;
            if(velX < -maxVel)
                velX = -maxVel;
            if(angle > Math.toRadians(-20))
                angularVelocity = -0.01f;
            else
                angularVelocity = 0;
        }
        else {
            if(gc.getInput().isKeyDown(Input.KEY_D)) {
                velX += accel;
                if(velX > maxVel)
                    velX = maxVel;
                if(angle < Math.toRadians(20))
                    angularVelocity = 0.01f;
                else
                    angularVelocity = 0;
            }
            else {
                if(velX > 0) {
                    velX -= accel;
                    if(velX <= 0)
                        velX = 0;
                    if(angle > 0)
                        angularVelocity = -0.01f;
                    else
                        angularVelocity = 0;
                }
                else {
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

        body.setCenterX(getX());
        setX(getX() + velX*delta);
    }

    public void updateY(GameContainer gc, float delta) {
        delta *= 0.4f;
        float maxAccel = stats.getMaxAcceleration();
        float maxVel = stats.getMaxVelocity();
        accel = maxAccel* delta;

        gc.getInput();
        if(gc.getInput().isKeyDown(Input.KEY_W)) {
            velY -= accel;
            if(velY < -maxVel)
                velY = -maxVel;
        }
        else {
            if(gc.getInput().isKeyDown(Input.KEY_S)) {
                velY += accel;
                if(velY > maxVel)
                    velY = maxVel;
            }
            else {
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

        body.setCenterY(getY());
        setY(getY() + velY*delta);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(image, getX() - image.getWidth()/2, getY() - image.getHeight()/2);
        weapon.render(gc, g);
        //g.draw(body);
    }

    public void damage(int amount){
        stats.setCurrentLife(stats.getCurrentLife()-amount);
        if(stats.getCurrentLife() <= 0)
            sbg.enterState(0);
    }

    public void reset(GameContainer gc){
        init(gc);
    }

    public float getLife(){
        return stats.getCurrentLife();
    }

    public void setScrapMetal(int scrapMetal) {
        this.scrapMetal = scrapMetal;
    }

    public int getScrapMetal() {
        return scrapMetal;
    }

    public PlayerStats getStats(){
        return stats;
    }

    public void setWeapon(Weapon weapon) throws SlickException {
        this.weapon = weapon;
        weapon.init(gc);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }
}
