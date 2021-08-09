package shop.shopitems;

import entities.Player;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import shop.UIComponent;

public class SpeedUpItem extends ShopItem{
    public SpeedUpItem(GUIContext container, UIComponent parent) throws SlickException {
        super(container, parent);
    }

    public void applyToPlayer(Player player){
        player.setScrapMetal(player.getScrapMetal()-price.getMetalPrice());
    }
}
