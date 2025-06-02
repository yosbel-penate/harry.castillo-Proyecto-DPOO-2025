package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Shira extends PlayerCharacter {
    private int health = 15;
    private int attack = 7;
    private boolean havesMana = false;
    private String characterName = "Shira";
    private String imageName = "Shira.png";
    private String closestImageName = "closerShira.png";

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
