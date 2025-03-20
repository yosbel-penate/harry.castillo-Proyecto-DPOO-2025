package characters;
//Harry todos los comentarios que veas aqui despues yo los borrare y dejare solo los necesarios

public abstract class Character {
    //atributos de la clase, el modificador de acceso es protected para poder usarlo en las subclases
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected int movement;

    //metodo constructor
    public Character(String nombre, int salud, int ataque, int defensa, int movement){
        this.name=nombre;
        this.health=salud;
        this.attack=ataque;
        this.defense=defensa;
        this.movement = movement;
    }

    /*ahora los metodos getters y setters, estaran ordenados por atributos. De no necesitar alguno
    de ellos en un futuro podemos eliminarlos o simplemente dejarlos como estan
     */
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
    /*con respecto al movimiento solo voy a agregar un metodo getter , porque no se si en un futuro
    dentro del juego se vaya a implementar algo para modificar el movement del jugador
     */
    public int getMovement(){
        return movement;
    }
    /*estos metodos a continuacion son concretos para acciones especificas dentro del juego, como hacer
    daño, curar la salud y cosas asi, si se me pasa alguno lo agregare despues, o igual si quieres puedes
    corregirlo tu y commiteas especificamente lo que hiciste, al igual que con cualquier otra parte del
    codigo
     */
    public void toAttack(Character objetive){//el hecho de que reciba una instancia de la clase personaje significa que puede reciber cualquier objeto de cualquiera de las subclases
    }
    public void takeDamage( int damage){
        int DamageReceived=
    }



}
