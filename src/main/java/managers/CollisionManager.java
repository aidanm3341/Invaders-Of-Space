package managers;

import entities.Entity;
import entities.Player;
import entities.emitters.Bullet;
import entities.enemies.Enemy;
import entities.loot.Loot;

import java.util.Iterator;
import java.util.List;

public class CollisionManager {

    private final List<Entity> entities;

    private final List<Bullet> bullets;
    private final List<Enemy> enemies;
    private final List<Player> player;
    private final List<Loot> loot;

    public CollisionManager(List<Entity> entities, List<Bullet> bullets, List<Enemy> enemies, List<Player> player, List<Loot> loot) {
        this.entities = entities;
        this.bullets = bullets;
        this.enemies = enemies;
        this.player = player;
        this.loot = loot;
    }

    public void checkCollisions() {
        checkBulletCollisions();
        checkPlayerCollisions();
        checkLootCollisions();
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

    private void checkLootCollisions(){
        Iterator<Loot> lootIterator = loot.iterator();
        while(lootIterator.hasNext()){
            Loot l = lootIterator.next();
            for(Player p : player){
                if(p.collidesWith(l)){
                    lootIterator.remove();
                    entities.remove(l);

                    p.setScrapMetal(p.getScrapMetal() + 1);
                }
            }
        }
    }

    public void update(){
        checkCollisions();
    }
}
