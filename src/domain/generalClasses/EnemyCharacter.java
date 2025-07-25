package domain.generalClasses;

import java.util.Random;

import app.gameplayFeatures.Gameplay;
import domain.boss.Jefa;
import domain.enemies.Federico;
import domain.enemies.Golem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import domain.enemies.Wolf;

import static app.fastFeatures.PublicVariables.*;


public class EnemyCharacter {
    private int x;
    private int y;
    private int attack = 2;
    private int Health = 10;
    private boolean alive = true;
    private String imageName;
    private String closestImageName ;
    private PlayerCharacter character;
    private static boolean collidePlayer;
    private static Random random = new Random();


    public void draw(GraphicsContext graphics) {
        graphics.drawImage(new Image(imageName), x, y);
    }

    public void draw(EnemyCharacter enemi, GraphicsContext graphics) {
        graphics.drawImage(new Image(enemi.getImageName()), enemi.getX(), enemi.getY());
    }


    public static void randomQuantity(EnemyCharacter[][] enemi, int row, int mission){


        int random = new Random().nextInt(1, 6);
        if (mission == 3){
            random = 1;
        }

        for (int i = 0; i<random; i++){
            switch (mission){
                case 0:
                    enemi[row][i] = new Wolf();
                    break;
                case 1:
                    enemi[row][i] = new Golem();
                    break;
                case 2:
                    enemi[row][i] = new Federico();
                    break;
                case 3:
                    enemi[row][i] = new Jefa();
                    break;
            }
        }


        for (int i = 0; i < enemi[row].length; i++){
            if(enemi[row][i] == null){
                switch (mission){
                    case 0:
                        enemi[row][i] = new Wolf();
                        break;
                    case 1:
                        enemi[row][i] = new Golem();
                        break;
                    case 2:
                        enemi[row][i] = new Federico();
                        break;
                    case 3:
                        enemi[row][i] = new Jefa();
                }
                enemi[row][i].setAlive(false);
                enemi[row][i].setHealth(-1);
            }else{
                enemi[row][i].setAlive(true);
            }
        }

    }

    public void move(int playerx, int playery, int enemyx, int enemyy) {
        int distanceX = playerx - enemyx;
        int distanceY = playery - enemyy;

        if (distanceX < 0 && distanceY < 0) {
            if (distanceY < (enemyy + diagonalDown) + distanceY) {
                x = enemyx + left;
                y = enemyy + diagonalUp;
            } else {
                x = enemyx + right;
                y = enemyy + diagonalUp;
            }
        }
        if (distanceX < 0 && distanceY == 0) {
            x = enemyx + left;
            y = enemyy + diagonalDown;
        }
        if (distanceX == 0 && distanceY < 0) {
            y = enemyy + up;
        }
        if (distanceX < 0 && distanceY > 0) {
            x = enemyx + left;
            y = enemyy + diagonalDown;
        }
        if (distanceX > 0 && distanceY > 0) {
            if (distanceY > (enemyy + diagonalDown) + distanceY) {
                x = enemyx + left;
                y = enemyy + diagonalDown;
            } else {
                x = enemyx + right;
                y = enemyy + diagonalDown;
            }
        }
        if (distanceX > 0 && distanceY == 0) {
            x = enemyx + right;
            y = enemyy + diagonalDown;
        }
        if (distanceX == 0 && distanceY > 0) {
            y = enemyy + down;
        }
        if (distanceX > 0 && distanceY < 0) {
            x = enemyx + right;
            y = enemyy + diagonalUp;
        }
    }

    public void move(int playerx, int playery, int enemyx, int enemyy, EnemyCharacter enemi) {
        int distanceX = playerx - enemyx;
        int distanceY = playery - enemyy;
        if (distanceX < 0 && distanceY < 0) {
            if (distanceY < (enemyy + diagonalDown) + distanceY) {
                enemi.setX(enemyx + left);
                enemi.setY(enemyy + diagonalUp);
            } else {
                enemi.setX(enemyx + right);
                enemi.setY(enemyy + diagonalUp);
            }
        }
        if (distanceX < 0 && distanceY == 0) {
            enemi.setX(enemyx + left);
            enemi.setY(enemyy + diagonalDown);
        }
        if (distanceX == 0 && distanceY < 0) {
            enemi.setY(enemyy + up);
        }
        if (distanceX < 0 && distanceY > 0) {
            enemi.setX(enemyx + left);
            enemi.setY(enemyy + diagonalDown);
        }
        if (distanceX > 0 && distanceY > 0) {
            if (distanceY > (enemyy + diagonalDown) + distanceY) {
                enemi.setX(enemyx + left);
                enemi.setY(enemyy + diagonalDown);
            } else {
                enemi.setX(enemyx + right);
                enemi.setY(enemyy + diagonalDown);
            }
        }
        if (distanceX > 0 && distanceY == 0) {
            enemi.setX(enemyx + right);
            enemi.setY(enemyy + diagonalDown);
        }
        if (distanceX == 0 && distanceY > 0) {
            enemi.setY(enemyy + down);
        }
        if (distanceX > 0 && distanceY < 0) {
            enemi.setX(enemyx + right);
            enemi.setY(enemyy + diagonalUp);
        }
    }


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

