package domain.consumables;

import DAO.interfaces.consumables;
import app.gameplayFeatures.Consumables;

public class ManaPotion extends Consumables implements consumables {
    private String id="02";
    private int manaAdded=3;

    public ManaPotion(){}


    public int getPointsAdded() {
        return manaAdded;
    }

    public void setPointsAdded(int manaAdded) {
        this.manaAdded=manaAdded;
    }
}
