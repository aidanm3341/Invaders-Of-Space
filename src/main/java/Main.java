import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Game("Invaders Of Space"));
            appgc.setDisplayMode(1100, 700, false);//2560 x 1600
            appgc.setShowFPS(false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
