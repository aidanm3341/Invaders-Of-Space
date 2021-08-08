package entities.loot;

import entities.Entity;
import entities.EntityType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Metal extends Entity {

    private final float SIZE_OF_BOB = 10;
    private final float SPEED_OF_BOB = 0.008f;

    private final float x, y;
    private float actualY;
    private double a;
    private Image image;

    public Metal(float x, float y) throws SlickException {
        image = new Image("loot/metal.png");
        this.x = x - image.getWidth()/2;
        this.y = y - image.getHeight()/2;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

    }

    @Override
    public void update(GameContainer gc, float delta) throws SlickException {
        a += SPEED_OF_BOB;
        if(a > 360)
            a = 0;
        actualY = y + (SIZE_OF_BOB * (float) Math.sin(a));
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(image, x, actualY);
    }

    @Override
    public EntityType getType() {
        return EntityType.LOOT;
    }
}
