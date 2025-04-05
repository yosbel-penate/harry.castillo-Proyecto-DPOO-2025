package consumables;

import characters.PlayersCharacters;

public class vitality_potion extends Consumables{
    private static int health_added=5;
    public int id=2;

    public vitality_potion(String name, int cantidad, int health_added) {
        super(name, cantidad);
        this.health_added=health_added;
    }

    @Override
    public void useConsumable(PlayersCharacters playersCharacters) {
        int newHealth=playersCharacters.getHealth()+health_added;
        playersCharacters.setAttack(newHealth);
    }
    public static int getHealth_added(){
        return health_added;
    }
    public int getId(){
        return id;
    }


}
