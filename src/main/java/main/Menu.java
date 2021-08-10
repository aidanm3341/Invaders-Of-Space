package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState implements ComponentListener {

    private MouseOverArea play;
    private StateBasedGame sbg;
    private GameContainer gc;
    private Game game;

    public int getID() {
        return Main.MAINMENU;
    }

    public Menu(Game game){
        this.game = game;
    }

    public void init(GameContainer gc, StateBasedGame stateBasedGame) throws SlickException {
        Image playImage = new Image("playButton.png");
        play = new MouseOverArea(gc, playImage, gc.getWidth()/2 - playImage.getWidth()/2, gc.getHeight()/2 - playImage.getHeight()/2);
        play.addListener(this);
        this.sbg = stateBasedGame;
        this.gc = gc;
    }

    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        play.render(gc, g);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void componentActivated(AbstractComponent abstractComponent) {
        game.reset(gc, sbg);
        sbg.enterState(Main.GAME);
    }
}
