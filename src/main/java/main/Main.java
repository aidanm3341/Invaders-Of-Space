package main;

import entities.player.Player;
import managers.Fiona;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends StateBasedGame{

    // main.Game state identifiers
    public static final int MAINMENU     = 0;
    public static final int GAME         = 1;
    public static final int SHOP         = 2;

    private Fiona fiona;

    public Main(String name) {
        super(name);
        fiona = new Fiona();
        fiona.sayHello();
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        Player player = new Player(this);
        player.init(gc);
        GUI gui = new GUI(player);
        gui.init(gc);

        Game game = new Game(player, gui);
        this.addState(new Menu(game));
        this.addState(game);
        this.addState(new Shop(player, gui));
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
