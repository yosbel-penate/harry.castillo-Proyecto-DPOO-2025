package domain.dangers;

import domain.generalClasses.PlayerCharacter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

import static app.fastFeatures.PublicVariables.*;
import static app.fastFeatures.PublicVariables.diagonalDown;



public class Ether_Storm {
     public static int[] xPosition = new int[3];
     public static int[] yPosition = new int[3];;
     private int nextXPosition;
     private int nextYPosition;
     private static int causeDamage=2;
     private String imageName;
     private String nameDanger;
     private static boolean isDamageCaused;
     private static boolean isConditionReady=false;

     public Ether_Storm(){
     }
     public Ether_Storm(int xPosition, int yPosition){
     }
     public static void randomPosition() {
          for (int i = 0; i<3;i++){
               int xPos = new Random().nextInt(1, 11);
               int yPos = new Random().nextInt(1, 10);
               int firstCol = 64;
               int evenPeak = 32;

               if (xPos % 2 == 0) {
                    yPos = (yPos * down) + evenPeak;
               } else {
                    yPos = yPos * down;
               }

               xPos = firstCol + ((xPos-1) * right);

               xPosition[i]=xPos;
               yPosition[i]=yPos;

          }
     }

     public static void drawStorm(GraphicsContext graphics){
          graphics.drawImage(new Image("storm.png"),xPosition[0],yPosition[0]);
          graphics.drawImage(new Image("storm.png"),xPosition[1],yPosition[1]);
          graphics.drawImage(new Image("storm.png"),xPosition[2],yPosition[2]);

     }
     public static void moveEtherStorm(){
        for(int i=0;i<3;i++){
             int move=new Random().nextInt(1,6);
             switch (move){
                  case 1:
                       if (yPosition[i] == upLimitEven) {
                            break;
                       }
                       if (xPosition[i] == leftLimit) {
                            break;
                       }
                       xPosition[i] += left;
                       yPosition[i] += diagonalUp;
                       break;
                  case 2:
                       if (yPosition[i] == upLimitOdd) {
                            break;
                       }
                       if (xPosition[i] == upLimitEven) {
                            break;
                       }
                       yPosition[i]+=up;
                       break;
                  case 3:
                       if (yPosition[i] == upLimitEven) {
                            break;
                       }
                       if (xPosition[i] == rightLimit) {
                            break;
                       }
                       xPosition[i] += right;
                       yPosition[i] += diagonalUp;
                       break;
                  case 4:
                       if (yPosition[i]  == downLimitOdd) {
                            break;
                       }
                       if (xPosition[i] == leftLimit ) {
                            break;
                       }
                       xPosition[i] += left;
                       yPosition[i] += diagonalDown;
                       break;
                  case 5:
                       if (yPosition[i] == downLimitOdd) {
                            break;
                       }
                       if (xPosition[i] == downLimitEven) {
                            break;
                       }
                       yPosition[i] += down;
                       break;
                  case 6:
                       if (yPosition[i] == downLimitOdd) {
                            break;
                       }
                       if (xPosition[i] == rightLimit) {
                            break;
                       }
                       xPosition[i] += right;
                       yPosition[i] += diagonalDown;
                       break;
             }
        }
     }
     public static void damageStorm(PlayerCharacter[] player){
                    for (int j=0;j<5;j++){
                         player[j].setHealth(player[j].getHealth()-2);
                    }
     }



     public static int getCauseDamage(){
          return causeDamage;
     }
     public static boolean getIsDamageCaused(){
          return isDamageCaused;
     }
     public static boolean getIsConditionReady(){
          return isConditionReady;
     }








}
