package shop;

import entities.loot.Metal;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

public class Price {
    private UnicodeFont priceFont;
    private int metalPrice;

    public Price(int metalPrice) throws SlickException {
        this.metalPrice = metalPrice;

        priceFont = (new MyFont(15)).getUniFont();
    }

    public void render(Graphics g, float x, float y){
        g.setFont(priceFont);
        g.drawString(String.valueOf(metalPrice), x, y);
        g.drawImage(Metal.getImage(), x + priceFont.getWidth(String.valueOf(metalPrice)) + 5, y);
    }

    public float getWidth(){
        return (priceFont.getWidth(String.valueOf(metalPrice)) + 5) + Metal.getImage().getWidth();
    }

    public float getHeight(){
        return Metal.getImage().getHeight();
    }

    public int getMetalPrice(){
        return metalPrice;
    }
}
