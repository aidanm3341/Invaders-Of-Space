package entities.enemies;

import entities.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;

public class GreenGoblin extends Enemy
{
    private float[] vertices;

    public GreenGoblin(float x, float y, Player player) throws SlickException
    {
        super(x, y, player);
    }

    public void init(GameContainer gc) throws SlickException {
        width = 40;
        height = 40;
        life = 30;
        color = Color.green;
        ectoSize = 20;

        vertices = new float[]{
                x,y,
                x,y+height,
                x+width,y+height,
                x+width,y
        };
        body = new Polygon(vertices);

        sheet = new SpriteSheet("enemies/monster1.png", 48, 112);
        image = sheet.getSprite(0, 0);
    }
}
