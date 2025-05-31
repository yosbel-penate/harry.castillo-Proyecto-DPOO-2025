package domain.consumables;

import app.interfaces.consumables;

public class VitalityPotion extends app.gameplayFeatures.Consumables implements consumables {
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
