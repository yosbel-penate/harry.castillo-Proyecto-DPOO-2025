package app.fastFeatures;

import domain.characters.*;
import domain.generalClasses.PlayerCharacter;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static app.fastFeatures.LabelManager.createLabel;

public class PublicVariables {
    // Campaing or Pvp

    public static boolean campaing;


    // Resolucion de Gameplay.
    public static final int screenWidth = 1000;
    public static final int screenHeight = 850;

    // Game
    public static Stage window = new Stage();

    // Movimientos en Gameplay (involucra tambien a PlayerCharacter)

    public static final int left = -48;
    public static final int right = 48;
    public static final int diagonalUp = -32;
    public static final int diagonalDown = 32;
    public static final int up = -64;
    public static final int down = 64;
    public static final int noPoints = 0;
    public static final int upLimitEven = 32;
    public static final int upLimitOdd = 64;
    public static final int downLimitEven = 608;
    public static final int downLimitOdd = 640;
    public static final int leftLimit = 64;
    public static final int rightLimit = 544;

    // PlayerCharacter, EnemyCharacter.

    // Posiciones iniciales del jugador y el enemigo (Roaster y Combat tambi�n).
    public static final int playerInitialPositionX = 64;
    public static final int playerInitialPositionY = 64;
    public static final int enemyInitialPositionX = 544;
    public static final int enemyInitialPositionY = 64;

    // Limites de los rangos de los enemigos y los personajes.

    public static final int xLeftDownCornerLimit = 64;
    public static final int yLeftDownCornerLimit = 640;
    public static final int xLeftUpCornerLimit = 64;
    public static final int yLeftUpCornerLimit = 64;
    public static final int xRightDownCornerLimit = 544;
    public static final int yRightDownCornerLimit = 640;
    public static final int xRightUpCornerLimit = 544;
    public static final int yRightUpCornerLimit = 64;
    public static final int upLimit = 64;
    public static final int downLimit = 544;
    public static final int nextRowEven = 96;
    public static final int nextRowOdd = 96;
    public static final int initialEvenRow = 112;
    public static final int finalEvenRow = 496;
    public static final int initialOddRow = 64;
    public static final int finalOddRow = 544;

    // Personajes inicializados.

    public static PlayerCharacter Alessandra = new Alessandra();
    public static PlayerCharacter Azeli = new Azeli();
    public static PlayerCharacter Cintya = new Cintya();
    public static PlayerCharacter Draven = new Draven();
    public static PlayerCharacter Drekker = new Drekker();
    public static PlayerCharacter Globius = new Globius();
    public static PlayerCharacter Goldan = new Goldan();
    public static PlayerCharacter Gortana = new Gortana();
    public static PlayerCharacter Grisha = new Grisha();
    public static PlayerCharacter Groshta = new Groshta();
    public static PlayerCharacter Heloro = new Heloro();
    public static PlayerCharacter Higlob = new Higlob();
    public static PlayerCharacter Hobag = new Hobag();
    public static PlayerCharacter Hobgrou = new Hobgrou();
    public static PlayerCharacter Jax = new Jax();
    public static PlayerCharacter Lina = new Lina();
    public static PlayerCharacter Lyffa = new Lyffa();
    public static PlayerCharacter Lyrasa = new Lyrasa();
    public static PlayerCharacter Shira = new Shira();
    public static PlayerCharacter Sirael = new Sirael();
    public static PlayerCharacter Xaviru = new Xaviru();
    public static PlayerCharacter Zorak = new Zorak();

    public static int restoreAllCharacters = 0;

    static Font font = new Font(20);
    public static Label actualTerrainData = createLabel(720, 350, "", Color.WHITE, font);
    public static Label upperActualTerrainData = createLabel(723, 430, "", Color.WHITE, font);

}
