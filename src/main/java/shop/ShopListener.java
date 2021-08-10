package shop;

import org.newdawn.slick.SlickException;
import shop.shopitems.ShopItemUI;

public interface ShopListener {
    void itemPurchased(ShopItemUI item) throws SlickException;
}
