import entities.*;
import managers.EntityManager;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame
{
    private EntityManager entityManager;
    private Player player;
    private Camera camera;

    public Game(String gamename)
    {
        super(gamename);
    }


    public void init(GameContainer gc) throws SlickException {
        player = new Player();
        entityManager = new EntityManager(player);
        camera = new Camera(player);
        camera.setScalingFactor(0.5f);

        entityManager.init(gc);
    }


    public void update(GameContainer gc, int i) throws SlickException {
        if(gc.getInput().isKeyPressed(gc.getInput().KEY_ESCAPE))
            gc.exit();
        entityManager.update(gc, i);
    }


    public void render(GameContainer gc, Graphics g) throws SlickException {
        camera.cameraAction(gc, g);

        entityManager.render(gc, g);

        //camera.cameraAction(gc, g);
    }
}