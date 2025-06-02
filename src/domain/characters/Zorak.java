package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Zorak extends PlayerCharacter {
    private int health = 8;
    private int attack = 6;
    private boolean havesMana = false;
    private String characterName = "Zorak";
    private String imageName = "Zorak.png";
    private String closestImageName = "closerZorak.png";

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
