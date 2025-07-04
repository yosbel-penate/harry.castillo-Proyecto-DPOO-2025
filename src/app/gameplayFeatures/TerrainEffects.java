package app.gameplayFeatures;

import domain.generalClasses.PlayerCharacter;


public class TerrainEffects {



    public static void applyTerrainEffects(PlayerCharacter[] player, int actualTerrain, int x, int y) {


        if (actualTerrain == 9 && player[0].getX() == x && player[0].getY() == y) {
            for (PlayerCharacter p : player) {
                p.setHealth(p.getHealth() - 2);
            }
            player[0].setX(Gameplay.getPreviusX());
            player[0].setY(Gameplay.getPreviusY());
        }
    }


}