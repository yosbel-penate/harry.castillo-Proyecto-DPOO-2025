package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Shira extends PlayerCharacter {
    private int health = 15;
    private int attack = 7;
    private boolean havesMana = false;
    private String characterName = "Shira";
    private String imageName = "shira.png";
    private String closestImageName = "closerShira.png";

    public String getClosestImageName() {
        return closestImageName;
    }

    public String getImageName() {
        return imageName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public boolean havesMana() {
        return havesMana;
    }

    @Override
    public int getAttack() {
        return attack;
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
    public void setAttack(int attack){
        this.attack=attack;
    }
}
