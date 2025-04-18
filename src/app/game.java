package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
// Cosas de JavaFX.



public class game extends Application {

public static Stage window;
    public static Scene gameScene;
    public static boolean gameOver;
    private static GraphicsContext graphics;
    private Scene mainScene;
    private Scene gameModeScene;
    private Canvas canvas;
    // Escena, graficos, raiz y lienzo activados para su uso en toda la aplicacion.


    private boolean campaignActivated = false;
    // Booleano.

    public static Campaign campaign = new Campaign(graphics, gameScene);

    public static void main(String[] args) {
        launch(args);

    }

@Override
    public void start(Stage window) throws Exception {
        game.window = window;
        /* Game.window es la variable publica estatica que usaran
        todas las clases para instanciar la ventana que necesiten.
         */

        windowDesign();
        window.setScene(mainScene);
        window.setTitle("Cronicas de Valthar: El torneo de las eras");
        window.show();
        // Ventana principal.
    }
    private void windowDesign() {
        Button play = new Button("Jugar.");
        Button options = new Button("Opciones. ");
        Button quit = new Button("Salir. ");
        Label titulo = new Label("Cronicas de Valthar: El torneo de las eras.");
        // Botones y la marca del titulo.

        VBox mainMenu = new VBox(20);
        mainMenu.getChildren().addAll(titulo, play, options, quit);
        mainScene = new Scene(mainMenu, 832, 850);
        // Añadiendo todos estos botones al menu.
play.setOnAction(e -> gameModes());
        // El boton lleva a los modos de juego.

        quit.setOnAction(e -> window.close());


    }


    public void gameModes() {
        Button campaig = new Button("Campaña.");
        Button pvp = new Button("PvP.");
        Button tournament = new Button("Torneo.");
        // Botones para inicializar modos de juego.

        Campaign campaign = new Campaign(graphics, gameScene);
        game.campaign = campaign;
        /* Objeto campaña para instanciar el modo campaña. Cuando aprenda
                    a programar mejor las clases, no hare uso de un objeto. Tambien
                    se iguala el objeto publico estatico para poder usar la instancia
                    en todas las clases.
                 */
                VBox gameModeMenu = new VBox(30);
                gameModeMenu.getChildren().addAll(campaig, pvp, tournament);
                gameModeScene = new Scene(gameModeMenu, 832, 850);
                // Se añaden los botones: primero a su contenedor y luego a la pantalla.

                window.setScene(gameModeScene);
                // Se activa la escena donde se muestran todos los objetos.

                campaig.setOnAction(e -> campaign.initialize());
                // Cada boton lleva al modo de juego correspondiente.

}
}
