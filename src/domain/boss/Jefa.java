package domain.boss;

import domain.generalClasses.EnemyCharacter;

public class Jefa extends EnemyCharacter {
    private int x;
    private int y;
    private int health = 40;
    private int attack = 10;
    private String imageName = "jefaMala.png";
    private String closestImageName = "closerJefaMala.png";


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

    public Jefa() {
    }
}
