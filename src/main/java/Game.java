import entities.Camera;
import entities.player.Player;
import managers.EntityManager;
import managers.WaveManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import shop.Shop;

public class Game extends BasicGameState {

    private final EntityManager entityManager = EntityManager.getInstance();
    private WaveManager waveManager;
    private Player player;
    private Camera camera;
    private GUI gui;
    private Shop shop;

    private boolean shopView;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Player(sbg);
        player.init(gc);
        waveManager = new WaveManager();
        camera = new Camera(player);
        Camera.setScalingFactor(0.6f);
        shopView = false;
        shop = new Shop(player);
        shop.init(gc, sbg);

        entityManager.init(gc);
        entityManager.addEntity(player);

        gui = new GUI(player);
        gui.init(gc);

        waveManager.init(gc, player.getPos());
        waveManager.start(gc,0);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(gc.getInput().isKeyPressed(gc.getInput().KEY_ESCAPE))
            gc.exit();
        else if(gc.getInput().isKeyPressed(gc.getInput().KEY_P))
            shopView = !shopView;

        if(!shopView) {
            entityManager.update(gc, i);
            waveManager.update(gc, i);
        }
        else{
            shop.update(gc, sbg, i);
        }
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Camera.cameraAction(gc, g);
        entityManager.render(gc, g);
        Camera.cameraCut(gc, g);

        waveManager.render(gc, g);
        gui.render(gc, g);

        if(shopView){
            shop.render(gc, sbg, g);
        }
    }

    public int getID() {
        return Main.GAME;
    }

    private void reset(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player.reset(gc);
        waveManager.reset(gc);
        entityManager.reset(gc);
        shop.reset(gc, sbg);
        init(gc, sbg);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException{
        reset(gc, sbg);
    }
}