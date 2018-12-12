package managers;

import entities.Entity;
import entities.emitters.Bullet;
import entities.enemies.Enemy;
import messaging.Message;
import messaging.MessageQueue;

import java.util.ArrayList;

public class CollisionManager {

    private ArrayList<Entity> entities;
    private MessageQueue msgQueue;

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
        if (entities.get(i).equals(entities.get(j)))
            return true;
        if (entities.get(i) instanceof Bullet && entities.get(j) instanceof Bullet)
            return true;

        if (entities.get(i).collidesWith(entities.get(j))) {
            if (entities.get(i) instanceof Bullet && entities.get(j) instanceof Enemy) {
                Bullet b = (Bullet) entities.get(i);
                msgQueue.add(new Message(entities.get(j), entities.get(i), "damage", String.valueOf(b.getDamage())));
                entities.remove(entities.get(i));
            }
        }

        return false;
    }

    public void update(){
        checkCollisions();
        msgQueue.dispatch();
    }
}
