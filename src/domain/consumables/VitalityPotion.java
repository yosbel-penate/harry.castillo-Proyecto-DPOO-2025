package domain.consumables;

import app.interfaces.Consumables;

public class VitalityPotion extends app.gameplayFeatures.Consumables implements Consumables {
    private String id = "01";
    private int healthAdded = 3;

    public VitalityPotion() {
    }


    public int getPointsAdded() {
        return healthAdded;
    }

    public void setPointsAdded(int healthAdded) {
        this.healthAdded=healthAdded;
    }
}
