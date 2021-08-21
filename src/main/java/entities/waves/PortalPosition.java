package entities.waves;

import entities.Arena;
import org.newdawn.slick.geom.Point;

public class PortalPosition {

    private static final float PADDING = 100f;

    public static final Point TOP_LEFT = new Point(PADDING, PADDING);
    public static final Point TOP_RIGHT = new Point(Arena.WIDTH - Portal.PORTAL_WIDTH - PADDING, PADDING);
    public static final Point BOTTOM_LEFT = new Point(PADDING, Arena.HEIGHT - Portal.PORTAL_HEIGHT - PADDING);
    public static final Point BOTTOM_RIGHT = new Point(Arena.WIDTH - Portal.PORTAL_WIDTH - PADDING, Arena.HEIGHT - Portal.PORTAL_HEIGHT - PADDING);
    public static final Point MIDDLE = new Point(Arena.WIDTH/2 - Portal.PORTAL_WIDTH/2, Arena.HEIGHT/2 - Portal.PORTAL_HEIGHT/2);
}
