package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Cintya extends PlayerCharacter {
    private int health = 8;
    private int attack = 5;
    private boolean havesMana = false;
    private String characterName = "Cintya";
    private String imageName = "cintya.png";
    private String closestImageName = "closerCintya.png";

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
