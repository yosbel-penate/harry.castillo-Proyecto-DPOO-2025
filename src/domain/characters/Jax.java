package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Jax extends PlayerCharacter {
    private int health = 8;
    private int attack = 6;
    private boolean havesMana = false;
    private String characterName = "Jax";
    private String imageName = "Jax.png";
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

    public boolean isHavesMana() {
        return havesMana;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }
}
