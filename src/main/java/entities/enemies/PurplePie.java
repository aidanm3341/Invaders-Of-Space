package entities.enemies;

import entities.Player;
import messaging.Message;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;

public class PurplePie extends Enemy{

    private float[] vertices;

    public PurplePie(float x, float y, Player player) throws SlickException
    {
        super(x, y, player);
    }

    public void init(GameContainer gc) throws SlickException
    {
        width = 100;
        height = 70;
        life = 80;
        color = new Color(153, 0 ,153);
        ectoSize = 35;

        vertices = new float[]{
                x,y,
                x,y+height,
                x+width,y+height,
                x+width,y
        };
        body = new Polygon(vertices);

        sheet = new SpriteSheet("monster2.png", 123, 115);
        image = sheet.getSprite(0, 0);
    }
}
