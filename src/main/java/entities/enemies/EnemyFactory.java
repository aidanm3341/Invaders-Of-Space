package entities.enemies;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class EnemyFactory {

    public static Enemy createEnemy(EnemyType type, Point target, float x, float y) throws SlickException
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
