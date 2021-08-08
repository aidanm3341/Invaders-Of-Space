package entities.loot;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Metal extends Loot {
    public Metal(float x, float y) throws SlickException {
        super(x, y, new Image("loot/metal.png"));
    }
}
