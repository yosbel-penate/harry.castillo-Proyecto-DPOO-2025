package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Gortans extends PlayerCharacter {
    private int health = 7;
    private int attack = 4;
    private boolean havesMana = True;
    private int mana = 15;
    private String characterName = "Gortana";
    private String imageName = "Gortana.png";
    private String closestImageName = "closerGortana.png";

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
