package entities.enemies;

import entities.Player;
import messaging.Message;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;

public class YellowYak extends Enemy{

    private float[] vertices;

    public YellowYak(float x, float y, Player player) throws SlickException
    {
        super(x, y, player);
    }

    public void init(GameContainer gc) throws SlickException
    {
        width = 70;
        height = 40;
        life = 60;
        color = Color.yellow;
        ectoSize = 10;

        vertices = new float[]{
                x,y,
                x,y+height,
                x+width,y+height,
                x+width,y
        };
        body = new Polygon(vertices);

        sheet = new SpriteSheet("monster3.png", 84, 60);
        image = sheet.getSprite(0, 0);
    }
}
