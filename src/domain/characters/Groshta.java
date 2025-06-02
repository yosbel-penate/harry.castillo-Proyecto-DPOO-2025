package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Groshta extends PlayerCharacter {
    private int health = 10;
    private int attack = 3;
    private boolean havesMana = false;
    private String characterName = "Groshta";
    private String imageName = "Groshta.png";
    private String closestImageName = "closerGroshta.png";

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
