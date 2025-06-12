package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Draven extends PlayerCharacter {


    private int health=10;
    private int attack = 3;
    private boolean havesMana = false;
    private String characterName = "Draven";
    private String imageName = "draven.png";
    private String closestImageName = "closerDraven.png";





    public String getClosestImageName() {
        return closestImageName;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
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
