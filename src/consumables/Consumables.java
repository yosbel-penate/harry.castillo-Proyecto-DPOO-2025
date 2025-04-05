package consumables;

import characters.PlayersCharacters;

public abstract class Consumables {
    private String name;
    private int cantidad;

    public Consumables(String name,int cantidad){
        this.name=name;
        this.cantidad=cantidad;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public abstract void useConsumable(PlayersCharacters playersCharacters);
}
