import entities.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import shop.ShopListener;
import shop.UIComponent;
import shop.shopitems.ShopItem;
import shop.shopitems.SpeedUpItem;

public class Shop extends BasicGameState implements UIComponent, ShopListener {

    private static int PADDING = 100;
    private GameContainer gc;
    private StateBasedGame sbg;

    private ShopItem shopItem;

    private Player player;

    public Shop(Player player){
        this.player = player;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;
        this.sbg = sbg;
        shopItem = new SpeedUpItem(gc, this);
        shopItem.addListener(this);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(new Color(50, 50, 80, 230));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        shopItem.render(gc, g);
    }

    public void reset(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.init(gc, sbg);
    }

    @Override
    public void setParent(UIComponent parent) {

    }

    @Override
    public float getX() {
        return PADDING;
    }

    @Override
    public float getY() {
        return PADDING;
    }

    @Override
    public float getWidth() {
        return gc.getWidth()-PADDING*2;
    }

    @Override
    public float getHeight() {
        return gc.getHeight()-PADDING*2;
    }

    @Override
    public float getPadding(){
        return 20;
    }

    @Override
    public void itemPurchased(ShopItem item) {
        item.applyToPlayer(player);
        sbg.enterState(Main.GAME);
    }

    @Override
    public int getID() {
        return Main.SHOP;
    }
}
