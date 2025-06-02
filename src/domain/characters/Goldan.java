package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Goldan extends PlayerCharacter {
    private int health = 7;
    private int attack = 5;
    private boolean havesMana = True;
    private int mana = 10;
    private String characterName = "Goldan";
    private String imageName = "Goldan.png";
    private String closestImageName = "closerGoldan.png";

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
