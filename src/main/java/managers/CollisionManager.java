package managers;

import entities.Entity;
import entities.Player;
import entities.emitters.Bullet;
import entities.enemies.Enemy;
import messaging.Message;
import messaging.MessageQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionManager {

    private final List<Entity> entities;

    private final List<Bullet> bullets;
    private final List<Enemy> enemies;
    private final List<Player> player;

    private final MessageQueue msgQueue;
    private Entity entity1, entity2;

    public CollisionManager(List<Entity> entities, List<Bullet> bullets, List<Enemy> enemies, List<Player> player) {
        this.entities = entities;
        msgQueue = MessageQueue.getInstance();
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

    private boolean doCheckAndReturnContinue(int i, int j) {
        entity1 = entities.get(i);
        entity2 = entities.get(j);

        if (entity1.equals(entity2))
            return true;
        if (entity1 instanceof Bullet && entity2 instanceof Bullet)
            return true;

        if (entity1.collidesWith(entity2)) {
            if (entity1 instanceof Bullet && entity2 instanceof Enemy) {
                Bullet b = (Bullet) entity1;
                msgQueue.add(new Message(entity2, entity1, "damage", String.valueOf(b.getDamage())));
                entities.remove(entity1);
            }
            else if(entity1 instanceof Player && entity2 instanceof Enemy){
                msgQueue.add(new Message(entity1, entity2, "damage", "5"));
                msgQueue.add(new Message(entity2, entity1, "damage", "100"));
            }
        }

        return false;
    }

    private void checkBulletCollisions(){
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet b = bulletIterator.next();
            for (Enemy e : enemies) {
                if (b.collidesWith(e)) {
                    msgQueue.add(new Message(e, b, "damage", String.valueOf(b.getDamage())));
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
                    msgQueue.add(new Message(p, e, "damage", "5"));
                    msgQueue.add(new Message(e, p, "damage", "100"));
                }
            }
        }
    }

    public void update(){
        checkCollisions();
        msgQueue.dispatch();
    }
}
