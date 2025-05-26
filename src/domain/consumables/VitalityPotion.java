package domain.consumables;

import DAO.interfaces.consumables;
import app.gameplayFeatures.Consumables;

public class VitalityPotion extends Consumables implements consumables {
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
