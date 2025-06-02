package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Hobgrou extends PlayerCharacter {
    private int health = 8;
    private int attack = 3;
    private boolean havesMana = false;
    private String characterName = "Hobgrou";
    private String imageName = "Hobgrou.png";
    private String closestImageName = "closerHobgrou.png";

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
