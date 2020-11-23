package entities.enemies;

import entities.Player;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class EnemyFactory {

    private static Point player;

    public EnemyFactory(Point player)
    {
        this.player = player;
    }

    public static Enemy getEnemy(EnemyType type, float x, float y) throws SlickException
    {
        switch(type){
            case GREEN_GOBLIN:
                return new GreenGoblin(x, y, player);
            case PURPLE_PIE:
                return new PurplePie(x, y, player);
            case YELLOW_YAK:
                return new YellowYak(x, y, player);

            default:
                return new GreenGoblin(x, y, player);
        }
    }
}
