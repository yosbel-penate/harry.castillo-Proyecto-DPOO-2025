package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Higlob extends PlayerCharacter {
    private int health = 15;
    private int attack = 6;
    private boolean havesMana = true;
    private int mana = 25;
    private String characterName = "Higlob";
    private String imageName = "higlob.png";
    private String closestImageName = "closerHiglob.png";

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
