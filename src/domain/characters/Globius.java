package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Globius extends PlayerCharacter {
    private int health = 7;
    private int attack = 5;
    private boolean havesMana = false;
    private String characterName = "Globius";
    private String imageName = "globiud.png";
    private String closestImageName = "closerGlobius.png";

    public String getClosestImageName() {
        return closestImageName;
    }

    public String getImageName() {
        return imageName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public boolean isHavesMana() {
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
