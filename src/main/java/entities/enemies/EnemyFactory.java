package entities.enemies;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class EnemyFactory {

    private static final List<Enemy> greenGoblins = new ArrayList<>();

    public static Enemy createEnemy(EnemyType type, Vector2f target, float x, float y)
    {
        switch(type){
            case PURPLE_PIE:
                return new PurplePie(x, y, target);
            case YELLOW_YAK:
                return new YellowYak(x, y, target);

            case GREEN_GOBLIN:
            default:
                Enemy e = new GreenGoblin(x, y, target, greenGoblins);
                greenGoblins.add(e);
                return e;
        }
    }
}
