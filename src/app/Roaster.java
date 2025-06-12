package app;

import app.fastFeatures.SelectedCharacters;
import app.gameModes.Campaign;
import app.fastFeatures.AudioPlayer;
import app.fastFeatures.PublicVariables;
import app.menus.PauseMenu;
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
import static app.fastFeatures.PublicVariables.*;

import domain.generalClasses.PlayerCharacter;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Roaster {
    private static Group root;
    private static Canvas canvas;
    private static GraphicsContext graphics;
    private static Scene roasterScene;
    public static AnimationTimer animationTimer;
    private static int selectedCharacters = -1;
    private static SelectedCharacters[] selected;
    private static String[] numberImages = {"first.png", "second.png", "third.png", "fourth.png", "fifth.png"};
    private static boolean cantMove = false;
    private static PlayerCharacter[] player;

    private static int col = 32;
    private static int row = 32;


    private static Label message;
    private static Label name = new Label();
    private static Label health = new Label();
    private static Label attack = new Label();
    private static Label mana = new Label();
    private static Label race = new Label();
    private static Label type = new Label();
    private static Label behavior = new Label();
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
        root.getChildren().addAll(name, health, mana, attack, race, type, behavior, PauseMenu.getPauseMenu());
    }

    private static void setupCharactersArray() {
        player = new PlayerCharacter[5];
        for(int i = 0; i<5;i++){
            player[i] = new PlayerCharacter();
        }
    }

    private static void setupSelectedArray() {
        selected = new SelectedCharacters[5];
        for(int i = 0; i<5;i++){
            selected[i] = new SelectedCharacters();
        }
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
                            if (row == first) {
                                break;
                            }
                            row -= next;
                            break;
                        case "S":
                            if (row == fourth && col == fourth) {
                                row += next;
                                break;
                            }
                            if (row == fourth && col == fifth) {
                                row += next;
                                break;
                            }
                            if (row == fifth && col == fifth) {
                                break;
                            }
                            if (row == fifth && col == fourth) {
                                break;
                            }
                            if (row == fourth) {
                                break;
                            }
                            row += next;
                            break;
                        case "D":
                            if (row == fifth && col == fifth) {
                                break;
                            }
                            if (col == fifth) {
                                break;
                            }
                            col += next;
                            break;

                        case "A":
                            if (row == fifth && col == fourth) {
                                break;
                            }
                            if (col == first) {
                                break;
                            }
                            col -= next;
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
        graphics.drawImage(new Image("closerDraven.png"), first, first);
        graphics.drawImage(new Image("closerSirael.png"), first, second);
        graphics.drawImage(new Image("closerHobgrou.png"), first, third);
        graphics.drawImage(new Image("closerDrekker.png"), first, fourth);

        graphics.drawImage(new Image("closerCintya.png"), second, first);
        graphics.drawImage(new Image("closerLyrasa.png"), second, second);
        graphics.drawImage(new Image("closerGroshta.png"), second, third);
        graphics.drawImage(new Image("closerGrisha.png"), second, fourth);

        graphics.drawImage(new Image("closerJax.png"), third, first);
        graphics.drawImage(new Image("closerXaviru.png"), third, second);
        graphics.drawImage(new Image("closerGlobius.png"), third, third);
        graphics.drawImage(new Image("closerZorak.png"), third, fourth);

        graphics.drawImage(new Image("closerLina.png"), fourth, first);
        graphics.drawImage(new Image("closerHeloro.png"), fourth, second);
        graphics.drawImage(new Image("closerHobag.png"), fourth, third);
        graphics.drawImage(new Image("closerGoldan.png"), fourth, fourth);
        graphics.drawImage(new Image("closerAzely.png"), fourth, fifth);

        graphics.drawImage(new Image("closerAlessandra.png"), fifth, first);
        graphics.drawImage(new Image("closerLyffa.png"), fifth, second);
        graphics.drawImage(new Image("closerHiglob.png"), fifth, third);
        graphics.drawImage(new Image("closerGortana.png"), fifth, fourth);
        graphics.drawImage(new Image("closerShira.png"), fifth, fifth);
    }


    private static void drawSelectionSquare() {
        graphics.drawImage(new Image("selectSquare.png"), col, row);
    }

    private static void settingLabels() {

        labelInvisibilizer(name, health, mana, attack, race, type, behavior);
        labelSetFont(name, health, mana, attack, race,type, behavior, font);
        labelSetColor(name, health, mana, attack, race, type, behavior, Color.WHITE);

        labelLocator(
                name, 720, 240,
                health, 720, 290,
                mana, 790, 290,
                attack, 720, 340,
                race, 720, 390,
                type, 720, 440,
                behavior, 720, 490
        );
    }

    private static void drawCharactersData() {

        labelVisibilizer(name, health, mana, attack, race, type, behavior);
        switch (col) {
            case first:
                switch (row) {
                    case first:
                        graphics.drawImage(new Image("dravenFace.png"), 740, 20);
                        name.setText(Draven.getCharacterName());
                        health.setText("HP: " + Draven.getHealth());
                        attack.setText("Attack: " + Draven.getAttack());
                        race.setText("Raza: Humano");
                        type.setText("Tipo: Guerrero");
                        behavior.setText("Personalidad: Valiente,\nleal e impulsivo.");
                        labelInvisibilizer(mana);
                        break;
                    case second:
                        graphics.drawImage(new Image("siraelFace.png"), 740, 20);
                        name.setText(Sirael.getCharacterName());
                        health.setText("HP: " + Sirael.getHealth());
                        attack.setText("Attack: " + Sirael.getAttack());
                        race.setText("Raza: Elfo");
                        type.setText("Tipo: Guerrero");
                        behavior.setText("Personalidad: Serio,\nhonorable e inalcanzable.");
                        labelInvisibilizer(mana);
                        break;
                    case third:
                        graphics.drawImage(new Image("hobgrouFace.png"), 740, 20);
                        name.setText(Hobgrou.getCharacterName());
                        health.setText("HP: " + Hobgrou.getHealth());
                        attack.setText("Attack: " + Hobgrou.getAttack());
                        race.setText("Raza: Goblin");
                        type.setText("Tipo: Guerrero");
                        behavior.setText("Personalidad: Valiente,\ntenaz y reservado.");
                        labelInvisibilizer(mana);
                        break;
                    case fourth:
                        graphics.drawImage(new Image("drekkerFace.png"), 740, 20);
                        name.setText(Drekker.getCharacterName());
                        health.setText("HP: " + Drekker.getHealth());
                        attack.setText("Attack: " + Drekker.getAttack());
                        race.setText("Raza: Orco");
                        type.setText("Tipo: Guerrero");
                        behavior.setText("Personalidad: Experimen-\ntado, fuerte y sereno.");
                        labelInvisibilizer(mana);
                        break;
                }
                break;
            case second:
                switch (row) {
                    case first:
                        graphics.drawImage(new Image("cintyaFace.png"), 740, 20);
                        name.setText(Cintya.getCharacterName());
                        health.setText("HP: " + Cintya.getHealth());
                        attack.setText("Attack: " + Cintya.getAttack());
                        race.setText("Raza: Humana");
                        type.setText("Tipo: Arquera");
                        behavior.setText("Personalidad: Testaruda,\nfamosa y demasiado\nsincera.");
                        labelInvisibilizer(mana);
                        break;

                    case second:
                        graphics.drawImage(new Image("lyrasaFace.png"), 740, 20);
                        name.setText(Lyrasa.getCharacterName());
                        health.setText("HP: " + Lyrasa.getHealth());
                        attack.setText("Attack: " + Lyrasa.getAttack());
                        race.setText("Raza: Elfa");
                        type.setText("Tipo: Arquera");
                        behavior.setText("Personalidad: Silenciosa,\nprecisa y desconfiada.");
                        labelInvisibilizer(mana);
                        break;
                    case third:
                        graphics.drawImage(new Image("groshtaFace.png"), 740, 20);
                        name.setText(Groshta.getCharacterName());
                        health.setText("HP: " + Groshta.getHealth());
                        attack.setText("Attack: " + Groshta.getAttack());
                        race.setText("Raza: Goblin");
                        type.setText("Tipo: Arquero");
                        behavior.setText("Personalidad: Canalla,\nastuto y gloton.");
                        labelInvisibilizer(mana);
                        break;
                    case fourth:
                        graphics.drawImage(new Image("grishaFace.png"), 740, 20);
                        name.setText(Grisha.getCharacterName());
                        health.setText("HP: " + Grisha.getHealth());
                        attack.setText("Attack: " + Grisha.getAttack());
                        race.setText("Raza: Orco");
                        type.setText("Tipo: Arquera");
                        behavior.setText("Personalidad: Sadica,\ndespiadada y grosera.");
                        labelInvisibilizer(mana);
                        break;
                }
                break;
            case third:
                switch (row) {
                    case first:
                        graphics.drawImage(new Image("jaxFace.png"), 740, 20);
                        name.setText(Jax.getCharacterName());
                        health.setText("HP: " + Jax.getHealth());
                        attack.setText("Attack: " + Jax.getAttack());
                        race.setText("Raza: Humano");
                        type.setText("Tipo: Barbaro");
                        behavior.setText("Personalidad: Canalla\n, frio y gruñon.");
                        labelInvisibilizer(mana);
                        break;

                    case second:
                        graphics.drawImage(new Image("xaviruFace.png"), 740, 20);
                        name.setText(Xaviru.getCharacterName());
                        health.setText("HP: " + Xaviru.getHealth());
                        attack.setText("Attack: " + Xaviru.getAttack());
                        race.setText("Raza: Elfo");
                        type.setText("Tipo: Berserker");
                        behavior.setText("Personalidad: Rencoroso,\nserio y de pocas palabras.");
                        labelInvisibilizer(mana);
                        break;
                    case third:
                        graphics.drawImage(new Image("globiusFace.png"), 740, 20);
                        name.setText(Globius.getCharacterName());
                        health.setText("HP: " + Globius.getHealth());
                        attack.setText("Attack: " + Globius.getAttack());
                        race.setText("Raza: Goblin");
                        type.setText("Tipo: Berserker");
                        behavior.setText("Personalidad: Principe,\nmal educado e irascible.");
                        labelInvisibilizer(mana);
                        break;
                    case fourth:
                        graphics.drawImage(new Image("zorakFace.png"), 740, 20);
                        name.setText(Zorak.getCharacterName());
                        health.setText("HP: " + Zorak.getHealth());
                        attack.setText("Attack: " + Zorak.getAttack());
                        race.setText("Raza: Orco");
                        type.setText("Tipo: Berserker");
                        behavior.setText("Personalidad: Brutal,\nruidoso y caotico.");
                        labelInvisibilizer(mana);
                        break;
                }
                break;

            case fourth:
                switch (row) {
                    case first:
                        graphics.drawImage(new Image("linaFace.png"), 740, 20);
                        name.setText(Lina.getCharacterName());
                        health.setText("HP: " + Lina.getHealth());
                        mana.setText("MP: " + Lina.getMana());
                        attack.setText("Attack: " + Lina.getAttack());
                        race.setText("Raza: Humana");
                        type.setText("Tipo: Maga");
                        behavior.setText("Personalidad: Energica,\njusta y apasionada.");
                        break;

                    case second:
                        graphics.drawImage(new Image("heloroFace.png"), 740, 20);
                        name.setText(Heloro.getCharacterName());
                        health.setText("HP: " + Heloro.getHealth());
                        attack.setText("Attack: " + Heloro.getAttack());
                        mana.setText("MP: "+Heloro.getMana());
                        race.setText("Raza: Elfo");
                        type.setText("Tipo: Mago");
                        behavior.setText("Personalidad: Sabio,\npreocupado y justo.");
                        break;
                    case third:
                        graphics.drawImage(new Image("hobagFace.png"), 740, 20);
                        name.setText(Hobag.getCharacterName());
                        health.setText("HP: " + Hobag.getHealth());
                        attack.setText("Attack: " + Hobag.getAttack());
                        mana.setText("MP: "+Hobag.getMana());
                        race.setText("Raza: Goblin");
                        type.setText("Tipo: Maga");
                        behavior.setText("Personalidad: Celosa,\nvaliente y poco amigable.");
                        break;
                    case fourth:
                        graphics.drawImage(new Image("goldanFace.png"), 740, 20);
                        name.setText(Goldan.getCharacterName());
                        health.setText("HP: " + Goldan.getHealth());
                        mana.setText("MP: " + Goldan.getMana());
                        attack.setText("Attack: " + Goldan.getAttack());
                        race.setText("Raza: Orco");
                        type.setText("Tipo: Mago");
                        behavior.setText("Personalidad: Desalmado,\ncruel e impulsivo.");
                        break;
                        case fifth:
                        graphics.drawImage(new Image("azeliFace.png"), 740, 20);
                        name.setText(Azeli.getCharacterName());
                        health.setText("HP: " + Azeli.getHealth());
                        mana.setText("MP: " + Azeli.getMana());
                        attack.setText("Attack: " + Azeli.getAttack());
                        race.setText("Raza: Fenix humanoide ");
                        type.setText("Tipo: Sanadora");
                        behavior.setText("Personalidad: Valiente,\napasionada y optimista.");
                        break;
                }
                break;
            case fifth:
                switch (row) {
                    case first:
                        graphics.drawImage(new Image("alessandraFace.png"), 740, 20);
                        name.setText(Alessandra.getCharacterName());
                        health.setText("HP: " + Alessandra.getHealth());
                        attack.setText("Attack: " + Alessandra.getAttack());
                        mana.setText("MP: "+Alessandra.getMana());
                        race.setText("Raza: Humana");
                        type.setText("Tipo: Sanadora");
                        behavior.setText("Personalidad: Coqueta,\namable y extrovertida.");
                        break;

                    case second:
                        graphics.drawImage(new Image("lyffaFace.png"), 740, 20);
                        name.setText(Lyffa.getCharacterName());
                        health.setText("HP: " + Lyffa.getHealth());
                        attack.setText("Attack: " + Lyffa.getAttack());
                        mana.setText("MP: "+Lyffa.getMana());
                        race.setText("Raza: Elfa");
                        type.setText("Tipo: Sanadora");
                        behavior.setText("Personalidad: Princesa,\npervertida y sadica.");
                        break;
                    case third:
                        graphics.drawImage(new Image("higlobFace.png"), 740, 20);
                        name.setText(Higlob.getCharacterName());
                        health.setText("HP: " + Higlob.getHealth());
                        attack.setText("Attack: " + Higlob.getAttack());
                        mana.setText("MP: "+Higlob.getMana());
                        race.setText("Raza: Goblin");
                        type.setText("Tipo: Sanadora");
                        behavior.setText("Personalidad: Distraido,\nhalagador e inocente.");
                        break;
                    case fourth:
                        graphics.drawImage(new Image("gortonaFace.png"), 740, 20);
                        name.setText(Gortana.getCharacterName());
                        health.setText("HP: " + Gortana.getHealth());
                        attack.setText("Attack: " + Gortana.getAttack());
                        mana.setText("MP: "+Gortana.getMana());
                        race.setText("Raza: Orco");
                        type.setText("Tipo: Sanadora");
                        behavior.setText("Personalidad: Sabia,\nvieja y amargada.");
                        break;
                    case fifth:
                        labelInvisibilizer(mana);
                        graphics.drawImage(new Image("shiraFace.png"), 740, 20);
                        name.setText(Shira.getCharacterName());
                        health.setText("HP: " + Shira.getHealth());
                        attack.setText("Attack: " + Shira.getAttack());
                        race.setText("Raza: Gato humanoide");
                        type.setText("Tipo: Guerrera");
                        behavior.setText("Personalidad: Suicida,\nruidosa y aguerrida.");
                        break;
                }
                break;

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

            yes = createButton("Si", 32, 730, e -> fourActions(), font);
            yes.getStyleClass().add("combat-button");
            yes.setTextFill(Color.BLACK);

            no = createButton("No", 92, 730, e -> reset(), font);
            no.getStyleClass().add("combat-button");
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

        switch (col) {
            case first -> {
                switch (row) {
                    case first -> {
                        player[selectedCharacters] = Draven;
                        imageName = Draven.getImageName();
                    }
                    case second -> {
                        player[selectedCharacters] = Sirael;
                        imageName = Sirael.getImageName();
                    }
                    case third -> {
                        player[selectedCharacters] = Hobgrou;
                        imageName = Hobgrou.getImageName();
                    }
                    case fourth -> {
                        player[selectedCharacters] = Drekker;
                        imageName = Drekker.getImageName();
                    }
                }
            }
            case second -> {
                switch (row) {
                    case first -> {
                        player[selectedCharacters] = Cintya;
                        imageName = Cintya.getImageName();
                    }

                    case second ->{
                        player[selectedCharacters] = Lyrasa;
                        imageName = Lyrasa.getImageName();
                    }
                    case third ->{
                        player[selectedCharacters] = Groshta;
                        imageName = Groshta.getImageName();
                    }
                    case fourth ->{
                        player[selectedCharacters] = Grisha;
                        imageName = Grisha.getImageName();
                    }

                }

            }
            case third -> {
                switch (row) {
                    case first -> {
                        player[selectedCharacters] = Jax;
                        imageName = Jax.getImageName();
                    }

                    case second ->{
                        player[selectedCharacters] = Xaviru;
                        imageName = Xaviru.getImageName();
                    }
                    case third ->{
                        player[selectedCharacters] = Globius;
                        imageName = Globius.getImageName();
                    }
                    case fourth ->{
                        player[selectedCharacters] = Zorak;
                        imageName = Zorak.getImageName();
                    }

                }

            }
            case fourth -> {
                switch (row) {
                    case first -> {
                        player[selectedCharacters] = Lina;
                        imageName = Lina.getImageName();
                    }

                    case second ->{
                        player[selectedCharacters] = Heloro;
                        imageName = Heloro.getImageName();
                    }
                    case third ->{
                        player[selectedCharacters] = Hobag;
                        imageName = Hobag.getImageName();
                    }
                    case fourth ->{
                        player[selectedCharacters] = Goldan;
                        imageName = Goldan.getImageName();
                    }
                    case fifth ->{
                        player[selectedCharacters] = Azeli;
                        imageName = Azeli.getImageName();
                    }

                }

            }
            case fifth -> {
                switch (row) {
                    case first -> {
                        player[selectedCharacters] = Alessandra;
                        imageName = Alessandra.getImageName();
                    }

                    case second ->{
                        player[selectedCharacters] = Lyffa;
                        imageName = Lyffa.getImageName();
                    }
                    case third ->{
                        player[selectedCharacters] = Higlob;
                        imageName = Higlob.getImageName();
                    }
                    case fourth ->{
                        player[selectedCharacters] = Gortana;
                        imageName = Gortana.getImageName();
                    }

                    case fifth ->{
                        player[selectedCharacters] = Shira;
                        imageName = Shira.getImageName();
                    }

                }

            }


        }


            boolean alreadySelected = false;
        if (player[selectedCharacters].getHealth()< 1){
            player[selectedCharacters].setHealth(0);
            alreadySelected = true;
        }else {
            for (int i = 0; i < selectedCharacters; i++) {
                if (player[i].getImageName().equals(imageName)) {
                    alreadySelected = true;
                    break;
                }

            }
        }

            if (!alreadySelected) {
                player[selectedCharacters].setImageName(imageName);
                player[selectedCharacters].setX(64);
                player[selectedCharacters].setY(64);
                selected[selectedCharacters] = new SelectedCharacters(col, row, selectedCharacters, true);
            } else {
                selectedCharacters--;
            }

    }

    private static EventHandler<ActionEvent> fourActions(){
        animationTimer.stop();
        Campaign.initialize();
        AudioPlayer.playRoasterButtonSound();
        for (int i = 0; i <= 4; i++) {
            selected[i] = new SelectedCharacters();
        }
        cantMove = false;
        selectedCharacters = -1;
        if(message != null && yes != null && no != null){
            labelInvisibilizer(message);
            buttonInvisibilizer(yes, no);
        }
        return null;
    }
    private static void reset(){
        selectedCharacters = -1;
        cantMove = false;
        for (int i = 0; i <= 4; i++) {
            player[i] = new PlayerCharacter();
            selected[i] = new SelectedCharacters();
        }
        if(message != null && yes != null && no != null){
            labelInvisibilizer(message);
            buttonInvisibilizer(yes, no);
            animationTimer.start();
        }
    }
    public static PlayerCharacter[] getPlayer() {
        return player;
    }
    public static Scene getRoasterScene(){
        return roasterScene;
    }
}