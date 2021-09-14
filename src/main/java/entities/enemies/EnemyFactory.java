package entities.enemies;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

public class EnemyFactory {

    public static Enemy createEnemy(EnemyType type, Vector2f target, float x, float y)
    {
        switch(type){
            case GREEN_GOBLIN:
                return new GreenGoblin(x, y, target);
            case PURPLE_PIE:
                return new PurplePie(x, y, target);
            case YELLOW_YAK:
                return new YellowYak(x, y, target);

            default:
                return new GreenGoblin(x, y, target);
        }
    }
}
