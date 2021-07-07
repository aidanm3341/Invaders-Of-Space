package managers;

import entities.Entity;
import entities.Player;
import entities.emitters.Bullet;
import entities.enemies.Enemy;
import messaging.Message;
import messaging.MessageQueue;

import java.util.ArrayList;
import java.util.Iterator;

public class CollisionManager {

    private ArrayList<Entity> entities;
    private MessageQueue msgQueue;
    private Entity entity1, entity2;

    public CollisionManager(ArrayList<Entity> entities)
    {
        this.entities = entities;
        msgQueue = MessageQueue.getInstance();
    }

    public void checkCollisions()
    {
        for(int i=0; i<entities.size(); i++){
            for(int j=0; j<entities.size(); j++){
                try {
                    if(doCheckAndReturnContinue(i ,j))
                        continue;
                }catch(IndexOutOfBoundsException e){} // because of issues with removing bullets from entities cause crash otherwise
            }
        }
    }

    private boolean doCheckAndReturnContinue(int i, int j)
    {
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

    public void update(){
        checkCollisions();
        msgQueue.dispatch();
    }
}
