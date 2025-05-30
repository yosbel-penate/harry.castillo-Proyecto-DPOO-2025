package app.menus;

import app.fastFeatures.AudioPlayer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControlsInstructions {
    private static VBox controlsVBox;
    private static Label controlsLabel;
    private static Button back;


    public static void initControlsInstructions(){
        back=new Button("Back");
        back.getStyleClass().add("menu-button");

        controlsLabel=new Label("Controles del juego:\n" +
                "\n- W: arriba" +
                "\n- Q: diagonal izquierda-arriba"+
                "\n- E: diagonal derecha-arriba"+
                "\n- S: abajo" +
                "\n- A: diagonal izquierda-abajo" +
                "\n- D: diagonal derecha-abajo" +
                "\n- P: Pausar" +
                "\n- T: Rango"+
                "\n- Space: Seleccionar");
        controlsLabel.getStyleClass().add("control-label");

        controlsVBox=new VBox(20);
        controlsVBox.getChildren().addAll(controlsLabel,back);
        controlsVBox.setAlignment(Pos.CENTER_RIGHT);
        controlsVBox.setPadding(new Insets(0,0,0,10));
        controlsVBox.setTranslateX(170);
        back.setTranslateX(-80);
        controlsVBox.setTranslateY(-50);

        hideMenu();

        back.setOnAction(_ -> {
            AudioPlayer.playButtonSound();
            hideMenu();
            OptionsMenu.showMenu();
        });



    }

    public static VBox getControlsVBox(){
        return controlsVBox;
    }

    public static void hideMenu(){
        controlsVBox.setVisible(false);
        controlsVBox.setMouseTransparent(true);

    }
    public static void showMenu(){
        controlsVBox.setVisible(true);
        controlsVBox.setMouseTransparent(false);
    }
}
