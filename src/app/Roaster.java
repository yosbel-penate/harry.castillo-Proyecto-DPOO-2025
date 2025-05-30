package app;

import app.fastFeatures.SelectedCharacters;
import app.gameModes.Campaign;
import app.fastFeatures.AudioPlayer;
import app.fastFeatures.PublicVariables;
import app.menus.PauseMenu;
import domain.characters.Draven;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import static app.fastFeatures.ButtonManager.*;
import static app.fastFeatures.LabelManager.*;
import static app.fastFeatures.PublicVariables.window;

import domain.generalClasses.PlayerCharacter;

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

    private static int row = 32;
    private static int col = 32;


    private static Label message;
    private static Label health = new Label();
    private static Label attack = new Label();
    private static Label mana = new Label();
    private static Label race = new Label();
    private static Label type = new Label();
    private static Label behavior = new Label();
    private static Label movement = new Label();
    private static Font font = new Font(20);

    private static Button yes;
    private static Button no;



    // Siguiente columna o fila.

    private static final int next = 120;

    // Filas del Roaster.

    private static final int first = 32;
    private  static final int second = 152;
    private  static final int third = 272;
    private  static final int fourth = 392;
    private  static final int fifth = 512;



    public static void setup() {
        setupWindow();
        setupCharactersArray();
        setupSelectedArray();
        moveSelectionSquare();
        drawAndActualizePosition();
    }

    private static void setupWindow() {
        root = new Group();
        roasterScene = new Scene(root, PublicVariables.screenWidth, PublicVariables.screenHeight);
        roasterScene.getStylesheets().add(Roaster.class.getResource("/buttons.css").toExternalForm());
        canvas = new Canvas(PublicVariables.screenWidth, PublicVariables.screenHeight);
        root.getChildren().add(canvas);
        graphics = canvas.getGraphicsContext2D();
        window.setScene(roasterScene);
        root.getChildren().addAll(health, mana, attack, race, type, behavior, movement, PauseMenu.getPauseMenu());
    }

    private static void setupCharactersArray() {
        player = new PlayerCharacter[5];
        player[0] = new PlayerCharacter();
        player[1] = new PlayerCharacter();
        player[2] = new PlayerCharacter();
        player[3] = new PlayerCharacter();
        player[4] = new PlayerCharacter();

    }

    private static void setupSelectedArray() {
        selected = new SelectedCharacters[5];
        selected[0] = new SelectedCharacters();
        selected[1] = new SelectedCharacters();
        selected[2] = new SelectedCharacters();
        selected[3] = new SelectedCharacters();
        selected[4] = new SelectedCharacters();
    }

    private static void moveSelectionSquare() {
        roasterScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!cantMove) {
                    switch (event.getCode().toString()) {
                        case "P":
                            PauseMenu.managePauseMenu();
                            break;
                        case "W":
                            if (col == first) {
                                break;
                            }
                            col -= next;
                            break;
                        case "S":
                            if (col == fourth && row == fourth) {
                                col += next;
                                break;
                            }
                            if (col == fourth && row == fifth) {
                                col += next;
                                break;
                            }
                            if (col == fifth && row == fifth) {
                                break;
                            }
                            if (col == fifth && row == fourth) {
                                break;
                            }
                            if (col == fourth) {
                                break;
                            }
                            col += next;
                            break;
                        case "D":
                            if (col == fifth && row == fifth) {
                                break;
                            }
                            if (row == fifth) {
                                break;
                            }
                            row += next;
                            break;

                        case "A":
                            if (col == fifth && row == fifth) {
                                break;
                            }
                            if (row == first) {
                                break;
                            }
                            row -= next;
                            break;
                        case "R":
                            reset();
                            break;
                        case "SPACE":
                            AudioPlayer.playSelectedCharacter();
                            selectCharacter();

                    }
                }
            }
        });

    }

    private static void drawAndActualizePosition() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                drawBackgroundAndSquares();
                drawCharacters();
                drawSelectionSquare();
                settingLabels();
                drawCharactersData();
                drawSelectedCharacters();
                allCharactersSelected();
            }
        };
        animationTimer.start();
    }

    private static void drawBackgroundAndSquares() {
        graphics.drawImage(new Image("background.png"), 0, 0);
        graphics.drawImage(new Image("characterSelector.png"), 8, 8);
        graphics.drawImage(new Image("characterData.png"), 702, 8);

    }

    private static void drawCharacters() {
        graphics.drawImage(new Image("closerHiglob.png"), first, first);
        graphics.drawImage(new Image("closerDraven.png"), first, second);
        graphics.drawImage(new Image("closerZorak.png"), first, third);
        graphics.drawImage(new Image("closerHeloro.png"), first, fourth);
        graphics.drawImage(new Image("closerLyrasa.png"), second, first);
    }


    private static void drawSelectionSquare() {
        graphics.drawImage(new Image("selectSquare.png"), row, col);
    }

    private static void settingLabels() {

        labelInvisibilizer(health, mana, attack, race, type, behavior, movement);
        labelSetFont(health, attack, race,type, behavior, movement, font);
        labelSetColor(health, attack, race, type, behavior, movement, Color.WHITE);

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
    }

    private static void drawCharactersData() {
        if (row == first && col == first) {
            labelVisibilizer(health,mana,attack,race, type,behavior,movement);
            graphics.drawImage(new Image("higlobFace.png"), 740, 20);
            health.setText("HP: 7");
            mana.setText("MP: 15");
            attack.setText("Attack: 2");
            race.setText("Raza: Goblin");
            type.setText("Tipo: Sanador");
            behavior.setText("Personalidad: Distraído,\nhalagador e inocente.");
            movement.setText("Movimiento: 4");
        }
        if (row == first && col == second) {
            labelVisibilizer(health,attack,race, type,behavior,movement);
            graphics.drawImage(new Image("dravenFace.png"), 740, 20);
            health.setText("HP: 10");
            attack.setText("Attack: 3");
            race.setText("Raza: Humano");
            type.setText("Tipo: Guerrero");
            behavior.setText("Personalidad: Valiente,\nleal e impulsivo.");
            movement.setText("Movimiento: 2");
        }
        if (row == first && col == third) {
            labelVisibilizer(health,attack,race, type,behavior,movement);
            graphics.drawImage(new Image("zorakFace.png"), 740, 20);
            health.setText("HP: 10");
            attack.setText("Attack: 5");
            race.setText("Raza: Orco");
            type.setText("Tipo: Berserker");
            behavior.setText("Personalidad: Experimen-\ntado, fuerte y sereno.");
            movement.setText("Movimiento: 3");
        }
        if (row == first && col == fourth) {
            labelVisibilizer(health,mana,attack,race, type,behavior,movement);
            graphics.drawImage(new Image("heloroFace.png"), 740, 20);
            health.setText("HP: 7");
            mana.setText("MP: 10 ");
            attack.setText("Attack: 5");
            race.setText("Raza: Elfo");
            type.setText("Tipo: Mago");
            behavior.setText("Personalidad: Sabio,\npreocupado y justo.");
            movement.setText("Movimiento: 4");
        }
        if (row == second && col == first) {
            labelVisibilizer(health,attack,race, type,behavior,movement);
            graphics.drawImage(new Image("lyrasaFace.png"), 740, 20);
            health.setText("HP: 6");
            attack.setText("Attack: 5");
            race.setText("Raza: Elfa");
            type.setText("Tipo: Arquera");
            behavior.setText("Personalidad: Silenciosa,\nprecisa y desconfiada.");
            movement.setText("Movimiento: 3");
        }

    }

    private static void drawSelectedCharacters() {
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
    }

    private static void allCharactersSelected() {
        if (selectedCharacters == 4) {
            graphics.drawImage(new Image("messageSquare.png"), 5, 670);
            Font font = new Font(18);

            message = createLabel(32, 690, "¿Esta seguro de haber elegido todos los personajes en el orden correcto?",
                    Color.WHITE, font);

            yes = createButton("Si.", 32, 730, e -> threeActions(), font);
            yes.setTextFill(Color.BLACK);

            no = createButton("No.", 92, 730, e -> reset(), font);
            no.setTextFill(Color.BLACK);


            labelVisibilizer(message);
            buttonVisibilizer(yes, no);

            root.getChildren().addAll(message, yes, no);
            animationTimer.stop();
            cantMove = true;
        }
    }


    private static void selectCharacter() {
        if (!(selectedCharacters == 4)) {
            selectedCharacters++;
        }

        String imageName = "";
        String closestImageName = "";
        String characterName = "";
        boolean noAllowed = false;
        int health = 0;
        int attack = 0;

        switch (row) {
            case first:
                switch (col) {
                    case first:
                        imageName = "higlob.png";
                        closestImageName = "closerHiglob.png";
                        characterName = "Higlob";
                        health = 7;
                        attack = 2;

                        break;
                    case second:
                        Draven Draven = new Draven();
                        imageName = Draven.getImageName();
                        closestImageName = Draven.getClosestImageName();
                        characterName = Draven.getCharacterName();
                        health = Draven.getHealth();
                        attack = Draven.getAttack();
                        break;
                    case third:
                        imageName = "zorak.png";
                        closestImageName = "closerZorak.png";
                        characterName = "Zorak";
                        health = 5;
                        attack = 1;
                        break;
                    case fourth:
                        imageName = "heloro.png";
                        closestImageName = "closerHeloro.png";
                        characterName = "Heloro";
                        health = 5;
                        attack = 1;
                        break;
                    default:
                        noAllowed = true;
                        break;
                }
                break;
            case second:
                switch (col) {
                    case first:
                        imageName = "lyrasa.png";
                        closestImageName = "closerLyrasa.png";
                        characterName = "Lyrasa";
                        health = 6;
                        attack = 2;
                        break;
                    default:
                        noAllowed = true;
                        break;
                }
                break;
            default:
                noAllowed = true;
                break;
        }

        if(!noAllowed) {
            boolean alreadySelected = false;
            for (int i = 0; i < selectedCharacters; i++) {
                if (player[i].getImageName().equals(imageName)) {
                    alreadySelected = true;
                    break;
                }
            }

            if (!alreadySelected) {
                player[selectedCharacters].setImageName(imageName);
                player[selectedCharacters].setClosestImageName(closestImageName);
                player[selectedCharacters].setCharacterName(characterName);
                player[selectedCharacters].setHealth(health);
                player[selectedCharacters].setAttack(attack);
                player[selectedCharacters].setX(64);
                player[selectedCharacters].setY(64);
                selected[selectedCharacters] = new SelectedCharacters(row, col, selectedCharacters, true);
            } else {
                System.out.println("No puede seleccionar este personaje de nuevo.");
                selectedCharacters--;
            }
        }else{
            System.out.println("De momento, no hay un personaje a seleccionar aqui.");
            selectedCharacters--;
        }
    }

    private static EventHandler<ActionEvent> threeActions(){
        animationTimer.stop();
        Campaign.initialize();
        AudioPlayer.playRoasterButtonSound();
        return null;
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

            labelInvisibilizer(message);
            buttonInvisibilizer(yes, no);
            animationTimer.start();
        }
    }
    public static PlayerCharacter[] getPlayer() {
        return player;
    }
}