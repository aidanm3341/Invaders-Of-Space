package entities.waves;

import entities.Entity;
import entities.EntityType;
import entities.enemies.Enemy;
import entities.enemies.EnemyFactory;
import entities.enemies.EnemyType;
import managers.EntityManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;
import java.util.LinkedList;

public class Portal extends Entity{

    private final EntityManager entityManager = EntityManager.getInstance();
    private static final Point TOP_LEFT_POS = new Point(100, 100);

    private static SpriteSheet sprite = null;

    static {
        try {
            sprite = new SpriteSheet("portal.png", 126, 147);
        } catch (SlickException e) {}
    }

    public static final int PORTAL_WIDTH = sprite.getSprite(0 ,0).getWidth();
    public static final int PORTAL_HEIGHT = sprite.getSprite(0 ,0).getHeight();

    private LinkedList<Enemy> enemies;
    private ArrayList<Enemy> enemyDeathTracker;

    private float spriteCounter;

    private int counter, spawnTime;
    private boolean isDone;

    public Portal(float x, float y) throws SlickException {
        setX(x);
        setY(y);
    }

    public void init(GameContainer gc) throws SlickException {
        enemies = new LinkedList<Enemy>();
        enemyDeathTracker = new ArrayList<Enemy>();
        width = sprite.getSprite(0, 0).getWidth();
        height = sprite.getSprite(0, 0).getHeight();
        spriteCounter = 0;
        counter = 0;
        spawnTime = 500;
    }

    public void update(GameContainer gc, float delta) throws SlickException {
        if(counter >= spawnTime) {
            dispatchEnemy();
            counter = 0;
        }
        counter++;

        //image = sheet.getSprite(sheetCount/50, 0);
        if(spriteCounter > 90)
            spriteCounter = 0;
        spriteCounter += 0.3 * delta;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(sprite.getSprite((int) spriteCounter/50, 0), getX(), getY());
    }

    private void dispatchEnemy() {
        if(!enemies.isEmpty())
            entityManager.addEntity(enemies.poll());
    }

    public void addEnemy(GameContainer gc, EnemyType type, Point target) throws SlickException{
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
