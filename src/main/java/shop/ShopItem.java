package shop;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class ShopItem implements UIComponent{

    private Font shopItemFont;

    private MouseOverArea mouseOverArea;
    private UIComponent parent;

    private String text;

    public ShopItem(GUIContext container, UIComponent parent) throws SlickException {
        mouseOverArea = new MouseOverArea(container, null,
                (int) (parent.getX() + parent.getPadding()),
                (int) (parent.getY() + parent.getPadding()),
                (int) (parent.getWidth() - parent.getPadding()*2),
                100);
        mouseOverArea.setNormalColor(Color.black);

        this.parent = parent;

        shopItemFont = (new MyFont(30)).getUniFont();
        text = "New Gun";
    }

    public void render(GameContainer gc, Graphics g){
        mouseOverArea.render(gc, g);
        g.setColor(Color.white);
        g.setFont(shopItemFont);
        g.drawString(text, getX()+getPadding(), getY()+getPadding());
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


}
