package domain.consumables;

import app.gameplayFeatures.Consumables;
import app.interfaces.consumables;

public class ShardOfAether extends Consumables implements consumables {
    private String id="03";
    private int attackAdded=2;


    @Override
    public int getPointsAdded() {
        return attackAdded;
    }

    @Override
    public void setPointsAdded(int attackAdded) {
        this.attackAdded=attackAdded;
    }
}
