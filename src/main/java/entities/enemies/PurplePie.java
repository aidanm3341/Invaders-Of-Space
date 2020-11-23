package entities.enemies;

import ai.AimerAI;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;

public class PurplePie extends Enemy{

    private float[] vertices;

    public PurplePie(float x, float y, Point player) throws SlickException
    {
        super(x, y);
        ai = new AimerAI(this, player);
        ai.init();
    }

    public void init(GameContainer gc) throws SlickException
    {
        width = 100;
        height = 70;
        life = 80;
        color = new Color(153, 0 ,153);
        ectoSize = 35;

        vertices = new float[]{
                getX(),getY(),
                getX(),getY()+height,
                getX()+width,getY()+height,
                getX()+width,getY()
        };
        body = new Polygon(vertices);

        sheet = new SpriteSheet("enemies/monster2.png", 123, 115);
        image = sheet.getSprite(0, 0);
    }
}
