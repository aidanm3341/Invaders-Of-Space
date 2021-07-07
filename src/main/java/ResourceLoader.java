import org.newdawn.slick.Image;

import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    private static Map<Integer, Image> images;
    private static boolean isLoaded = false;

    public static Image getImage(int index){
        if(!isLoaded) loadImages();
        return images.get(index);
    }

    private static void loadImages(){
        images = new HashMap<Integer, Image>();
    }
}
