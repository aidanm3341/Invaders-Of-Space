import entities.player.Player;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends StateBasedGame{

    // Game state identifiers
    public static final int MAINMENU     = 0;
    public static final int GAME         = 1;
    public static final int SHOP         = 2;

    public Main(String name) {
        super(name);
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        Player player = new Player(this);
        player.init(gc);
        this.addState(new Menu());
        this.addState(new Game(player));
        this.addState(new Shop(player));
    }


    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Invaders Of Space"));
            appgc.setDisplayMode(1100, 700, false);//2560 x 1600
            appgc.setShowFPS(false);
            appgc.start();
        }
        catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
