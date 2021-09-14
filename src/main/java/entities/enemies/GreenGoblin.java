package entities.enemies;

import ai.AI;
import ai.BasicAI;
import ai.BasicGroupingAI;
import ai.NewBasicAI;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

import java.util.List;

public class GreenGoblin extends Enemy
{
    private float[] vertices;

    public GreenGoblin(float x, float y, Vector2f player, List<Enemy> enemies) {
        super(x, y);
        //ai = new NewBasicAI(this, player);
        this.ai = new BasicGroupingAI(this, enemies, player);
        ai.init();
    }

    public void init(GameContainer gc) {
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

        try {
            sheet = new SpriteSheet("enemies/monster1.png", 48, 112);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        image = sheet.getSprite(0, 0);
    }
}
