package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Jax extends PlayerCharacter {
    private int health = 11;
    private int attack = 6;
    private boolean havesMana = false;
    private String characterName = "Jax";
    private String imageName = "jax.png";
    private String closestImageName = "closerJax.png";

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
