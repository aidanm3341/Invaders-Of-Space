package managers;

import entities.Arena;
import entities.Entity;
import entities.EntityType;
import entities.player.Player;
import entities.emitters.Bullet;
import entities.enemies.DeathListener;
import entities.enemies.Enemy;
import entities.loot.Loot;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class EntityManager implements DeathListener {
    private static EntityManager instance;

    private List<Entity> entities;
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Player> player;
    private List<Loot> loot;

    private CollisionManager collisions;
    private LootManager lootManager;
    private Arena bg;

    private EntityManager(){}

    public void init(GameContainer gc) {
        entities = new ArrayList<>();
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        player = new ArrayList<>();
        loot = new ArrayList<>();

        bg = new Arena();
        collisions = new CollisionManager(entities, bullets, enemies, player, loot);
        lootManager = new LootManager();

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
        else if(e.getType().equals(EntityType.ENEMY)) {
            enemies.add((Enemy) e);
            ((Enemy) e).addDeathListener(this);
        }
        else if(e.getType().equals(EntityType.PLAYER))
            player.add((Player) e);
        else if(e.getType().equals(EntityType.LOOT))
            loot.add((Loot) e);
    }

    public void removeEntity(Entity e){
        entities.remove(e);
        if(e.getType().equals(EntityType.BULLET))
            bullets.remove(e);
        else if(e.getType().equals(EntityType.ENEMY)) {
            enemies.remove(e);
            ((Enemy) e).removeDeathListener(this);
        }
        else if(e.getType().equals(EntityType.PLAYER))
            player.remove(e);
        else if(e.getType().equals(EntityType.LOOT))
            loot.remove(e);
    }

    @Override
    public void notifyOfDeath(Enemy enemy) throws SlickException {
        addEntity(lootManager.generateLoot(enemy.getX(), enemy.getY()));
    }

    public void reset(GameContainer gc){
        init(gc);
    }

    public static EntityManager getInstance(){
        if(instance == null)
            instance = new EntityManager();
        return instance;
    }
}
