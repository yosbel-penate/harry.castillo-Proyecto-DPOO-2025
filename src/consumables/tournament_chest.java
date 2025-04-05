package consumables;

import characters.PlayersCharacters;
import java.util.Random;

public class tournament_chest extends Consumables{

    private int health_added;

    public tournament_chest(String name, int cantidad) {
        super(name, cantidad);
    }

    @Override
    public void useConsumable(PlayersCharacters playersCharacters) {
        Random random=new Random();
        int idRandom=random.nextInt(1,2);

        switch (idRandom){
            case 1:
                int newAttack=playersCharacters.getAttack()+shard_of_Aether.getAttack_added();
                playersCharacters.setAttack(newAttack);
                break;
            case 2:
                int newHealth=playersCharacters.getHealth()+vitality_potion.getHealth_added();
                playersCharacters.setAttack(newHealth);
                break;
            default:
                break;
        }
    }
}
