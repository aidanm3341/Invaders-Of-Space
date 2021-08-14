package shop;

import shop.shopitems.ShopItemData;
import shop.shopitems.ShopItemFactory;
import util.WeightedRandomBag;

import java.util.ArrayList;
import java.util.List;

public class ShopItemGenerator {

    private final ShopItemFactory shopItemFactory;
    private final WeightedRandomBag<ITEM_ID> itemIDs;

    public ShopItemGenerator(){
        shopItemFactory = new ShopItemFactory();

        itemIDs = new WeightedRandomBag<>();
        itemIDs.addEntry(ITEM_ID.SPEED_UP, 5);
        itemIDs.addEntry(ITEM_ID.ADVANCED_WEAPON, 1);
        itemIDs.addEntry(ITEM_ID.HEAL, 8);
        itemIDs.addEntry(ITEM_ID.MAX_HEALTH_UP, 5);
        itemIDs.addEntry(ITEM_ID.DAMAGE_UP, 8);
    }

    public ShopItemData getShopItem(){
        return IDtoItem(itemIDs.getRandom());
    }

    public List<ShopItemData> getXShopItems(int x){
        List<ShopItemData> data = new ArrayList<>();
        for(int i=0; i<x; i++)
            data.add(IDtoItem(itemIDs.getRandom()));
        return data;
    }


    private enum ITEM_ID {
        SPEED_UP, ADVANCED_WEAPON, HEAL, MAX_HEALTH_UP, DAMAGE_UP
    }

    private ShopItemData IDtoItem(ITEM_ID id){
        switch(id){
            case SPEED_UP:
                return shopItemFactory.speedUpItem();
            case ADVANCED_WEAPON:
                return shopItemFactory.advancedWeaponItem();
            case HEAL:
                return shopItemFactory.healItem();
            case MAX_HEALTH_UP:
                return shopItemFactory.maxHealthItem();
            case DAMAGE_UP:
                return shopItemFactory.damageUpItem();
            default:
                return null;
        }
    }
}
