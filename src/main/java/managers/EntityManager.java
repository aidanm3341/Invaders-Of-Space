package managers;

import entities.Background;
import entities.Entity;
import entities.Player;
import entities.enemies.EnemyFactory;
import entities.enemies.EnemyType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class EntityManager {

    private static ArrayList<Entity> entities;
    private CollisionManager collisions;
    private EnemyFactory enemyFactory;
    private Background bg;
    private Player player;

    public EntityManager(Player player)
    {
        this.player = player;
    }

    public void init(GameContainer gc) throws SlickException {
        entities = new ArrayList<Entity>();
        enemyFactory = new EnemyFactory(player);
        bg = new Background();
        collisions = new CollisionManager(entities);

        entities.add(enemyFactory.getEnemy(EnemyType.YELLOW_YAK, 200, 200));
        entities.add(player);

        bg.init(gc);
        for(int j=0; j<entities.size(); j++)
            entities.get(j).init(gc);
    }


    public void update(GameContainer gc, int i) throws SlickException {

        bg.update(gc, i);
        for(int j=0; j<entities.size(); j++) {
            entities.get(j).update(gc, i);
        }
        collisions.update();
    }


    public void render(GameContainer gc, Graphics g) throws SlickException {
        bg.render(gc, g);
        for(int j=0; j<entities.size(); j++)
            entities.get(j).render(gc, g);
    }

    public static void addEntity(Entity e) {
        entities.add(e);
    }

    public static void removeEntity(Entity e){
        entities.remove(e);
    }
}
