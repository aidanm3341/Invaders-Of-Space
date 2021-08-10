package main;

import entities.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import shop.PanelUI;
import shop.ShopListener;
import shop.UIComponent;
import shop.shopitems.ShopItemFactory;
import shop.shopitems.ShopItemUI;

public class Shop extends BasicGameState implements UIComponent, ShopListener {

    private static int PADDING = 100;
    private GameContainer gc;
    private StateBasedGame sbg;

    private ShopItemFactory itemFactory;
    private PanelUI panel1, panel2, panel3;
    private ShopItemUI shopItem1, shopItem2, shopItem3;

    private Player player;
    private GUI gui;

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
        itemFactory = new ShopItemFactory();
        shopItem1 = new ShopItemUI(gc, panel1, itemFactory.speedUpItem());
        shopItem1.addListener(this);
        shopItem2 = new ShopItemUI(gc, panel2, itemFactory.advancedWeaponItem());
        shopItem2.addListener(this);
        shopItem3 = new ShopItemUI(gc, panel3, itemFactory.healItem());
        shopItem3.addListener(this);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(gc.getInput().isKeyPressed(gc.getInput().KEY_P))
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
