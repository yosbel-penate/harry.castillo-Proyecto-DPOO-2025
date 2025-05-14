package app.main;

import app.menus.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
// Cosas de JavaFX.


public class Game extends Application {
    public static Stage window;
    public static AudioClip audio;
    private static Scene mainScene;
    public static boolean isPausable=false;
    private static StackPane stackPane=new StackPane();
    private HBox mainContainer;
    private VBox leftContainer;
    private StackPane rightContainer;
    // Escena, graficos, raiz y lienzo activados para su uso en toda la aplicacion.


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage window) {
        Game.window = window;
        /* Game.window es la variable publica estatica que usaran
        todas las clases para instanciar la ventana que necesiten.
         */
        mainScene=new Scene(stackPane,1000,850);
        mainScene.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());

        Initializer.InitAllMethods();

        mainScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case P -> {
                    if (Game.isPausable) {
                        PauseMenu.managePauseMenu();
                    }
                }
            }
        });

        ImageView wallpaper=new ImageView(getClass().getResource("/fondoPrincipal.png").toExternalForm());
        wallpaper.setPreserveRatio(false);
        wallpaper.fitWidthProperty().bind(stackPane.widthProperty());
        wallpaper.fitHeightProperty().bind(stackPane.heightProperty());

        leftContainer=MainMenu.getMainMenu();

        rightContainer=new StackPane();
        rightContainer.setPrefWidth(300);
        rightContainer.setAlignment(Pos.CENTER_RIGHT);
        rightContainer.getChildren().addAll(OptionsMenu.getOptionsMenu(),GameModeMenu.getGameModeMenu(),
                SlidersSoundsMenu.getSliders(), SlidersBrightnessMenu.getBrightnessVbox());



        mainContainer=new HBox();
        mainContainer.setSpacing(150);
        mainContainer.getChildren().addAll(leftContainer,rightContainer);



        stackPane.getChildren().addAll(wallpaper,mainContainer,PauseMenu.getPauseMenu());




        window.setScene(mainScene);
        window.setTitle("Cronicas de Valthar: El Torneo de las Eras");
        window.setResizable(false);
        window.show();



    }

    public static Scene getMainScene(){
        return mainScene;
    }
    public static StackPane getStackPane(){
        return stackPane;
    }

}



