package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Lyffa extends PlayerCharacter {
    private int health = 6;
    private int attack = 5;
    private boolean havesMana = True;
    private int mana = 12;
    private String characterName = "Lyffa";
    private String imageName = "Lyffa.png";
    private String closestImageName = "closerLyffa.png";

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
