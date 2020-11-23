package entities.enemies;

import ai.ChargerAI;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;

public class YellowYak extends Enemy{

    private float[] vertices;

    public YellowYak(float x, float y, Point player) throws SlickException
    {
        super(x, y);
        ai = new ChargerAI(this, player);
        ai.init();
    }

    public void init(GameContainer gc) throws SlickException
    {
        width = 70;
        height = 40;
        life = 60;
        color = Color.yellow;
        ectoSize = 10;

        vertices = new float[]{
                getX(),getY(),
                getX(),getY()+height,
                getX()+width,getY()+height,
                getX()+width,getY()
        };
        body = new Polygon(vertices);

        sheet = new SpriteSheet("enemies/monster3.png", 84, 60);
        image = sheet.getSprite(0, 0);
    }
}
