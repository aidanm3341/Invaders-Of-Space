package shop.shopitems;

import entities.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import shop.MyFont;
import shop.Price;
import shop.ShopListener;
import shop.UIComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class ShopItem implements UIComponent, ComponentListener{

    private UnicodeFont shopItemFont;

    private MouseOverArea mouseOverArea;
    private UIComponent parent;

    private List<ShopListener> listeners;

    private String text;
    protected Price price;

    public ShopItem(GUIContext container, UIComponent parent) throws SlickException {
        mouseOverArea = new MouseOverArea(container, null,
                (int) (parent.getX() + parent.getPadding()),
                (int) (parent.getY() + parent.getPadding()),
                (int) (parent.getWidth() - parent.getPadding()*2),
                100);
        mouseOverArea.addListener(this);
        mouseOverArea.setNormalColor(Color.black);
        mouseOverArea.setMouseOverColor(new Color(20, 20, 20));
        mouseOverArea.setMouseDownColor(new Color(50, 50, 50));

        this.parent = parent;

        listeners = new ArrayList<>();

        shopItemFont = (new MyFont(30)).getUniFont();
        text = "Magic Space Cannon";
        price = new Price(10);
    }

    public void render(GameContainer gc, Graphics g){
        mouseOverArea.render(gc, g);
        g.setColor(Color.white);
        g.setFont(shopItemFont);
        g.drawString(text, getX()+getPadding(), getY() + getHeight()/2 - shopItemFont.getHeight(text)/2);
        price.render(g, getX()+getWidth()-price.getWidth() - getPadding(), getY() + getHeight()/2 - price.getHeight()/2);
    }

    public abstract void applyToPlayer(Player player);

    @Override
    public void setParent(UIComponent parent) {
        this.parent = parent;
    }

    @Override
    public float getX() {
        return mouseOverArea.getX();
    }

    @Override
    public float getY() {
        return mouseOverArea.getY();
    }

    @Override
    public float getWidth() {
        return mouseOverArea.getWidth();
    }

    @Override
    public float getHeight() {
        return mouseOverArea.getHeight();
    }

    @Override
    public float getPadding() {
        return 10;
    }

    public void addListener(ShopListener listener){
        listeners.add(listener);
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        for(ShopListener listener : listeners)
            listener.itemPurchased(this);
    }
}
