package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Sirael extends PlayerCharacter {
    private int health = 20;
    private int attack = 7;
    private boolean havesMana = false;
    private String characterName = "Sirael";
    private String imageName = "Sirael.png";
    private String closestImageName = "closerSirael.png";

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
