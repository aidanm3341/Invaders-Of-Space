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

    private Entity entity1, entity2;

    public CollisionManager(List<Entity> entities, List<Bullet> bullets, List<Enemy> enemies, List<Player> player) {
        this.entities = entities;
        this.bullets = bullets;
        this.enemies = enemies;
        this.player = player;
    }

    public void checkCollisions() {
        checkBulletCollisions();
        checkPlayerCollisions();
//        System.out.println(entities.size());
//        for(int i=0; i<entities.size(); i++){
//            for(int j=0; j<entities.size(); j++){
//                try {
//                    if(doCheckAndReturnContinue(i ,j))
//                        continue;
//                }catch(IndexOutOfBoundsException e){} // because of issues with removing bullets from entities cause crash otherwise
//            }
//        }
    }

    private void checkBulletCollisions(){
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet b = bulletIterator.next();
            for (Enemy e : enemies) {
                if (b.collidesWith(e)) {
                    //msgQueue.add(new Message(e, b, "damage", String.valueOf(b.getDamage())));
                    e.damage(b.getDamage());
                    //msgQueue.add(new Message(b, null, "destroyBullet", ""));
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
//                    msgQueue.add(new Message(p, e, "damage", "5"));
                    p.damage(5);
//                    msgQueue.add(new Message(e, p, "damage", "100"));
                    e.damage(100);
                }
            }
        }
    }

    public void update(){
        checkCollisions();
    }
}
