package app.gameplayFeatures;

import app.Maps;
import app.fastFeatures.AudioPlayer;
import domain.generalClasses.PlayerCharacter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileMap {
    private static final int initialColPos = 64;
    private static final int finalColPos = 544;
    private static final int initialEvenRowPos = 64;
    private static final int finalEvenRowPos = 640;
    private static final int initialOddRowPos = 32;
    private static final int finalOddRowPos = 672;
    private static final int nextCol = 48;
    private static final int nextRow = 64;
    private static final int initialPos = 64;
    private static final int realocate = 20;
    private static final int realocateCol = 8;
    private static final int realocateRow = 8;


    private static GraphicsContext graphics = Gameplay.getGraphics();
    private static PlayerCharacter[] player;
    private static boolean returnToNormal = false;
    private static int[][] matrix;
    private static int[][] upperThingsMatrix;
    private static String[] images = {
            "borderlessGrass.png",
            "borderGrass1.png",
            "borderGrass2.png",
            "borderSand1.png",
            "borderSand2.png",
            "sand.png",
            "borderWater1.png",
            "borderWater2.png",
            "water.png"};
    private static String[] upperThingsImages = {
            "nothing.png",
            "ruins.png",
            "tree.png",
            "mountains.png"};

    


    public static void drawTutorialMap(GraphicsContext graphics) {
        matrix = Maps.getTutorialMap();
        int contadorFila = 0;

        for (int col = initialColPos; col <= finalColPos; col += nextCol) {
            int terrain;
            int contadorColumna = 0;
            if (contadorFila % 2 == 0) {
                for (int row = initialEvenRowPos; row <= finalEvenRowPos; row += nextRow) {
                    terrain = matrix[contadorFila][contadorColumna];
                    graphics.drawImage(new Image(images[terrain]), col, row);
                    contadorColumna++;
                }
            } else {
                for (int row = initialOddRowPos; row <= finalOddRowPos; row += nextRow) {
                    terrain = matrix[contadorFila][contadorColumna];
                    graphics.drawImage(new Image(images[terrain]), col, row);
                    contadorColumna++;
                }
            }
            contadorFila++;
        }

    }


    public static void drawCampaignMap(GraphicsContext graphics) {
        matrix = Maps.getFirstMap();
        upperThingsMatrix = Maps.getFirstMapUpperThingsMatrix();
        int colCounter = 0;

        for (int col = initialPos; col <= finalColPos; col += nextCol) {
            int terrain;
            int upper;
            int rowCounter = 0;
            if (colCounter % 2 == 0) {
                for (int evenRow = initialEvenRowPos; evenRow <= finalEvenRowPos; evenRow += nextRow) {
                    terrain = matrix[colCounter][rowCounter];
                    upper = upperThingsMatrix[colCounter][rowCounter];
                    graphics.drawImage(new Image(images[terrain]), col, evenRow);
                    checkTerrains(terrain, upper, col, evenRow, colCounter, rowCounter);
                    if (upper == 3) {
                        evenRow = evenRow - realocate;
                        returnToNormal = true;
                    }
                    if (returnToNormal) {
                        evenRow = evenRow + realocate;
                        returnToNormal = false;
                    }
                    graphics.drawImage(new Image(upperThingsImages[upper]), col + realocateCol, evenRow + realocateRow);
                    rowCounter++;
                }
                // Impar.
                // Par.
            } else {
                for (int oddRow = initialOddRowPos; oddRow < finalOddRowPos; oddRow += nextRow) {
                    terrain = matrix[colCounter][rowCounter];
                    upper = upperThingsMatrix[colCounter][rowCounter];
                    graphics.drawImage(new Image(images[terrain]), col, oddRow);
                    checkTerrains(terrain, upper, col, oddRow, colCounter, rowCounter);
                    if (upper == 3) {
                        oddRow = oddRow + realocate;
                        returnToNormal = true;
                    }
                    if (returnToNormal) {
                        oddRow = oddRow - realocate;
                        returnToNormal = false;
                    }
                    graphics.drawImage(new Image(upperThingsImages[upper]), col + realocateCol, oddRow + realocateRow);
                    rowCounter++;
                }
            }
            colCounter++;
            AudioPlayer.playTileMap();}
    }

    private static void checkTerrains(int actualTerrain, int upperActualTerrain, int x, int y, int col, int fil) {
        if (actualTerrain >= 0 && actualTerrain <=2 && player[0].getX() == x && player[0].getY() == y){
            graphics.drawImage(new Image(images[0]), 705, 350);
        }
        if (upperActualTerrain == 2 && player[0].getX() == x && player[0].getY() == y){
            graphics.drawImage(new Image(upperThingsImages[upperActualTerrain]), 713, 430);
        }

        if (actualTerrain >= 3 && actualTerrain <=5 && player[0].getX() == x && player[0].getY() == y){
            graphics.drawImage(new Image(images[actualTerrain]), 705, 350);
        }


        if(actualTerrain >= 6 && actualTerrain <=8){

           if (player[0].getX() == x && player[0].getY() == y){
               player[0].setX(Gameplay.getPreviusX());
               player[0].setY(Gameplay.getPreviusY());
               Gameplay.setActionPoints(Gameplay.getActionPoints()+1);
           }
        }
           if(upperActualTerrain == 3 && player[0].getX() == x && player[0].getY() == y){
               graphics.drawImage(new Image(upperThingsImages[upperActualTerrain]), 713, 430);

           }
        if (upperActualTerrain == 1 && player[0].getX() == x && player[0].getY() == y){
            graphics.drawImage(new Image(upperThingsImages[upperActualTerrain]), 713, 430);
        }

      }
    public static void setPlayer(PlayerCharacter[] player){
        TileMap.player = player;}
    }