    public void range(EnemyCharacter enemi, GraphicsContext graphics, long time) {
        Image rango = new Image("rangeTerrain.png");
        if (!Gameplay.isActivateRange() || time % 2 != 0) {
            return;
        }

        boolean actived = false;
        boolean corner = false;

        if (enemi.getX() == xLeftDownCornerLimit && enemi.getY() == yLeftDownCornerLimit) {
            graphics.drawImage(rango, enemi.getX(), enemi.getY() + left);
            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalUp);
            corner = true;
        } else if (enemi.getX() == xLeftUpCornerLimit && enemi.getY() == yLeftUpCornerLimit) {
            graphics.drawImage(rango, enemi.getX(), enemi.getY() + down);
            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalUp);
            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalDown);
            corner = true;
        } else if (enemi.getX() == xRightDownCornerLimit && enemi.getY() == yRightDownCornerLimit) {
            graphics.drawImage(rango, enemi.getX(), enemi.getY() + up);
            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalUp);
            corner = true;
        } else if (enemi.getX() == xRightUpCornerLimit && enemi.getY() == yRightUpCornerLimit) {
            graphics.drawImage(rango, enemi.getX(), enemi.getY() + down);
            graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalUp);
            graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalDown);
            corner = true;
        }

        if (corner) {
        } else {
            if (enemi.getX() == upLimit) {
                graphics.drawImage(rango, enemi.getX(), enemi.getY() + up);
                graphics.drawImage(rango, enemi.getX(), enemi.getY() + down);
                graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalUp);
                graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalDown);
                actived = true;
            } else if (enemi.getX() == downLimit) {
                graphics.drawImage(rango, enemi.getX(), enemi.getY() + up);
                graphics.drawImage(rango, enemi.getX(), enemi.getY() + down);
                graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalUp);
                graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalDown);
                actived = true;
            }

            for (int col = 1; col <= 11; col++) {
                if (col % 2 == 0) {
                    for (int posEven = initialEvenRow; posEven <= finalEvenRow; posEven += nextRowEven) {
                        if (enemi.getY() == upLimitEven && enemi.getX() == posEven) {
                            graphics.drawImage(rango, enemi.getX(), enemi.getY() + down);
                            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalDown);
                            graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalDown);
                            actived = true;
                        }
                        if (enemi.getY() == downLimitEven && enemi.getX() == posEven) {
                            graphics.drawImage(rango, enemi.getX(), enemi.getY() + up);
                            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalUp);
                            graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalUp);
                            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalDown);
                            graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalDown);
                            actived = true;
                        }
                    }
                } else {
                    for (int posOdd = initialOddRow; posOdd <= finalOddRow; posOdd += nextRowOdd) {
                        if (enemi.getY() == upLimitOdd && enemi.getX() == posOdd) {
                            graphics.drawImage(rango, enemi.getX(), enemi.getY() + down);
                            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalDown);
                            graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalDown);
                            graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalUp);
                            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalUp);
                            actived = true;
                        }
                        if (enemi.getY() == downLimitOdd && enemi.getX() == posOdd) {
                            graphics.drawImage(rango, enemi.getX(), enemi.getY() + up);
                            graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalUp);
                            graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalUp);
                            actived = true;
                        }
                    }
                }
            }

            if (!actived) {
                graphics.drawImage(rango, enemi.getX(), enemi.getY() + down);
                graphics.drawImage(rango, enemi.getX(), enemi.getY() + up);
                graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalUp);
                graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalUp);
                graphics.drawImage(rango, enemi.getX() + right, enemi.getY() + diagonalDown);
                graphics.drawImage(rango, enemi.getX() + left, enemi.getY() + diagonalDown);
            }
        }
    }

    public boolean collideRange(PlayerCharacter player) {
        int characterX = player.getX();
        int characterY = player.getY();
        int x = this.getX();
        int y = this.getY();
        return (characterX == x && (characterY == y || characterY == y + up || characterY == y + down)) ||
                ((characterX == x + right || characterX == x + left) && (characterY == y + diagonalUp || characterY == y + diagonalDown));
    }

    public void setCharacter(PlayerCharacter character) {
        this.character = character;
    }

    public String getImageName() {
        return imageName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public EnemyCharacter(){

    }

    public int getAttack() {
        return attack;
    }
    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        this.Health = health;
    }

    public String getClosestImageName() {
        return closestImageName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public static boolean isCollidePlayer() {
        return collidePlayer;
    }

    public static void setCollidePlayer(boolean collidePlayer) {
        EnemyCharacter.collidePlayer = collidePlayer;
    }

}



