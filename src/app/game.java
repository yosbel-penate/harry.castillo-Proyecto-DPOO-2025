package app;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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
        Image fondoPrincipal= new Image(getClass().getResource("/DAO/images/mainwallpaper/fondoPrincipal.png").toExternalForm());
        ImageView vista = new ImageView(fondoPrincipal);
        Button play = new Button("Jugar");
        play.getStyleClass().add("menu-button");
        Button options = new Button("Opciones");
        options.getStyleClass().add("menu-button");
        Button quit = new Button("Salir");
        quit.getStyleClass().add("menu-button");
        // Botones y la marca del titulo. Los botones estan estilizados con CSS

        VBox mainMenu = new VBox(20);
        mainMenu.getChildren().addAll(play, options, quit);
        mainMenu.setAlignment(Pos.CENTER);
        // Esto hace que la imagen se deforme proporcionalmente al cambio que se haga de tamaño en la pantalla
        vista.setPreserveRatio(false);
        vista.setFitWidth(850);
        vista.setFitHeight(832);

        /*
        Creando un contenedor que va a colocar elementos sobre elementos en el orden en que se pongan
         */
        StackPane root=new StackPane();
        root.getChildren().addAll(vista,mainMenu);

        /*
        Carga la escena con el fondo de pantalla del juego y los botones y hace que se ajusten al tamaño de la escena
         */
        mainScene=new Scene(root,832,850);
        vista.fitWidthProperty().bind(mainScene.widthProperty());
        vista.fitHeightProperty().bind(mainScene.heightProperty());

        /*Esto es para trasladar los botones hacia la izquierda y que se ajusten proporcionales
         al tamaño del menu*/
        play.translateXProperty().bind(mainScene.widthProperty().multiply(-0.30)); options.translateXProperty()
                .bind(mainScene.widthProperty().multiply(-0.30)); quit.translateXProperty()
                .bind(mainScene.widthProperty().multiply(-0.30));


        //Esto traslada los botones hacia arriba
        play.setTranslateY(-40);
        options.setTranslateY(-40);
        quit.setTranslateY(-40);

        //Añadiendo hoja de estilos de css para los botones
        mainScene.getStylesheets().add(getClass().getResource("/DAO/css/buttons.css").toExternalForm());
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
