package shop.shopitems;

import entities.player.Player;
import entities.weapons.WeaponFactory;
import org.newdawn.slick.SlickException;
import shop.Price;

public class ShopItemFactory {

    public ShopItemData speedUpItem(){
        return new ShopItemData("Speed Up", new Price(10)) {
            @Override
            public void applyToPlayer(Player player) {
                player.setScrapMetal(player.getScrapMetal()-getPrice().getMetalPrice());
                player.getStats().setMaxVelocity(player.getStats().getMaxVelocity() + 0.25f);
                player.getStats().setMaxAcceleration(player.getStats().getMaxAcceleration() + 0.004f);
                isUsed = true;
            }
        };
    }

    public ShopItemData advancedWeaponItem(){
        return new ShopItemData("Advanced Weapon", new Price(2)) {
            @Override
            public void applyToPlayer(Player player) throws SlickException {
                player.setScrapMetal(player.getScrapMetal()-getPrice().getMetalPrice());

                WeaponFactory weaponFactory = new WeaponFactory();
                player.setWeapon(weaponFactory.advancedWeapon(player.getPos()));
                isUsed = true;
            }
        };
    }

    public ShopItemData healItem() {
        return new ShopItemData("Heal", new Price(3)) {
            @Override
            public void applyToPlayer(Player player) throws SlickException {
                player.setScrapMetal(player.getScrapMetal()-getPrice().getMetalPrice());
                player.getStats().setCurrentLife(player.getStats().getCurrentLife()+30);
            }
        };
    }

}
