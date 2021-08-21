package main;

import entities.Camera;
import entities.player.Player;
import managers.EntityManager;
import managers.waves.WaveManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {

    private final EntityManager entityManager = EntityManager.getInstance();
    private WaveManager waveManager;
    private Player player;
    private Camera camera;
    private GUI gui;

    public Game(Player player, GUI gui){
        this.player = player;
        this.gui = gui;
    }

    public void init(GameContainer gc, StateBasedGame sbg){
        waveManager = new WaveManager();
        camera = new Camera(player);
        Camera.setScalingFactor(0.6f);

        entityManager.init(gc);
        entityManager.addEntity(player);

        waveManager.init(gc, player.getPos());
        waveManager.start(gc,0);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(gc.getInput().isKeyPressed(gc.getInput().KEY_ESCAPE))
            gc.exit();
        else if(gc.getInput().isKeyPressed(gc.getInput().KEY_P))
            sbg.enterState(Main.SHOP);

        entityManager.update(gc, i);
        waveManager.update(gc, sbg, i);
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Camera.cameraAction(gc, g);
        Camera.cameraActionScale(g);
        entityManager.render(gc, g);
        Camera.cameraCutScale(g);
        Camera.cameraCut(gc, g);

        waveManager.render(gc, g);
        gui.render(gc, g);
    }

    public int getID() {
        return Main.GAME;
    }

    public void reset(GameContainer gc, StateBasedGame sbg) {
        player.reset(gc);
        waveManager.reset(gc);
        entityManager.reset(gc);
        init(gc, sbg);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException{
        //reset(gc, sbg);
    }
}