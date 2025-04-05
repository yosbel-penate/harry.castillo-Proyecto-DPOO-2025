package consumables;

import characters.PlayersCharacters;

public class shard_of_Aether extends Consumables{
   private static int attack_added=2;
   private int id=1;

    public shard_of_Aether(String name, int cantidad, int attack_added) {
        super(name, cantidad);
        this.attack_added=attack_added;
    }

    @Override
    public void useConsumable(PlayersCharacters playersCharacters) {
        int newAttack=playersCharacters.getAttack()+attack_added;
        playersCharacters.setAttack(newAttack);
    }
    public static int getAttack_added(){
        return attack_added;
    }
    public int getId(){
        return id;
    }

}
