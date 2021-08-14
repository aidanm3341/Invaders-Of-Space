package shop.shopitems;

import entities.player.Player;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import shop.Price;

public abstract class ShopItemData {
    private String name;
    private Price price;
    protected boolean isUsed;

    public ShopItemData(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public abstract void applyToPlayer(Player player) throws SlickException;

    public abstract Image getImage() throws SlickException;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
