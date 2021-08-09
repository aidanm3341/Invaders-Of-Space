package entities.loot;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Metal extends Loot {
    
    private static Image image;

    static {
        try {
            image = new Image("loot/metal.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Metal(float x, float y) {
        super(x, y, image);
    }


    public static Image getImage(){
        return image;
    }
}
