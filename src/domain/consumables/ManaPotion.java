package domain.consumables;

import app.interfaces.consumables;

public class ManaPotion extends app.gameplayFeatures.Consumables implements consumables {
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
