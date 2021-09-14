package entities.enemies;

import ai.ChargerAI;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

public class YellowYak extends Enemy{

    private float[] vertices;

    public YellowYak(float x, float y, Vector2f player) {
        super(x, y);
        ai = new ChargerAI(this, player);
        ai.init();
    }

    public void init(GameContainer gc) {
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

        try {
            sheet = new SpriteSheet("enemies/monster3.png", 84, 60);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        image = sheet.getSprite(0, 0);
    }
}
