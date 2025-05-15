package app.main.Roaster;

import app.gameModes.Campaign;
import app.main.AudioPlayer;
import app.main.Game;
import app.menus.PauseMenu;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import static app.main.Game.window;

import domain.entities.PlayerCharacter;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Roaster {
    private static Group root;
    private static Canvas canvas;
    private static GraphicsContext graphics;
    private static Scene roasterScene;
    private static AnimationTimer animationTimer;
    private static int selectedCharacters = -1;
    private static SelectedCharacters[] selected;
    private static String[] numberImages = {"first.png", "second.png", "third.png", "fourth.png", "fifth.png"};
    private static boolean cantMove = false;
    private static PlayerCharacter[] player;

    private static int x = 32;

    private static int y = 32;


    private static Label message;
    private static Label health = new Label();
    private static Label attack = new Label();
    private static Label mana = new Label();
    private static Label race = new Label();
    private static Label type= new Label();
    private static Label behavior = new Label();
    private static Label movement= new Label();
    private static Font font = new Font(20);





    private static Button yes;
    private static Button no;





    public static void initialize(){
        root = new Group();

        roasterScene = new Scene(root, 1000, 850);
        roasterScene.getStylesheets().add(Roaster.class.getResource("/buttons.css").toExternalForm());

        canvas = new Canvas(1000, 850);
        root.getChildren().add(canvas);
        graphics = canvas.getGraphicsContext2D();
        window.setScene(roasterScene);
        player = new PlayerCharacter[5];
        player[0] = new PlayerCharacter();
        player[1] = new PlayerCharacter();
        player[2] = new PlayerCharacter();
        player[3] = new PlayerCharacter();
        player[4] = new PlayerCharacter();

        selected = new SelectedCharacters[5];
        selected[0] = new SelectedCharacters();
        selected[1] = new SelectedCharacters();
        selected[2] = new SelectedCharacters();
        selected[3] = new SelectedCharacters();
        selected[4] = new SelectedCharacters();



        root.getChildren().addAll(health, mana, attack, race, type, behavior, movement,PauseMenu.getPauseMenu());


        roasterScene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == javafx.scene.input.KeyCode.P) {
                PauseMenu.managePauseMenu();
            }
        });



        moveSelectionSquare();
        drawAndActualizePosition();


    }

    private static void moveSelectionSquare() {
        roasterScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!cantMove) {
                    switch (event.getCode().toString()) {
                        case "W":
                            if (y == 32) {
                                break;
                            }
                            y -= 120;
                            break;
                        case "S":
                            if (y == 392 && x == 392) {
                                y += 120;
                                break;
                            }
                            if (y == 392 && x == 512) {
                                y += 120;
                                break;
                            }
                            if (y == 512 && x == 512) {
                                break;
                            }
                            if (y == 512 && x == 392) {
                                break;
                            }
                            if (y == 392) {
                                break;
                            }
                            y += 120;
                            break;
                        case "D":
                            if (y == 512 && x == 512) {
                                break;
                            }
                            if (x == 512) {
                                break;
                            }
                            x += 120;
                            break;

                        case "A":
                            if (y == 512 && x == 392) {
                                break;
                            }
                            if (x == 32) {
                                break;
                            }
                            x -= 120;
                            break;
                        case "R":
                            reset();
                            break;
                        case "SPACE":
                            AudioPlayer.playSelectedCharacter();
                            if (!(selectedCharacters == 4)) {
                                selectedCharacters++;
                            } else {
                                break;
                            }
                            switch (x) {
                                case 32:
                                    switch (y) {
                                        case 32:

                                            switch (selectedCharacters) {
                                                case 0:

                                                    player[selectedCharacters].setImageName("goblin.png");
                                                    player[selectedCharacters].setClosestImageName("closerHiglob.png");
                                                    player[selectedCharacters].setCharacterName("Higlob");
                                                    selected[0] = new SelectedCharacters(32, 32, 0, true);
                                                    player[selectedCharacters].setX(64);
                                                    player[selectedCharacters].setY(64);
                                                    player[selectedCharacters].setHealth(7);
                                                    player[selectedCharacters].setAttack(2);
                                                    break;
                                                case 1:
                                                    if (!(player[0].getImageName() == "goblin.png")) {
                                                        player[selectedCharacters].setImageName("goblin.png");
                                                        player[selectedCharacters].setClosestImageName("closerHiglob.png");
                                                        player[selectedCharacters].setCharacterName("Higlob");
                                                        player[selectedCharacters].setHealth(7);
                                                        player[selectedCharacters].setAttack(2);
                                                        selected[1] = new SelectedCharacters(32, 32, 1, true);

                                                    } else {
                                                        System.out.println("No puede seleccionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 2:
                                                    if (!(player[0].getImageName() == "goblin.png") && !(player[1].getImageName() == "goblin.png")) {
                                                        player[selectedCharacters].setImageName("goblin.png");
                                                        player[selectedCharacters].setClosestImageName("closerHiglob.png");
                                                        player[selectedCharacters].setCharacterName("Higlob");
                                                        player[selectedCharacters].setHealth(7);
                                                        player[selectedCharacters].setAttack(2);
                                                        selected[2] = new SelectedCharacters(32, 32, 2, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 3:
                                                    if (!(player[2].getImageName() == "goblin.png") && !(player[0].getImageName() == "goblin.png") && !(player[1].getImageName() == "goblin.png")) {
                                                        player[selectedCharacters].setImageName("goblin.png");
                                                        player[selectedCharacters].setClosestImageName("closerHiglob.png");
                                                        player[selectedCharacters].setCharacterName("Higlob");
                                                        player[selectedCharacters].setHealth(7);
                                                        player[selectedCharacters].setAttack(2);
                                                        selected[3] = new SelectedCharacters(32, 32, 3, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 4:
                                                    if (!(player[3].getImageName() == "goblin.png") && !(player[2].getImageName() == "goblin.png") && !(player[0].getImageName() == "goblin.png") && !(player[1].getImageName() == "goblin.png")) {
                                                        player[selectedCharacters].setImageName("goblin.png");
                                                        player[selectedCharacters].setClosestImageName("closerHiglob.png");
                                                        player[selectedCharacters].setCharacterName("Higlob");
                                                        player[selectedCharacters].setHealth(7);
                                                        player[selectedCharacters].setAttack(2);
                                                        selected[4] = new SelectedCharacters(32, 32, 4, true);

                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                            }
                                            break;
                                        case 152:
                                            switch (selectedCharacters) {
                                                case 0:
                                                    player[selectedCharacters].setImageName("draven.png");
                                                    player[selectedCharacters].setClosestImageName("closerDraven.png");
                                                    player[selectedCharacters].setCharacterName("Draven");
                                                    selected[0] = new SelectedCharacters(32, 152, 0, true);
                                                    player[selectedCharacters].setHealth(5);
                                                    player[selectedCharacters].setAttack(3);
                                                    player[selectedCharacters].setX(64);
                                                    player[selectedCharacters].setY(64);
                                                    break;
                                                case 1:
                                                    if (!(player[0].getImageName() == "draven.png")) {
                                                        player[selectedCharacters].setImageName("draven.png");
                                                        player[selectedCharacters].setClosestImageName("closerDraven.png");
                                                        player[selectedCharacters].setCharacterName("Draven");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(3);
                                                        selected[1] = new SelectedCharacters(32, 152, 1, true);
                                                    } else {
                                                        System.out.println("No puede seleccionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 2:
                                                    if (!(player[1].getImageName() == "draven.png") && !(player[0].getImageName() == "draven.png")) {
                                                        player[selectedCharacters].setImageName("draven.png");
                                                        player[selectedCharacters].setClosestImageName("closerDraven.png");
                                                        player[selectedCharacters].setCharacterName("Draven");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(3);
                                                        selected[2] = new SelectedCharacters(32, 152, 2, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 3:
                                                    if (!(player[2].getImageName() == "draven.png") && !(player[1].getImageName() == "draven.png") && !(player[0].getImageName() == "draven.png")) {
                                                        player[selectedCharacters].setImageName("draven.png");
                                                        player[selectedCharacters].setClosestImageName("closerDraven.png");
                                                        player[selectedCharacters].setCharacterName("Draven");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(3);
                                                        selected[3] = new SelectedCharacters(32, 152, 3, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 4:
                                                    if (!(player[3].getImageName() == "draven.png") && !(player[2].getImageName() == "draven.png") && !(player[1].getImageName() == "draven.png") && !(player[0].getImageName() == "draven.png")) {
                                                        player[selectedCharacters].setImageName("draven.png");
                                                        player[selectedCharacters].setClosestImageName("closerDraven.png");
                                                        player[selectedCharacters].setCharacterName("Draven");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(3);
                                                        selected[4] = new SelectedCharacters(32, 152, 4, true);

                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                            }
                                            break;
                                        case 272:
                                            switch (selectedCharacters) {
                                                case 0:
                                                    player[selectedCharacters].setImageName("berserker.png");
                                                    player[selectedCharacters].setClosestImageName("closerZorak.png");
                                                    player[selectedCharacters].setCharacterName("Zorak");
                                                    selected[0] = new SelectedCharacters(32, 272, 0, true);
                                                    player[selectedCharacters].setX(64);
                                                    player[selectedCharacters].setY(64);
                                                    player[selectedCharacters].setHealth(5);
                                                    player[selectedCharacters].setAttack(1);
                                                    break;
                                                case 1:
                                                    if (!(player[0].getImageName() == "berserker.png")) {
                                                        player[selectedCharacters].setImageName("berserker.png");
                                                        player[selectedCharacters].setClosestImageName("closerZorak.png");
                                                        player[selectedCharacters].setCharacterName("Zorak");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(1);
                                                        selected[1] = new SelectedCharacters(32, 272, 1, true);
                                                    } else {
                                                        System.out.println("No puede seleccionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 2:
                                                    if (!(player[1].getImageName() == "berserker.png") && !(player[0].getImageName() == "berserker.png")) {
                                                        player[selectedCharacters].setImageName("berserker.png");
                                                        player[selectedCharacters].setClosestImageName("closerZorak.png");
                                                        player[selectedCharacters].setCharacterName("Zorak");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(1);
                                                        selected[2] = new SelectedCharacters(32, 272, 2, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 3:
                                                    if (!(player[2].getImageName() == "berserker.png") && !(player[1].getImageName() == "berserker.png") && !(player[0].getImageName() == "berserker.png")) {
                                                        player[selectedCharacters].setImageName("berserker.png");
                                                        player[selectedCharacters].setClosestImageName("closerZorak.png");
                                                        player[selectedCharacters].setCharacterName("Zorak");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(1);
                                                        selected[3] = new SelectedCharacters(32, 272, 3, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 4:
                                                    if (!(player[3].getImageName() == "berserker.png") && !(player[2].getImageName() == "berserker.png") && !(player[1].getImageName() == "berserker.png") && !(player[0].getImageName() == "berserker.png")) {
                                                        player[selectedCharacters].setImageName("berserker.png");
                                                        player[selectedCharacters].setClosestImageName("closerZorak.png");
                                                        player[selectedCharacters].setCharacterName("Zorak");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(1);
                                                        selected[4] = new SelectedCharacters(32, 272, 4, true);

                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                            }
                                            break;
                                        case 392:
                                            switch (selectedCharacters) {
                                                case 0:
                                                    player[selectedCharacters].setImageName("goodElven.png");
                                                    player[selectedCharacters].setClosestImageName("closerHeloro.png");
                                                    player[selectedCharacters].setCharacterName("Heloro");
                                                    player[selectedCharacters].setHealth(5);
                                                    player[selectedCharacters].setAttack(1);
                                                    selected[0] = new SelectedCharacters(32, 392, 0, true);
                                                    player[selectedCharacters].setX(64);
                                                    player[selectedCharacters].setY(64);
                                                    break;
                                                case 1:
                                                    if (!(player[0].getImageName() == "goodElven.png")) {
                                                        player[selectedCharacters].setImageName("goodElven.png");
                                                        player[selectedCharacters].setClosestImageName("closerHeloro.png");
                                                        player[selectedCharacters].setCharacterName("Heloro");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(1);
                                                        selected[1] = new SelectedCharacters(32, 392, 1, true);
                                                    } else {
                                                        System.out.println("No puede seleccionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 2:
                                                    if (!(player[1].getImageName() == "goodElven.png") && !(player[0].getImageName() == "goodElven.png")) {
                                                        player[selectedCharacters].setImageName("goodElven.png");
                                                        player[selectedCharacters].setClosestImageName("closerHeloro.png");
                                                        player[selectedCharacters].setCharacterName("Heloro");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(1);
                                                        selected[2] = new SelectedCharacters(32, 392, 2, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 3:
                                                    if (!(player[2].getImageName() == "goodElven.png") && !(player[1].getImageName() == "goodElven.png") && !(player[0].getImageName() == "goodElven.png")) {
                                                        player[selectedCharacters].setImageName("goodElven.png");
                                                        player[selectedCharacters].setClosestImageName("closerHeloro.png");
                                                        player[selectedCharacters].setCharacterName("Heloro");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(1);
                                                        selected[3] = new SelectedCharacters(32, 392, 3, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 4:
                                                    if (!(player[3].getImageName() == "goodElven.png") && !(player[2].getImageName() == "goodElven.png") && !(player[1].getImageName() == "goodElven.png") && !(player[0].getImageName() == "goodElven.png")) {
                                                        player[selectedCharacters].setImageName("goodElven.png");
                                                        player[selectedCharacters].setClosestImageName("closerHeloro.png");
                                                        player[selectedCharacters].setCharacterName("Heloro");
                                                        player[selectedCharacters].setHealth(5);
                                                        player[selectedCharacters].setAttack(1);
                                                        selected[4] = new SelectedCharacters(32, 392, 4, true);

                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                                case 152:
                                    switch (y) {
                                        case 32:
                                            switch (selectedCharacters) {
                                                case 0:
                                                    player[selectedCharacters].setImageName("anotherElven.png");
                                                    player[selectedCharacters].setClosestImageName("closerLyrasa.png");
                                                    player[selectedCharacters].setCharacterName("Lyrasa");
                                                    player[selectedCharacters].setHealth(6);
                                                    player[selectedCharacters].setAttack(2);
                                                    selected[0] = new SelectedCharacters(152, 32, 0, true);
                                                    player[selectedCharacters].setX(64);
                                                    player[selectedCharacters].setY(64);
                                                    break;
                                                case 1:
                                                    if (!(player[0].getImageName() == "anotherElven.png")) {
                                                        player[selectedCharacters].setImageName("anotherElven.png");
                                                        player[selectedCharacters].setClosestImageName("closerLyrasa.png");
                                                        player[selectedCharacters].setCharacterName("Lyrasa");
                                                        player[selectedCharacters].setHealth(6);
                                                        player[selectedCharacters].setAttack(2);
                                                        selected[1] = new SelectedCharacters(152, 32, 1, true);
                                                    } else {
                                                        System.out.println("No puede seleccionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 2:
                                                    if (!(player[0].getImageName() == "anotherElven.png") && !(player[1].getImageName() == "anotherElven.png")) {
                                                        player[selectedCharacters].setImageName("anotherElven.png");
                                                        player[selectedCharacters].setClosestImageName("closerLyrasa.png");
                                                        player[selectedCharacters].setCharacterName("Lyrasa");
                                                        player[selectedCharacters].setHealth(6);
                                                        player[selectedCharacters].setAttack(2);
                                                        selected[2] = new SelectedCharacters(152, 32, 2, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 3:
                                                    if (!(player[2].getImageName() == "anotherElven.png") && !(player[0].getImageName() == "anotherElven.png") && !(player[1].getImageName() == "anotherElven.png")) {
                                                        player[selectedCharacters].setImageName("anotherElven.png");
                                                        player[selectedCharacters].setClosestImageName("closerLyrasa.png");
                                                        player[selectedCharacters].setCharacterName("Lyrasa");
                                                        player[selectedCharacters].setHealth(6);
                                                        player[selectedCharacters].setAttack(2);
                                                        selected[3] = new SelectedCharacters(152, 32, 3, true);
                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                                case 4:
                                                    if (!(player[3].getImageName() == "anotherElven.png") && !(player[2].getImageName() == "anotherElven.png") && !(player[0].getImageName() == "anotherElven.png") && !(player[1].getImageName() == "anotherElven.png")) {
                                                        player[selectedCharacters].setImageName("anotherElven.png");
                                                        player[selectedCharacters].setClosestImageName("closerLyrasa.png");
                                                        player[selectedCharacters].setCharacterName("Lyrasa");
                                                        player[selectedCharacters].setHealth(6);
                                                        player[selectedCharacters].setAttack(2);
                                                        selected[4] = new SelectedCharacters(152, 32, 4, true);

                                                    } else {
                                                        System.out.println("No puede selecctionar este personaje de nuevo. ");
                                                        selectedCharacters--;
                                                    }
                                                    break;
                                            }
                                            break;


                                        default:
                                            selectedCharacters--;
                                            break;
                                    }
                            }

                    }
                }
            }
        });


    }

    private static void drawAndActualizePosition(){
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                graphics.drawImage(new Image("roaster1.png"), 8, 8);
                graphics.drawImage(new Image("roaster2.png"), 702, 8);
                graphics.setFill(Color.BLACK);

                graphics.drawImage(new Image("closerHiglob.png"), 32,32);
                graphics.drawImage(new Image("closerDraven.png"), 32, 152);
                graphics.drawImage(new Image("closerZorak.png"), 32, 272);
                graphics.drawImage(new Image("closerHeloro.png"), 32, 392);
                graphics.drawImage(new Image("closerLyrasa.png"), 152, 32);
                //60 x 90.
                Image cuadraito = new Image("selectSquare.png");
                graphics.drawImage(cuadraito, x, y);

                health.setVisible(false);
                mana.setVisible(false);
                attack.setVisible(false);
                race.  setVisible(false);
                type.  setVisible(false);
                behavior.setVisible(false);
                movement.setVisible(false);

                health.setTranslateX(720);
                health.setTranslateY(240);
                mana.setTranslateX(780);
                mana.setTranslateY(240);
                attack.setTranslateX(720);
                attack.setTranslateY(290);
                race.setTranslateX(720);
                race.setTranslateY(340);
                type.setTranslateX(720);
                type.setTranslateY(390);
                behavior.setTranslateX(720);
                behavior.setTranslateY(440);
                movement.setTranslateX(720);
                movement.setTranslateY(540);

                if (x == 32 && y == 32){
                    health.setVisible(true);
                    mana.setVisible(true);
                    attack.setVisible(true);
                    race.setVisible(true);
                    type.setVisible(true);
                    behavior.setVisible(true);
                    movement.setVisible(true);

                    graphics.drawImage(new Image("higlobFace.png"), 740, 20);

                    health.setFont(font);
                    mana.setFont(font);
                    attack.setFont(font);
                    race.setFont(font);
                    type.setFont(font);
                    behavior.setFont(font);
                    movement.setFont(font);

                    health.setTextFill(Color.WHITE);
                    mana.setTextFill(Color.WHITE);
                    attack.setTextFill(Color.WHITE);
                    race.setTextFill(Color.WHITE);
                    type.setTextFill(Color.WHITE);
                    behavior.setTextFill(Color.WHITE);
                    movement.setTextFill(Color.WHITE);

                    health.setText("HP: 7");
                    mana.setText("MP: 15");
                    attack.setText("Attack: 2");
                    race.setText("Raza: Goblin");
                    type.setText("Tipo: Sanador");
                    behavior.setText("Personalidad: Distraído,\nhalagador e inocente.");
                    movement.setText("Movimiento: 4");















                }
                if (x == 32 && y ==152){
                    health.setVisible(true);
                    attack.setVisible(true);
                    race.setVisible(true);
                    type.setVisible(true);
                    behavior.setVisible(true);
                    movement.setVisible(true);

                    graphics.drawImage(new Image("dravenFace.png"), 740, 20);

                    health.setFont(font);
                    attack.setFont(font);
                    race.setFont(font);
                    type.setFont(font);
                    behavior.setFont(font);
                    movement.setFont(font);

                    health.setTextFill(Color.WHITE);
                    attack.setTextFill(Color.WHITE);
                    race.setTextFill(Color.WHITE);
                    type.setTextFill(Color.WHITE);
                    behavior.setTextFill(Color.WHITE);
                    movement.setTextFill(Color.WHITE);

                    health.setText("HP: 10");
                    attack.setText("Attack: 3");
                    race.setText("Raza: Humano");
                    type.setText("Tipo: Guerrero");
                    behavior.setText("Personalidad: Valiente,\nleal e impulsivo.");
                    movement.setText("Movimiento: 2");
                }
                if (x == 32 && y == 272){
                    health.setVisible(true);
                    attack.setVisible(true);
                    race.setVisible(true);
                    type.setVisible(true);
                    behavior.setVisible(true);
                    movement.setVisible(true);

                    graphics.drawImage(new Image("zorakFace.png"), 740, 20);

                    health.setFont(font);
                    attack.setFont(font);
                    race.setFont(font);
                    type.setFont(font);
                    behavior.setFont(font);
                    movement.setFont(font);

                    health.setTextFill(Color.WHITE);
                    attack.setTextFill(Color.WHITE);
                    race.setTextFill(Color.WHITE);
                    type.setTextFill(Color.WHITE);
                    behavior.setTextFill(Color.WHITE);
                    movement.setTextFill(Color.WHITE);

                    health.setText("HP: 10");
                    attack.setText("Attack: 5");
                    race.setText("Raza: Orco");
                    type.setText("Tipo: Berserker");
                    behavior.setText("Personalidad: Experimen-\ntado, fuerte y sereno.");
                    movement.setText("Movimiento: 3");
                }
                if (x == 32 && y == 392){
                    health.setVisible(true);
                    mana.setVisible(true);
                    attack.setVisible(true);
                    race.setVisible(true);
                    type.setVisible(true);
                    behavior.setVisible(true);
                    movement.setVisible(true);

                    graphics.drawImage(new Image("heloroFace.png"), 740, 20);

                    health.setFont(font);
                    mana.setFont(font);
                    attack.setFont(font);
                    race.setFont(font);
                    type.setFont(font);
                    behavior.setFont(font);
                    movement.setFont(font);

                    health.setTextFill(Color.WHITE);
                    mana.setTextFill(Color.WHITE);
                    attack.setTextFill(Color.WHITE);
                    race.setTextFill(Color.WHITE);
                    type.setTextFill(Color.WHITE);
                    behavior.setTextFill(Color.WHITE);
                    movement.setTextFill(Color.WHITE);

                    health.setText("HP: 7");
                    mana.setText("MP: 10 ");
                    attack.setText("Attack: 5");
                    race.setText("Raza: Elfo");
                    type.setText("Tipo: Mago");
                    behavior.setText("Personalidad: Sabio,\npreocupado y justo.");
                    movement.setText("Movimiento: 4");
                }
                if (x == 152 && y == 32){
                    health.setVisible(true);
                    attack.setVisible(true);
                    race.setVisible(true);
                    type.setVisible(true);
                    behavior.setVisible(true);
                    movement.setVisible(true);

                    graphics.drawImage(new Image("lyrasaFace.png"), 740, 20);

                    health.setFont(font);
                    attack.setFont(font);
                    race.setFont(font);
                    type.setFont(font);
                    behavior.setFont(font);
                    movement.setFont(font);

                    health.setTextFill(Color.WHITE);
                    attack.setTextFill(Color.WHITE);
                    race.setTextFill(Color.WHITE);
                    type.setTextFill(Color.WHITE);
                    behavior.setTextFill(Color.WHITE);
                    movement.setTextFill(Color.WHITE);

                    health.setText("HP: 6");
                    attack.setText("Attack: 5");
                    race.setText("Raza: Elfa");
                    type.setText("Tipo: Arquera");
                    behavior.setText("Personalidad: Silenciosa,\nprecisa y desconfiada.");
                    movement.setText("Movimiento: 3");
                }

                if (selected[4].isCharacterActivated()) {
                    graphics.drawImage(new Image(numberImages[selected[4].getNumberImage()]), selected[4].getX(), selected[4].getY());
                }
                if (selected[3].isCharacterActivated()) {
                    graphics.drawImage(new Image(numberImages[selected[3].getNumberImage()]), selected[3].getX(), selected[3].getY());
                }
                if (selected[2].isCharacterActivated()) {
                    graphics.drawImage(new Image(numberImages[selected[2].getNumberImage()]), selected[2].getX(), selected[2].getY());
                }
                if (selected[1].isCharacterActivated()) {
                    graphics.drawImage(new Image(numberImages[selected[1].getNumberImage()]), selected[1].getX(), selected[1].getY());
                }
                if (selected[0].isCharacterActivated()) {
                    graphics.drawImage(new Image(numberImages[selected[0].getNumberImage()]), selected[0].getX(), selected[0].getY());
                }
                if (selectedCharacters == 4){
                    Font font = new Font(18);
                    message = new Label("¿Esta seguro de haber elegido todos los personajes en el orden correcto?");
                    message.setFont(font);
                    message.setTranslateX(32);
                    message.setTranslateY(670);
                    message.setTextFill(Color.BLACK);


                    yes = new Button ("Si.");
                    no = new Button ("No.");
                    yes.setFont(font);
                    yes.setTranslateX(32);
                    yes.setTranslateY(730);
                    yes.setTextFill(Color.BLACK);
                    yes.setFocusTraversable(false);
                    no.setFont(font);
                    no.setTranslateX(92);
                    no.setTranslateY(730);
                    no.setTextFill(Color.BLACK);
                    no.setFocusTraversable(false);

                    yes.setOnMouseClicked(e -> threeActions());
                    no.setOnMouseClicked(e -> reset());

                    message.setDisable(false);
                    message.setVisible(true);
                    yes.setDisable(false);
                    yes.setVisible(true);
                    no.setDisable(false);
                    no.setVisible(true);
                    root.getChildren().addAll(message, yes, no);
                    animationTimer.stop();
                    cantMove = true;
                }

            }
        };
        animationTimer.start();
    }


    private static void threeActions(){
        animationTimer.stop();
        Campaign.initialize();
        AudioPlayer.playRoasterButtonSound();
    }
    private static void reset(){
        selectedCharacters = -1;
        cantMove = false;
        player[0] = new PlayerCharacter();
        player[1] = new PlayerCharacter();
        player[2] = new PlayerCharacter();
        player[3] = new PlayerCharacter();
        player[4] = new PlayerCharacter();


        selected[0] = new SelectedCharacters();
        selected[1] = new SelectedCharacters();
        selected[2] = new SelectedCharacters();
        selected[3] = new SelectedCharacters();
        selected[4] = new SelectedCharacters();

        if(message != null && yes != null && no != null){

            message.setDisable(true);
            message.setVisible(false);
            yes.setDisable(true);
            yes.setVisible(false);
            no.setDisable(true);
            no.setVisible(false);
            animationTimer.start();
        }
    }
    public static PlayerCharacter[] getPlayer() {
        return player;
    }

    public static void setPlayer(PlayerCharacter[] player) {
        Roaster.player = player;
    }


}