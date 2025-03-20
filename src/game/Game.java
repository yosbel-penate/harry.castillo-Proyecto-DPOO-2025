package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import characters.Character;

public class Game extends Application {

    private GraphicsContext graphics;
    private Group root;
    private Scene scene;
    private Canvas canvas;
    //Escena, graficos, raiz y lienzo activados para su uso en toda la aplicacion.

    private int posxodd = 64;
    private int posxeven = 128;
    private int posyodd = 128;
    private int posyeven = 64;
    //Posiciones pares (even) e impares (odd) de las columnas iniciales.


    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage window) throws Exception {
        initializeComponents();
        eventHandler();
        gameLoop();
        draw();
        window.setScene(scene);
        window.setTitle("Cronicas de Valthar: El torneo de las eras");
        window.show();

    }

    public void gameLoop() {
        long InitialTime = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {

            //Este metodo es el que maneja los frames por segundo, que son 60.
            @Override
            public void handle(long actualTime) {
                actualizeState();
                mapLimits();
                draw();


            }
        };
        animationTimer.start();
    }

    public void actualizeState() {

    }

    public void initializeComponents() {
        root = new Group();
        scene = new Scene(root, 832, 850);
        canvas = new Canvas(832, 850);
        root.getChildren().add(canvas);
        graphics = canvas.getGraphicsContext2D();


    }

    public void mapLimits(){
        // Limites horizontales.
        if (posxodd < 64){
        posxodd = 64;
        }else if (posxodd > 704){
            posxodd = 704;
        }

        // Limites verticales.
        for (int i = 1; i <= 11; i++){
            int position = i *64;
            if (i % 2 == 0){
                if (posyodd < 64 && posxodd == position){
                    posyodd = 64;
                }
                if (posyodd > 576 && posxodd == position){
                    posyodd = 576;
                }

            }else{
                if (posyodd < 128 && posxodd == position){
                    posyodd = 128;
                }
                if (posyodd > 640 && posxodd == position){
                    posyodd = 640;
                }

            }





        }

    }


        public void draw () {
            for (int i = 1; i <= 11; i++) {
                if (i % 2 == 0) {

                    int pos = i * 64;
                    for (int j = 64; j < 640; j += 64) {
                        graphics.fillRect(pos, j, 64, 64);
                        System.out.println("Columna: " + i + ", posicion: " + (j / 64));

                    }


                } else {
                    int pos = i * 64;
                    for (int j = 128; j < 704; j += 64) {
                        graphics.fillRect(pos, j, 64, 64);
                        System.out.println("Columna: " + i + ", posicion: " + (j / 64));

                    }

                }


            }
            graphics.drawImage(new Image("monigote.png"), posxodd, posyodd);


        }
        public void eventHandler () {
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode().toString()) {
                        case "RIGHT":
                            posxodd += 64;
                            break;
                        case "LEFT":
                            posxodd -= 64;
                            break;
                        case "UP":
                            posyodd -= 64;
                            break;
                        case "DOWN":
                            posyodd += 64;
                            break;


                    }
                }
            });


        }


    }


