package domain.generalClasses;

import app.gameplayFeatures.Consumables;
import app.gameplayFeatures.Gameplay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

import static app.fastFeatures.PublicVariables.*;

// Temporizador del rango.


public class PlayerCharacter{
    private static boolean havesMana;
    private int x;
    private int y;
    private int attack;
    private int health;
    private String characterName;
    private String imageName;
    private String closestImageName;
    private EnemyCharacter enemy[];
    private static boolean collideEnemy;


    public void range(GraphicsContext graphics, long time) {
        Image rango = new Image("rangeTerrain.png");
        if (!Gameplay.isActivateRange() || time % 2 != 0) {
            return;
        }

        boolean actived = false;
        boolean corner = false;

        if (x == xLeftDownCornerLimit && y == yLeftDownCornerLimit) {
            graphics.drawImage(rango, x, y + left);
            graphics.drawImage(rango, x + right, y + diagonalUp);
            corner = true;
        } else if (x == xLeftUpCornerLimit && y == yLeftUpCornerLimit) {
            graphics.drawImage(rango, x, y + down);
            graphics.drawImage(rango, x + right, y + diagonalUp);
            graphics.drawImage(rango, x + right, y + diagonalDown);
            corner = true;
        } else if (x == xRightDownCornerLimit && y == yRightDownCornerLimit) {
            graphics.drawImage(rango, x, y + up);
            graphics.drawImage(rango, x + right, y + diagonalUp);
            corner = true;
        } else if (x == xRightUpCornerLimit && y == yRightUpCornerLimit) {
            graphics.drawImage(rango, x, y + down);
            graphics.drawImage(rango, x + left, y + diagonalUp);
            graphics.drawImage(rango, x + left, y + diagonalDown);
            corner = true;
        }

        if (corner) {
        } else {
            if (x == upLimit) {
                graphics.drawImage(rango, x, y + up);
                graphics.drawImage(rango, x, y + down);
                graphics.drawImage(rango, x + right, y + diagonalUp);
                graphics.drawImage(rango, x + right, y + diagonalDown);
                actived = true;
            } else if (x == downLimit) {
                graphics.drawImage(rango, x, y + up);
                graphics.drawImage(rango, x, y + down);
                graphics.drawImage(rango, x + left, y + diagonalUp);
                graphics.drawImage(rango, x + left, y + diagonalDown);
                actived = true;
            }

            for (int col = 1; col <= 11; col++) {
                if (col % 2 == 0) {
                    for (int posEven = initialEvenRow; posEven <= finalEvenRow; posEven += nextRowEven) {
                        if (y == upLimitEven && x == posEven) {
                            graphics.drawImage(rango, x, y + down);
                            graphics.drawImage(rango, x + right, y + diagonalDown);
                            graphics.drawImage(rango, x + left, y + diagonalDown);
                            actived = true;
                        }
                        if (y == downLimitEven && x == posEven) {
                            graphics.drawImage(rango, x, y + up);
                            graphics.drawImage(rango, x + right, y + diagonalUp);
                            graphics.drawImage(rango, x + left, y + diagonalUp);
                            graphics.drawImage(rango, x + right, y + diagonalDown);
                            graphics.drawImage(rango, x + left, y + diagonalDown);
                            actived = true;
                        }
                    }
                } else {
                    for (int posOdd = initialOddRow; posOdd <= finalOddRow; posOdd += nextRowOdd) {
                        if (y == upLimitOdd && x == posOdd) {
                            graphics.drawImage(rango, x, y + down);
                            graphics.drawImage(rango, x + right, y + diagonalDown);
                            graphics.drawImage(rango, x + left, y + diagonalDown);
                            graphics.drawImage(rango, x + left, y + diagonalUp);
                            graphics.drawImage(rango, x + right, y + diagonalUp);
                            actived = true;
                        }
                        if (y == downLimitOdd && x == posOdd) {
                            graphics.drawImage(rango, x, y + up);
                            graphics.drawImage(rango, x + right, y + diagonalUp);
                            graphics.drawImage(rango, x + left, y + diagonalUp);
                            actived = true;
                        }
                    }
                }
            }

            if (!actived) {
                graphics.drawImage(rango, x, y + down);
                graphics.drawImage(rango, x, y + up);
                graphics.drawImage(rango, x + right, y + diagonalUp);
                graphics.drawImage(rango, x + left, y + diagonalUp);
                graphics.drawImage(rango, x + right, y + diagonalDown);
                graphics.drawImage(rango, x + left, y + diagonalDown);
            }
        }
    }

    public void collideRange() {
        if (enemy[0].isAlive()) {
            int enemyX = enemy[0].getX();
            int enemyY = enemy[0].getY();
            if ((enemyX == x && (enemyY == y || enemyY == y + up || enemyY == y + down)) ||
                    ((enemyX == x + right || enemyX == x + left) && (enemyY == y + diagonalUp || enemyY == y + diagonalDown))) {
                collideEnemy = true;
            }
        }
    }

    public void collideWithConsumable(ArrayList<Consumables> inventory){

        enemy = Gameplay.getEnemy();
        if (!enemy[0].isAlive()){
        if (inventory.size() == 2){
            if (inventory.getFirst().getX() == x && inventory.getFirst().getY() == y) {
                inventory.getFirst().setQuantity(inventory.getFirst().getQuantity() + 3);
                Gameplay.setGrabConsumable(true);
                Gameplay.setDrawConsumable(false);
                x = playerInitialPositionX;
                y = playerInitialPositionY;
                enemy[0].setX(enemyInitialPositionX);
                enemy[0].setY(enemyInitialPositionY);
                for (EnemyCharacter enemyCharacter : enemy) {
                    enemyCharacter.setAlive(true);
                }
            }
        else if (inventory.get(1).getX() == x && inventory.get(1).getY() == y){
            System.out.println("Tetetwtewtwetwete");
            inventory.get(1).setQuantity(inventory.get(1).getQuantity() + 3);
            Gameplay.setGrabConsumable(true);
            Gameplay.setDrawConsumable(false);
            x = playerInitialPositionX;
            y = playerInitialPositionY;
            enemy[0].setX(enemyInitialPositionX);
            enemy[0].setY(enemyInitialPositionY);
                for (EnemyCharacter enemyCharacter : enemy) {
                    enemyCharacter.setAlive(true);
                }
        }
        } }
    }

    public static boolean isCollideEnemy() {
        return collideEnemy;
    }

    public static void setCollideEnemy(boolean collideEnemy) {
        PlayerCharacter.collideEnemy = collideEnemy;
    }

    public int getAttack() {return attack;}

    public void setAttack(int attack) {this.attack = attack;}


    public void setEnemy(EnemyCharacter[] enemy) {this.enemy = enemy;}

    public PlayerCharacter(){

    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getX() {return x;}

    public void setX(int x) {this.x = x;}

    public int getY() {return y;}

    public void setY(int y) {this.y = y;}

    public String getImageName() {return imageName;}

    public void setImageName(String imageName) {this.imageName = imageName;}


    public String getClosestImageName() {
        return closestImageName;
    }

    public void setClosestImageName(String closestImageName) {
        this.closestImageName = closestImageName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public  boolean isHavesMana() {
        return havesMana;
    }


}

