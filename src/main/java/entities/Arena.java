package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Arena extends Entity{

    public static final int WIDTH = 3360, HEIGHT = 2940;
    private static final float PARALLAX_BG1 = -1f;
    private static final float PARALLAX_BG2 = -0.93f;
    private static final float PARALLAX_BG3 = -0.86f;
    private static final float PARALLAX_BG4 = -0.8f;

    private Image bg1, bg2, bg3, bg4;
    private ArrayList<Tile> tiles1, tiles2, tiles3, tiles4;

    public Arena() { }

    public void init(GameContainer gc){
        try {
            bg1 = new Image("arena/bg1.png");
            bg2 = new Image("arena/bg2.png");
            bg3 = new Image("arena/bg3.png");
            bg4 = new Image("arena/bg4.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        tiles1 = new ArrayList<>();
        for(int i=0; i<15; i++) {
            for(int j=0; j<14; j++) {
                tiles1.add(new Tile(bg1, i* bg1.getWidth(), j* bg1.getHeight()));
            }
        }

        tiles2 = new ArrayList<>();
        for(int i=0; i<15; i++) {
            for(int j=0; j<14; j++) {
                tiles2.add(new Tile(bg2, i* bg2.getWidth(), j* bg2.getHeight()));
            }
        }

        tiles3 = new ArrayList<>();
        for(int i=0; i<15; i++) {
            for(int j=0; j<14; j++) {
                tiles3.add(new Tile(bg3, i* bg3.getWidth(), j* bg3.getHeight()));
            }
        }

        tiles4 = new ArrayList<>();
        for(int i=0; i<15; i++) {
            for(int j=0; j<14; j++) {
                tiles4.add(new Tile(bg4, i* bg4.getWidth(), j* bg4.getHeight()));
            }
        }
    }

    public void update(GameContainer gc, float i) throws SlickException {

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BG1);
        for(Tile t : tiles1)
            t.render(gc, g);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BG1);
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BG2);
        for(Tile t : tiles2)
            t.render(gc, g);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BG2);
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BG3);
        for(Tile t : tiles3)
            t.render(gc, g);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BG3);
        Camera.cameraActionMultipliedBy(gc, g, PARALLAX_BG4);
        for(Tile t : tiles4)
            t.render(gc, g);
        Camera.cameraCutMultipliedBy(gc, g, PARALLAX_BG4);
    }

    @Override
    public EntityType getType() {
        return EntityType.NON_COLLIDING;
    }
}
