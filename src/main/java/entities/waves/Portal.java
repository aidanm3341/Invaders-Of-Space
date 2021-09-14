package entities.waves;

import entities.Arena;
import entities.Entity;
import entities.EntityType;
import entities.emitters.PortalEmitter;
import entities.enemies.Enemy;
import entities.enemies.EnemyFactory;
import entities.enemies.EnemyType;
import managers.EntityManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.LinkedList;

public class Portal extends Entity{

    private final EntityManager entityManager = EntityManager.getInstance();

    private static SpriteSheet sprite = null;

    static {
        try {
            sprite = new SpriteSheet("portal2.png", 256, 256);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static final int PORTAL_WIDTH = sprite.getSprite(0 ,0).getWidth();
    public static final int PORTAL_HEIGHT = sprite.getSprite(0 ,0).getHeight();

    private LinkedList<Enemy> enemies;
    private ArrayList<Enemy> enemyDeathTracker;

    private float spriteCounter;

    private int counter, spawnTime;

    private PortalEmitter emitter;

    public Portal(float x, float y) {
        setX(x);
        setY(y);
    }

    public void init(GameContainer gc){
        enemies = new LinkedList<>();
        enemyDeathTracker = new ArrayList<>();
        width = sprite.getSprite(0, 0).getWidth();
        height = sprite.getSprite(0, 0).getHeight();
        spriteCounter = 0;
        counter = 0;
        spawnTime = 500;

        emitter = new PortalEmitter(getX() + getWidth()/2, getY() + getHeight()/2);
        emitter.init(gc);
    }

    public void update(GameContainer gc, float delta) throws SlickException {
        if(counter >= spawnTime) {
            dispatchEnemy();
            counter = 0;
        }
        counter++;

        if(spriteCounter > 7)
            spriteCounter = 0;
        spriteCounter += 0.007 * delta;

        emitter.update(gc, delta);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(sprite.getSprite((int) spriteCounter, 0), getX(), getY());
        emitter.render(gc, g);
    }

    private void dispatchEnemy() {
        if(!enemies.isEmpty())
            entityManager.addEntity(enemies.poll());
    }

    public void addEnemy(GameContainer gc, EnemyType type, Vector2f target){
        Enemy newEnemy = EnemyFactory.createEnemy(type, target, getX() + width/2, getY() + height/2);
        newEnemy.init(gc);
        enemies.add(newEnemy);
        enemyDeathTracker.add(newEnemy);
    }

    public boolean isDone() {
        for(Enemy e : enemyDeathTracker)
            if(!e.isDead())
                return false;
        return true;
    }

    @Override
    public EntityType getType() {
        return EntityType.NON_COLLIDING;
    }
}
