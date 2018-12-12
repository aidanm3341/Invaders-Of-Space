package entities;

import messaging.Message;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Background extends Entity{

    private Image spaceImage;
    private ArrayList<Tile> tiles;

    public Background()
    {

    }

    public void init(GameContainer gc) throws SlickException {
        spaceImage = new Image("spaceSquare.png");
        tiles = new ArrayList<Tile>();
        for(int i=-3; i<5; i++)
        {
            for(int j=-3; j<4; j++)
            {
                tiles.add(new Tile(spaceImage, i*spaceImage.getWidth(), j*spaceImage.getHeight()));
            }
        }
    }

    public void update(GameContainer gc, float i) throws SlickException {

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        for(Tile t : tiles)
            t.render(gc, g);
    }

    public void onMessage(Message msg) {

    }

}
