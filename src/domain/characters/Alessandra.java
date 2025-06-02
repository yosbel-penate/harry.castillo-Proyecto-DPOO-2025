package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Alessandra extends PlayerCharacter {
    private int health = 6;
    private int attack = 2;
    private boolean havesMana = True;
    private int mana = 15;
    private String characterName = "Alessandra";
    private String imageName = "Alessandra.png";
    private String closestImageName = "closerAlessandra.png";

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
