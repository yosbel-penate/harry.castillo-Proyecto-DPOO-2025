package domain.entities;

import java.util.Random;

import app.gameplayFeatures.Gameplay;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class EnemyCharacter {
    private int x;
    private int y;
    private int attack = 2;
    private int Health = 10;
    private boolean alive = true;
    private String imageName = "gayWolf.png";
    private String closestImageName = "closerGayWolf.png";
    // (Bromita)
    private PlayerCharacter character;
    private static boolean collidePlayer;
    private static Random random = new Random();


    public void draw(GraphicsContext graphics) {
        graphics.drawImage(new Image(imageName), x, y);
    }



    public void move(int playerx, int playery, int enemyx, int enemyy) {
        int distanceX = playerx - enemyx;
        int distanceY = playery - enemyy;



            if (distanceX < 0 && distanceY < 0) {
                if (distanceY < (enemyy+32) + distanceY) {
                    x = enemyx - 48;
                    y = enemyy - 32;

                } else {
                    x = enemyx - 48;
                    y = enemyy + 32;

                }


            } if (distanceX < 0 && distanceY == 0) {
                x = enemyx - 48;
                y = enemyy + 32;
            }if (distanceX == 0 && distanceY < 0){
                y = enemyy - 64;
            } if (distanceX < 0 && distanceY >0){
                x = enemyx - 48;
                y = enemyy + 32;
            }
            if (distanceX > 0 && distanceY > 0){
                if (distanceY > (enemyy+32) + distanceY) {
                    x = enemyx + 48;
                    y = enemyy - 32;

                } else {
                    x = enemyx + 48;
                    y = enemyy + 32;
                }
            } if (distanceX > 0 && distanceY == 0) {
                x = enemyx + 48;
                y = enemyy + 32;
            }if (distanceX == 0 && distanceY > 0){
                y = enemyy + 64;
            } if (distanceX > 0 && distanceY < 0){
                x = enemyx + 48;
                y = enemyy - 32;
            }




    }


    public void range(GraphicsContext graphics, long time) {
        Image rango = new Image("rangeTerrain.png");
        if (Gameplay.isActivateRange()) {
            boolean actived = false;
            boolean corner = false;
            if (time % 2 == 0) {

                if (x == 64 && y == 640) {

                    graphics.drawImage(rango, x, y - 64);
                    graphics.drawImage(rango, x + 48, y - 32);
                    actived = true;
                    corner = true;


                } else if (x == 64 && y == 64) {
                    graphics.drawImage(rango, x, y + 64);
                    graphics.drawImage(rango, x + 48, y + 32);
                    graphics.drawImage(rango, x + 48, y - 32);
                    actived = true;
                    corner = true;

                }
                if (x == 544 && y == 640) {
                    graphics.drawImage(rango, x, y - 64);
                    graphics.drawImage(rango, x - 48, y - 32);
                    actived = true;
                    corner = true;
                } else if (x == 544 && y == 64) {
                    graphics.drawImage(rango, x, y + 64);
                    graphics.drawImage(rango, x - 48, y + 32);
                    graphics.drawImage(rango, x - 48, y - 32);
                    corner = true;
                    actived = true;
                }

                if (!corner) {
                    if (x == 64) {
                        graphics.drawImage(rango, x, y + 64);
                        graphics.drawImage(rango, x, y - 64);
                        graphics.drawImage(rango, x + 48, y + 32);
                        graphics.drawImage(rango, x + 48, y - 32);
                        actived = true;
                    } else if (x == 544) {
                        graphics.drawImage(rango, x, y + 64);
                        graphics.drawImage(rango, x, y - 64);
                        graphics.drawImage(rango, x - 48, y + 32);
                        graphics.drawImage(rango, x - 48, y - 32);
                        actived = true;
                    }

                    for (int i = 1; i <= 11; i++) {

                        if (i % 2 == 0) {
                            for (int pos = 112; pos <= 496; pos += 96) {
                                if (y == 32 && x == pos) {
                                    graphics.drawImage(rango, x, y + 64);
                                    graphics.drawImage(rango, x + 48, y + 32);
                                    graphics.drawImage(rango, x - 48, y + 32);
                                    actived = true;

                                }
                                if (y == 608 && x == pos) {
                                    graphics.drawImage(rango, x, y - 64);
                                    graphics.drawImage(rango, x + 48, y - 32);
                                    graphics.drawImage(rango, x - 48, y - 32);
                                    graphics.drawImage(rango, x + 48, y + 32);
                                    graphics.drawImage(rango, x - 48, y + 32);
                                    actived = true;

                                }
                            }

                        } else {
                            for (int pos = 64; pos <= 544; pos += 96) {
                                if (y == 64 && x == pos) {
                                    graphics.drawImage(rango, x, y + 64);
                                    graphics.drawImage(rango, x + 48, y + 32);
                                    graphics.drawImage(rango, x - 48, y + 32);
                                    graphics.drawImage(rango, x - 48, y - 32);
                                    graphics.drawImage(rango, x + 48, y - 32);
                                    actived = true;
                                }
                                if (y == 640 && x == pos) {
                                    graphics.drawImage(rango, x, y - 64);
                                    graphics.drawImage(rango, x + 48, y - 32);
                                    graphics.drawImage(rango, x - 48, y - 32);
                                    actived = true;
                                }
                            }
                        }
                    }

                    if (!actived) {

                        graphics.drawImage(rango, x, y + 64);
                        graphics.drawImage(rango, x, y - 64);
                        graphics.drawImage(rango, x + 48, y - 32);
                        graphics.drawImage(rango, x - 48, y - 32);
                        graphics.drawImage(rango, x + 48, y + 32);
                        graphics.drawImage(rango, x - 48, y + 32);
                    }
                }

            }

        }

    }


    public void collideRange() {
        if (character.getX() == x && character.getY() == y) {collidePlayer = true;}
        if (character.getX() == x && character.getY() == y + 64) {collidePlayer = true;}
        if (character.getX() == x && character.getY() == y - 64) {collidePlayer = true;}
        if (character.getX() == x + 48 && character.getY() == y + 32) {collidePlayer = true;}
        if (character.getX() == x - 48 && character.getY() == y + 32) {collidePlayer = true;}
        if (character.getX() == x + 48 && character.getY() == y - 32) {collidePlayer = true;}
        if (character.getX() == x - 48 && character.getY() == y - 32) {collidePlayer = true;}
    }


    public PlayerCharacter getCharacter() {
        return character;
    }

    public void setCharacter(PlayerCharacter character) {
        this.character = character;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public EnemyCharacter(int x, int y) {
        this.y = y;
        this.x = x;
    }
    public EnemyCharacter(){

    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
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



