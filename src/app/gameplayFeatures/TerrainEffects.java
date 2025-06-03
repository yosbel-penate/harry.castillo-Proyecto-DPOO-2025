package app.gameplayFeatures;

import domain.generalClasses.PlayerCharacter;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import app.fastFeatures.LabelManager.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static app.fastFeatures.LabelManager.*;

    public class TerrainEffects {
        public static void applyTerrainEffects(PlayerCharacter[] player, int actualTerrain, int upperActualTerrain, int x, int y){
           if (actualTerrain == 9 && player[0].getX() == x && player[0].getY() == y){
               for (PlayerCharacter p: player){
                   p.setHealth(p.getHealth() - 2);
               }
                player[0].setX(Gameplay.getPreviusX());
                player[0].setY(Gameplay.getPreviusY());
           }
        }


    }