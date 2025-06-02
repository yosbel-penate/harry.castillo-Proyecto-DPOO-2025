package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Lina extends PlayerCharacter {
    private int health = 6;
    private int attack = 5;
    private boolean havesMana = True;
    private int mana = 10;
    private String characterName = "Lina";
    private String imageName = "Lina.png";
    private String closestImageName = "closerLina.png";

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
