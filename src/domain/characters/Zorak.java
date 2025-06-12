package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Zorak extends PlayerCharacter {
    private int health = 13;
    private int attack = 6;
    private boolean havesMana = false;
    private String characterName = "Zorak";
    private String imageName = "zorak.png";
    private String closestImageName = "closerZorak.png";

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
