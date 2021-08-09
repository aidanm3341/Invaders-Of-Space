package shop;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Shop {

    private static int PADDING = 100;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(new Color(0, 0, 100, 200));
        g.fillRect(PADDING, PADDING, gc.getWidth()-PADDING*2, gc.getHeight()-PADDING*2);
    }

    public void reset(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.init(gc, sbg);
    }
}
