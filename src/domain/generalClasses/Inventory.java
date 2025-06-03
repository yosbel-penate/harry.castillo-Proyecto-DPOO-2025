package domain.generalClasses;

import app.gameplayFeatures.Consumables;
import domain.consumables.ManaPotion;
import domain.consumables.VitalityPotion;

import java.util.ArrayList;

public class Inventory {
    private static boolean drawAtMap;
    private static boolean alreadyCreated = false;
    public static ArrayList<Consumables> createInventory(){
        VitalityPotion vitalityPotionpotion = new VitalityPotion();
        ManaPotion manaPotion=new ManaPotion();
        ArrayList<Consumables> inventory = new ArrayList<>(){};
        inventory.add(vitalityPotionpotion);
        inventory.add(manaPotion);

        alreadyCreated = true;
        return inventory;
    }

    public static boolean isDrawAtMap() {
        return drawAtMap;
    }

    public static void setDrawAtMap(boolean drawAtMap) {
        Inventory.drawAtMap = drawAtMap;
    }


    public static boolean isAlreadyCreated() {
        return alreadyCreated;
    }
}
