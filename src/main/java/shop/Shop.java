package shop;

import entities.player.Player;
import main.GUI;
import main.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import shop.shopitems.ShopItemFactory;
import shop.shopitems.ShopItemUI;

public class Shop extends BasicGameState implements UIComponent, ShopListener {

    private static int PADDING = 100;
    private static String CONTINUE_STRING = "Press <Space> to continue.";
    private GameContainer gc;
    private StateBasedGame sbg;

    private ShopItemGenerator itemGenerator;
    private PanelUI panel1, panel2, panel3;
    private ShopItemUI shopItem1, shopItem2, shopItem3;

    private Player player;
    private GUI gui;
    private UnicodeFont shopFont;

    public Shop(Player player, GUI gui){
        this.player = player;
        this.gui = gui;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.gc = gc;
        this.sbg = sbg;
        panel1 = new PanelUI(this, 0, 0, getWidth()/3, getHeight());
        panel2 = new PanelUI(this, getWidth()/3, 0, getWidth()/3, getHeight());
        panel3 = new PanelUI(this, getWidth()-getWidth()/3, 0, getWidth()/3, getHeight());

        itemGenerator = new ShopItemGenerator();

        shopFont = new MyFont(20).getUniFont();
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        shopItem1 = new ShopItemUI(gc, panel1, itemGenerator.getShopItem());
        shopItem1.addListener(this);
        shopItem2 = new ShopItemUI(gc, panel2, itemGenerator.getShopItem());
        shopItem2.addListener(this);
        shopItem3 = new ShopItemUI(gc, panel3, itemGenerator.getShopItem());
        shopItem3.addListener(this);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(gc.getInput().isKeyPressed(gc.getInput().KEY_SPACE))
            sbg.enterState(Main.GAME);
        shopItem1.update(gc, i);
        shopItem2.update(gc, i);
        shopItem3.update(gc, i);
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(new Color(50, 50, 80, 230));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        shopItem1.render(gc, g);
        shopItem2.render(gc, g);
        shopItem3.render(gc, g);

        gui.render(gc, g);
        g.setFont(shopFont);
        g.drawString(CONTINUE_STRING,
                gc.getWidth()/2 - shopFont.getWidth(CONTINUE_STRING)/2,
                gc.getHeight() - shopFont.getHeight(CONTINUE_STRING) - 20);
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
        return 0;
    }

    @Override
    public void itemPurchased(ShopItemUI item) throws SlickException {
        if(player.getScrapMetal() >= item.getPrice().getMetalPrice()) {
            item.applyToPlayer(player);
        }
    }

    @Override
    public int getID() {
        return Main.SHOP;
    }
}
