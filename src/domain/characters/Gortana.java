package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Gortana extends PlayerCharacter {
    private int health = 7;
    private int attack = 4;
    private boolean havesMana = true;
    private int mana = 15;
    private String characterName = "Gortana";
    private String imageName = "gortana.png";
    private String closestImageName = "closerGortana.png";

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
