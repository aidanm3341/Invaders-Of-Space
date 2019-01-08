import entities.*;
import managers.EntityManager;
import managers.WaveManager;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState
{
    private EntityManager entityManager;
    private WaveManager waveManager;
    private Player player;
    private Camera camera;
    private GUI gui;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Player(sbg);
        entityManager = new EntityManager(player);
        waveManager = new WaveManager();
        camera = new Camera(player);
        camera.setScalingFactor(0.6f);

        entityManager.init(gc);
        waveManager.init(gc);

        waveManager.start(gc,0);
        gui = new GUI(player);
        gui.init(gc);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(gc.getInput().isKeyPressed(gc.getInput().KEY_ESCAPE))
            gc.exit();
        entityManager.update(gc, i);
        waveManager.update(gc, i);
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Camera.cameraAction(gc, g);
        entityManager.render(gc, g);
        Camera.cameraCut(gc, g);

        waveManager.render(gc, g);
        gui.render(gc, g);
    }

    public int getID() {
        return Main.GAME;
    }

    private void reset(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        player.reset(gc);
        waveManager.reset(gc);
        entityManager.reset(gc);
        init(gc, sbg);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException{
        reset(gc, sbg);
    }
}