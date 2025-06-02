package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Heloro extends PlayerCharacter {
    private int health = 12;
    private int attack = 8;
    private boolean havesMana = True;
    private int mana = 20;
    private String characterName = "Heloro";
    private String imageName = "Heloro.png";
    private String closestImageName = "closerHeloro.png";

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
