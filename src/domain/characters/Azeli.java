package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Azeli extends PlayerCharacter {
    private int health = 7;
    private int attack = 4;
    private boolean havesMana = true;
    private int mana = 12;
    private String characterName = "Azeli";
    private String imageName = "azeli.png";
    private String closestImageName = "closerAzeli.png";

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
    
    public int getMana() {
        return mana;
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
