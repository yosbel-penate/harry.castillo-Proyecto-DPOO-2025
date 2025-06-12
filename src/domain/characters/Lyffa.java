package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Lyffa extends PlayerCharacter {
    private int health = 9;
    private int attack = 5;
    private boolean havesMana = true;
    private int mana = 12;
    private String characterName = "Lyffa";
    private String imageName = "lyffa.png";
    private String closestImageName = "closerLyffa.png";

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
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
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
