package domain.consumables;

import app.gameplayFeatures.Consumables;
import app.interfaces.consumables;

public class ShardOfAether extends Consumables implements consumables {
    private String id="03";
    private int attackAdded=2;
    private int x;
    private int y;


    @Override
    public int getPointsAdded() {
        return attackAdded;
    }

    @Override
    public void setPointsAdded(int attackAdded) {
        this.attackAdded=attackAdded;
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
