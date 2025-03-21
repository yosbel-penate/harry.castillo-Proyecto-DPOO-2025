package characters;

public abstract class Character {

    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected int movement;
    protected int position;

    public Character(String nombre, int salud, int ataque, int defensa, int movement, int position){
        this.name=nombre;
        this.health=salud;
        this.attack=ataque;
        this.defense=defensa;
        this.movement = movement;
        this.position=position;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health=health;
    }
    public int getAttack(){
        return attack;
    }
    public void setAttack(int attack){
        this.attack=attack;
    }
    public int getDefense(){
        return defense;
    }
    public void setDefense(int defense){
        this.defense=defense;
    }
    public int getMovement(){
        return movement;
    }
    public void setMovement(int movement){
        this.movement=movement;
    }
    public int getPosition(){
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public void toAttack(Character objetive){
        objetive.takeDamage(this.attack);
    }
    public void takeDamage( int damage){
        int DamageReceived=Math.max(0,damage-this.defense);
        this.health-=DamageReceived;
    }




}
