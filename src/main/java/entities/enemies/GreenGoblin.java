package entities.enemies;

import ai.BasicAI;
import ai.ChargerAI;
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
        ai = new BasicAI(this, player);
        ai.init();
    }

    public void init(GameContainer gc) throws SlickException
    {
        width = 40;
        height = 40;
        life = 30;
        color = Color.green;
        ectoSize = 20;

        vertices = new float[]{
                getX(),getY(),
                getX(),getY()+height,
                getX()+width,getY()+height,
                getX()+width,getY()
        };
        body = new Polygon(vertices);

        sheet = new SpriteSheet("enemies/monster1.png", 48, 112);
        image = sheet.getSprite(0, 0);
    }
}
