package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Groshta extends PlayerCharacter {
    private int health = 10;
    private int attack = 3;
    private boolean havesMana = false;
    private String characterName = "Groshta";
    private String imageName = "groshta.png";
    private String closestImageName = "closerGroshta.png";

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
