package app.cutscenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import static app.main.Game.window;

public class NarratorFirstApparition {
    private static GraphicsContext graphics;
    private static Scene narratorTalkingScene;
    private static Group root;
    private static Canvas canvas;


    public static void initialize(){

        root = new Group();
        narratorTalkingScene = new Scene(root, 1000, 850);
        canvas = new Canvas(1000, 850);
        root.getChildren().add(canvas);
        graphics = canvas.getGraphicsContext2D();
        window.setScene(narratorTalkingScene);
        draw();
        labelConfigurations(root);

    }


    private static void draw(){
        graphics.fillRect(0, 0, 1000, 850);
        Image narrador = new Image("narrador.png");
        graphics.drawImage(narrador, 150, -100);

    }


    private static void labelConfigurations(Group root) {
        Label message = new Label("¡Hola! Soy Eux_ven. Cogiste 2 en tu prueba y ahora reviviste en un mundo de fantasía. ");
        Font font = new Font ("P052 Roman",24);
        for (String fon : Font.getFontNames()){
            if(fon.toLowerCase().contains("times") || fon.toLowerCase().contains("new") || fon.toLowerCase().contains("roman")){
                System.out.println(fon);
            }
        }
        System.out.println(Font.getFontNames());

        message.setFont(font);
        message.setTextFill(Color.WHITE);
        message.setTranslateX(10);
        message.setTranslateY(700);

        root.getChildren().add(message);


    }

}
