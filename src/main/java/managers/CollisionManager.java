package managers;

import entities.Entity;
import entities.Player;
import entities.emitters.Bullet;
import entities.enemies.Enemy;

import java.util.Iterator;
import java.util.List;

public class CollisionManager {

    private final List<Entity> entities;

    private final List<Bullet> bullets;
    private final List<Enemy> enemies;
    private final List<Player> player;

    public CollisionManager(List<Entity> entities, List<Bullet> bullets, List<Enemy> enemies, List<Player> player) {
        this.entities = entities;
        this.bullets = bullets;
        this.enemies = enemies;
        this.player = player;
    }

    public void checkCollisions() {
        checkBulletCollisions();
        checkPlayerCollisions();
    }

    private void checkBulletCollisions(){
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet b = bulletIterator.next();
            for (Enemy e : enemies) {
                if (b.collidesWith(e)) {
                    e.damage(b.getDamage());
                    b.destroy();
                    bulletIterator.remove();
                    break;
                }
            }
        }
    }

    private void checkPlayerCollisions(){
        for(Enemy e : enemies){
            for(Player p : player) {
                if (p.collidesWith(e)) {
                    p.damage(5);
                    e.damage(100);
                }
            }
        }
    }

    public void update(){
        checkCollisions();
    }
}
