package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Hobag extends PlayerCharacter {
    private int health = 7;
    private int attack = 3;
    private boolean havesMana = True;
    private int mana = 8;
    private String characterName = "Hobag";
    private String imageName = "Hobag.png";
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
