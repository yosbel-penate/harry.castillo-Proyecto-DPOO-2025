package domain.enemies;

import domain.generalClasses.EnemyCharacter;

public class Federico extends EnemyCharacter {
    private int x;
    private int y;
    private int health = 25;
    private int attack = 3;
    private String imageName = "federico.png";
    private String closestImageName = "closerFederico.png";


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

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String getImageName() {
        return imageName;
    }
    @Override
    public String getClosestImageName() {
        return closestImageName;
    }


    public Federico() {
    }
}
