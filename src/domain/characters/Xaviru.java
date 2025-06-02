package domain.characters;

import domain.generalClasses.PlayerCharacter;

public class Xaviru extends PlayerCharacter {
    private int health = 15;
    private int attack = 10;
    private boolean havesMana = false;
    private String characterName = "Xaviru";
    private String imageName = "Xaviru.png";
    private String closestImageName = "closerXaviru.png";

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
    public static void queBola(String[] args){
        System.out.println("El Papucho");
    }
}
