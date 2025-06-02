package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Higlob extends PlayerCharacter {
    private int health = 15;
    private int attack = 6;
    private boolean havesMana = True;
    private int mana = 25;
    private String characterName = "Higlob";
    private String imageName = "Higlob.png";
    private String closestImageName = "closerHiglob.png";

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
