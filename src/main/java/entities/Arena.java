package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Arena extends Entity{

    public static final int WIDTH = 3360, HEIGHT = 2940;

    private static final float PARALLAX_BACKGROUND_NEBULA = -1.5f;
    private static final float PARALLAX_BACKGROUND_PLANET = -1.3f;
    private static final float PARALLAX_BACKGROUND_PLANET2 = -1.29f;
    private static final float PARALLAX_BACKGROUND_STARS1 = -1.36f;
    private static final float PARALLAX_BACKGROUND_STARS2 = -1.32f;
    private static final float PARALLAX_BACKGROUND_STARS3 = -1.4f;

    private Image bgNebula, bgPlanet, bgPlanet2, bgStars1, bgStars2, bgStars3;

    public Arena() { }

    public void init(GameContainer gc){
        try {
            bgNebula = new Image("arena/background_nebula.png");
            bgPlanet = new Image("arena/background_planet.png");
            bgPlanet2 = new Image("arena/background_planet2.png");
            bgStars1 = new Image("arena/background_stars1.png");
            bgStars2 = new Image("arena/background_stars2.png");
            bgStars3 = new Image("arena/background_stars3.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void update(GameContainer gc, float i) throws SlickException {

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BACKGROUND_NEBULA);
            g.drawImage(bgNebula, 0, 0);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BACKGROUND_NEBULA);
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BACKGROUND_STARS1);
            g.drawImage(bgStars1, 0, 0);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BACKGROUND_STARS1);
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BACKGROUND_STARS2);
            g.drawImage(bgStars2, 0, 0);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BACKGROUND_STARS2);
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BACKGROUND_STARS3);
            g.drawImage(bgStars3, 0, 0);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BACKGROUND_STARS3);
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BACKGROUND_PLANET);
            g.drawImage(bgPlanet, -200, -100);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BACKGROUND_PLANET);
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BACKGROUND_PLANET2);
            g.drawImage(bgPlanet2, -200, -100);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BACKGROUND_PLANET2);
    }

    @Override
    public EntityType getType() {
        return EntityType.NON_COLLIDING;
    }
}
