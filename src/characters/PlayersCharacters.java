package characters;

public class PlayersCharacters extends Character{
    private String type;
    private int level;

    public PlayersCharacters(String nombre, int salud, int ataque, int defensa, int movement, String type, int level, int position) {
        super(nombre, salud, ataque, defensa, movement,position);
        this.type=type;
        this.level=level;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level=level;
    }



}
