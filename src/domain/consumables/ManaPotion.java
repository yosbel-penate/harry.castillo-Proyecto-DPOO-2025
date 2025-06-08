package domain.consumables;

import app.interfaces.consumables;

public class ManaPotion extends app.gameplayFeatures.Consumables implements consumables {
    private String id="02";
    private int manaAdded=3;
    private int x;
    private int y;

    public ManaPotion(){}


    public int getPointsAdded() {
        return manaAdded;
    }

    public void setPointsAdded(int manaAdded) {
        this.manaAdded=manaAdded;
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
