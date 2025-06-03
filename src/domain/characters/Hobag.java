package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Hobag extends PlayerCharacter {
    private int health = 7;
    private int attack = 3;
    private boolean havesMana = true;
    private int mana = 8;
    private String characterName = "hobag";
    private String imageName = "hobag.png";
    private String closestImageName = "closerHobag.png";

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
    
    public int getMana() {
        return mana;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }
}
