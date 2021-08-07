package entities.loot;

import entities.Entity;
import entities.EntityType;
import messaging.Message;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Metal extends Entity {

    private final float x, y;
    private Image image;

    public Metal(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        image = new Image("loot/metal.png");
    }

    @Override
    public void update(GameContainer gc, float i) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(image, x, y);
    }

    @Override
    public EntityType getType() {
        return EntityType.LOOT;
    }

    @Override
    public void onMessage(Message msg) {

    }
}
