package managers;

import entities.Arena;
import entities.Entity;
import entities.EntityType;
import entities.Player;
import entities.emitters.Bullet;
import entities.enemies.Enemy;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private static EntityManager instance;

    private List<Entity> entities;
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Player> player;

    private CollisionManager collisions;
    private Arena bg;

    private EntityManager(){}

    public void init(GameContainer gc) throws SlickException {
        entities = new ArrayList<>();
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        player = new ArrayList<>();

        bg = new Arena();
        collisions = new CollisionManager(entities, bullets, enemies, player);

        bg.init(gc);
        for(int j = 0; j< entities.size(); j++)
            entities.get(j).init(gc);
    }


    public void update(GameContainer gc, int i) throws SlickException {
        bg.update(gc, i);
        for(int j = 0; j< entities.size(); j++) {
            entities.get(j).update(gc, i);
        }
        collisions.update();
    }


    public void render(GameContainer gc, Graphics g) throws SlickException {
        bg.render(gc, g);
        for(int j = 0; j< entities.size(); j++)
            entities.get(j).render(gc, g);
    }

    public void addEntity(Entity e) {
        entities.add(e);
        if(e.getType().equals(EntityType.BULLET))
            bullets.add((Bullet) e);
        else if(e.getType().equals(EntityType.ENEMY))
            enemies.add((Enemy) e);
        else if(e.getType().equals(EntityType.PLAYER))
            player.add((Player) e);
    }

    public void removeEntity(Entity e){
        entities.remove(e);
        if(e.getType().equals(EntityType.BULLET))
            bullets.remove(e);
        else if(e.getType().equals(EntityType.ENEMY))
            enemies.remove(e);
        else if(e.getType().equals(EntityType.PLAYER))
            player.remove(e);
    }

    public void reset(GameContainer gc) throws SlickException{
        init(gc);
    }

    public static EntityManager getInstance(){
        if(instance == null)
            instance = new EntityManager();
        return instance;
    }

}
