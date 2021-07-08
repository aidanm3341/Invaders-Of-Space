package entities;

import messaging.Message;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Arena extends Entity{

    public static final int WIDTH = 3360, HEIGHT = 2940;

    private Image spaceImage;
    private ArrayList<Tile> tiles;

    public Arena()
    {

    }

    public void init(GameContainer gc) throws SlickException {
        spaceImage = new Image("spaceSquare.png");
        tiles = new ArrayList<Tile>();
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<7; j++)
            {
                tiles.add(new Tile(spaceImage, i*spaceImage.getWidth(), j*spaceImage.getHeight()));
            }
        }

        //System.out.println(spaceImage.getWidth()*8 + " " + spaceImage.getHeight()*7);
    }

    public void update(GameContainer gc, float i) throws SlickException {

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        for(Tile t : tiles)
            t.render(gc, g);
    }

    public void onMessage(Message msg) {

    }

    @Override
    public EntityType getType() {
        return EntityType.NON_COLLIDING;
    }
}
