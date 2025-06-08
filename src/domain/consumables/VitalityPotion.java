package domain.consumables;

import app.interfaces.consumables;

public class VitalityPotion extends app.gameplayFeatures.Consumables implements consumables {
    private String id = "01";
    private int healthAdded = 3;
    private int x;
    private int y;


    public VitalityPotion() {
    }

    public int getPointsAdded() {
        return healthAdded;
    }

    public void setPointsAdded(int healthAdded) {
        this.healthAdded=healthAdded;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
