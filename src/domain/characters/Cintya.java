package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Cintya extends PlayerCharacter {
    private int health = 8;
    private int attack = 5;
    private boolean havesMana = false;
    private String characterName = "Cintya";
    private String imageName = "Cintya.png";
    private String closestImageName = "closerCintya.png";

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
