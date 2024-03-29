package shop.shopitems;

import entities.player.Player;
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

public class ShopItemUI implements UIComponent, ComponentListener{

    private ShopItemData data;
    private UnicodeFont shopItemFont;

    private MouseOverArea mouseOverArea;
    private UIComponent parent;
    private Image image;

    private List<ShopListener> listeners;

    public ShopItemUI(GUIContext container, UIComponent parent, ShopItemData data) throws SlickException {
        this.data = data;
        mouseOverArea = new MouseOverArea(container, null,
                (int) (parent.getX() + parent.getPadding()),
                (int) (parent.getY() + parent.getPadding()),
                (int) (parent.getWidth() - parent.getPadding()*2),
                (int) (parent.getHeight() - parent.getPadding()*2));
        mouseOverArea.addListener(this);
        mouseOverArea.setNormalColor(Color.black);
        mouseOverArea.setMouseOverColor(new Color(20, 20, 20));
        mouseOverArea.setMouseDownColor(new Color(50, 50, 50));

        this.parent = parent;

        listeners = new ArrayList<>();

        shopItemFont = (new MyFont(30)).getUniFont();
        image = data.getImage();
    }

    public void update(GameContainer gc, float delta){
        if(data.isUsed)
            disableInput();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        mouseOverArea.render(gc, g);
        g.setColor(Color.white);
        g.setFont(shopItemFont);
        if(shopItemFont.getWidth(data.getName()) < getWidth()) {
            g.drawString(data.getName(), getX() + getWidth() / 2 - shopItemFont.getWidth(data.getName()) / 2, getY() + parent.getPadding());
        }
        else{
            String str1 = data.getName().substring(0, data.getName().indexOf(' '));
            String str2 = data.getName().substring(data.getName().indexOf(' '));
            g.drawString(str1, getX() + getWidth() / 2 - shopItemFont.getWidth(str1) / 2, getY() + parent.getPadding());
            g.drawString(str2, getX() + getWidth() / 2 - shopItemFont.getWidth(str2) / 2, getY() + parent.getPadding() + shopItemFont.getHeight(str1));
        }
        data.getPrice().render(g, getX()+getWidth()/2-data.getPrice().getWidth()/2, getY()+getHeight()-data.getPrice().getHeight()-parent.getPadding());

        g.drawImage(image, getX() + getWidth()/2 - image.getWidth()/2, getY() + getHeight()/2 - image.getHeight()/2);
    }

    public void applyToPlayer(Player player) throws SlickException {
        data.applyToPlayer(player);
    }

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

    public Price getPrice() {
        return data.getPrice();
    }

    @Override
    public void componentActivated(AbstractComponent abstractComponent) {
        for(ShopListener listener : listeners) {
            try {
                listener.itemPurchased(this);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    protected void disableInput(){
        mouseOverArea.setAcceptingInput(false);
        mouseOverArea.setFocus(false);
        mouseOverArea.setNormalColor(Color.gray);
    }
}
