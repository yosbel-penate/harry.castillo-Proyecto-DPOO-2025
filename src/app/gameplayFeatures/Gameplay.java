package app.gameplayFeatures;

import app.Game;
import app.fastFeatures.AudioPlayer;
import app.Roaster;
import app.menus.PauseMenu;
import domain.characters.*;
import domain.consumables.ManaPotion;
import domain.consumables.ShardOfAether;
import domain.consumables.VitalityPotion;
import domain.dangers.EtherStorm;
import domain.generalClasses.Inventory;
import domain.generalClasses.EnemyCharacter;
import domain.generalClasses.PlayerCharacter;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

import static app.fastFeatures.ButtonManager.*;
import static app.fastFeatures.LabelManager.createLabel;
import static app.fastFeatures.PublicVariables.*;
import static domain.generalClasses.EnemyCharacter.randomQuantity;

public class Gameplay {

    // Variables diversas.

    private static boolean grabConsumable;
    private static boolean activateRange;
    private static AnimationTimer gameplayTimer;
    private static ArrayList<Consumables> inventory;
    private static int previusX;
    private static int previusY;
    private static Scene gameplayScene;
    private  static Group root;
    private  static Canvas  canvas;
    private static GraphicsContext graphics;
    private static int actionPoints = 2;
    private static Random random = new Random();
    private static PlayerCharacter[] player;
    private static EnemyCharacter[] enemy;
    private static EnemyCharacter[][] enemi = new EnemyCharacter[5][5];
    private static int enemiSize;
    private static ArrayList<EnemyCharacter[]> enemies = new ArrayList<EnemyCharacter[]>(){};
    private static long time;
    private static Font font;
    private static boolean methodDone=false;
    public static int mission = 0;
    private static MediaPlayer mediaPlayer;
    private static boolean videoFinished = false;
    private static boolean selectCharacterToConsumable = false;
    private static boolean characterSelected = false;
    private static int selectedCharacter = 0;
    private static int squareX = 705;
    private static int squareY = 100;
    private static int nextCharacter = 40;

    private static Button useItem1;
    private static Button useItem2;
    private static Button useItem3;
    private static boolean usePotion = false;
    private static boolean useMana = false;
    private static boolean useShard = false;

    private static final Set<String> activeKeys = new HashSet<>();



    // Labels y botones.

    private static Label actionPointLabel;
    private static Label inventoryLabel;
    private static Label emptyInventoryLabel;
    private static Label potionQuantity;
    private static Label manaQuantity;
    private static Label shardQuantity;
    private static Label[] characterData=new Label[5];
    private static Label message;

    private static Button goToAnotherMission;

    /*  Los itemNumber son para la cantidad de
        items en el Inventario. En el ItemNumber1
        pueden poner, por ejemplo, la cantidad de
        pociones.
    */



    public static void initializeGameplay() {
        reviewMission();
        setupPlayer();
        setupWindow();
        setupAnotherConfigs();
        setupEnemies();
        playerMovement();
        labelConfigurations();
        buttonConfigurations();
        gameLoop();
    }



    private static void reviewMission() {
    }

    private static void setupPlayer() {
        player = Roaster.getPlayer();
    }
    private static void setupWindow() {
        root = new Group();
        gameplayScene = new Scene(root, screenWidth, screenHeight);
        gameplayScene.getStylesheets().add(Gameplay.class.getResource("/buttons.css").toExternalForm());
        canvas = new Canvas(screenWidth, screenHeight);
        root.getChildren().add(canvas);
        graphics = canvas.getGraphicsContext2D();
        window.setScene(gameplayScene);
        root.getChildren().add(PauseMenu.getPauseMenu());
        root.setEffect(PauseMenu.getBrightness());
    }

    private static void setupAnotherConfigs() {
        EtherStorm.randomPosition();
        TileMap.setPlayer(player);
        if (!(Inventory.isAlreadyCreated())) {
            inventory = Inventory.createInventory();

        }
    }




    private static void setupEnemies() {
        int enemia = new Random().nextInt(1, 6);
        if (mission == 3){
            enemia = 1;
        }
        switch (enemia){
            case 1:
                randomQuantity(enemi, 0, mission);
                enemiSize = 1;
                break;
            case 2:
                randomQuantity(enemi, 0, mission);
                randomQuantity(enemi, 1, mission);
                enemiSize = 2;
                break;
            case 3:
                randomQuantity(enemi, 0, mission);
                randomQuantity(enemi, 1, mission);
                randomQuantity(enemi, 2, mission);
                enemiSize = 3;
                break;
            case 4:
                randomQuantity(enemi, 0, mission);
                randomQuantity(enemi, 1, mission);
                randomQuantity(enemi, 2, mission);
                randomQuantity(enemi, 3, mission);
                enemiSize = 4;
                break;
            case 5:
                randomQuantity(enemi, 0, mission);
                randomQuantity(enemi, 1, mission);
                randomQuantity(enemi, 2, mission);
                randomQuantity(enemi, 3, mission);
                randomQuantity(enemi, 4, mission);
                enemiSize = 5;
                break;
        }

        for (int i = 0; i < enemiSize; i++){
            for (int j = 0; j < enemi[i].length; j++){
                if (enemi[i][j].isAlive()){
                    if(!Combat.isNoRandomPosition()){
                        randomPositionEnemies(enemi[i][0]);
                        enemi[i][j].setCharacter(player[0]);
                        enemi[i][j].setX(enemi[i][0].getX());
                        enemi[i][j].setY(enemi[i][0].getY());
                    }
                }
            }
        }
    }

    private static void randomPositionEnemies(EnemyCharacter enemyCharacter) {
        int xPos = random.nextInt(7, 11);
        int yPos = random.nextInt(1, 10);
        int firstCol = 64;
        int evenPeak = 32;
        /* xPos es de 7 a 11 para que el enemigo solo aparezca
            esas columnas. Y la yPos es para que aparezca en
            los hexagonos del 1 al 10 (esto varia si es par
            o impar).
         */

        if (xPos % 2 == 0) {
            yPos = (yPos * down) + evenPeak;
        } else {
            yPos = yPos * down;
        }
        /* En caso de que sea par, se le añade 32 para que
           se adecue a las columnas pares, ya que estas
           estan desplazadas trentaidos pixeles mas arriba
           que las impares.
        */

        xPos = firstCol + ((xPos-1) * right);

        enemyCharacter.setX(xPos);
        enemyCharacter.setY(yPos);
        // Se le pone al enemigo la posicion aleatoria generada.
    }



    private static void labelConfigurations() {
        font = new Font("Arial", 20);
        actionPointLabel = createLabel(705, 48,"Action Points: " + actionPoints, Color.WHITE, font);
        inventoryLabel = createLabel(770, 550, "Inventario", Color.WHITE, font);
        emptyInventoryLabel = createLabel(720, 600, "El inventario esta vacio", Color.WHITE, font);
        potionQuantity= createLabel(725,660,"x"+inventory.getFirst().getQuantity(),Color.WHITE,font);
        potionQuantity.setVisible(false);
        manaQuantity= createLabel(785,660,"x"+inventory.get(1).getQuantity(),Color.WHITE,font);
        manaQuantity.setVisible(false);
        shardQuantity = createLabel(845, 660, "x"+inventory.get(2).getQuantity(),Color.WHITE,font);
        shardQuantity.setVisible(false);
        message = createLabel(60, 720, "¡Les has ganado a todos! ¡Pero todavía faltan enemigos\nen las ruinas del desierto!", Color.WHITE, font);
        message.setVisible(false);
        int y = 100;
        for (int i=0;i<5;i++){
            if (player[i].havesMana()){
                characterData[i] = createLabel(705,y,player[i].getCharacterName()+": HP: "+player[i].getHealth()+" AP: "+ player[i].getAttack()+" MP: "+player[i].getMana(),Color.WHITE,font);
            }else {
                characterData[i] = createLabel(705, y, player[i].getCharacterName() + ": HP: " + player[i].getHealth() + " AP: " + player[i].getAttack(), Color.WHITE, font);
            }
            root.getChildren().add(characterData[i]);
            y+=40;
        }
        root.getChildren().addAll(actionPointLabel, message, inventoryLabel, emptyInventoryLabel,potionQuantity,manaQuantity, shardQuantity,actualTerrainData,upperActualTerrainData);
    }


    private static void buttonConfigurations() {
        Font buttonFont = new Font(14);
        goToAnotherMission = createButton("¡Vamos para alla!", 60, 790, e -> nextMission(), buttonFont);
        goToAnotherMission.getStyleClass().add("combat-button");

        useItem1 = createButton("Usar", 705, 690, e -> usePotionInMap(), buttonFont);
        useItem1.getStyleClass().add("combat-button");

        useItem2 = createButton("Usar", 765, 690, e -> useManaPotionInMap(), buttonFont);
        useItem2.getStyleClass().add("combat-button");

        useItem3 = createButton("Usar", 825, 690, e -> useShardInMap(), buttonFont);
        useItem3.getStyleClass().add("combat-button");

        buttonInvisibilizer(goToAnotherMission, useItem1, useItem2, useItem3);

        root.getChildren().addAll(goToAnotherMission,useItem1, useItem2, useItem3);
    }

    private static void usePotionInMap() {
        selectCharacterToConsumable = true;
        usePotion = true;
        buttonInvisibilizer(useItem2, useItem3);

    }

    private static void useManaPotionInMap() {
        if(doAllHaveMana()) {
            if (theresNoMoreManaCharacters()) {
                selectCharacterToConsumable = true;
                useMana = true;
                buttonInvisibilizer(useItem1, useItem3);
            }
        }

    }

    private static void useShardInMap() {
        selectCharacterToConsumable = true;
        useShard = true;
        buttonInvisibilizer(useItem1, useItem3);

    }

    private static void nextMission(){
        mission++;
        if(mission == 4){
            goToAnotherMission.setText("Creditos.");
            AudioPlayer.stopTileMap();
            resetAll();
            showCreditsVideo(window);
            return;
        }
        buttonInvisibilizer(goToAnotherMission);
        message.setVisible(false);
        for (int i = 0; i < enemiSize; i++){
            Arrays.fill(enemi[i], null);
        }
        Combat.setNoRandomPosition(false);
        Combat.setDropConsumable(false);
        for(Consumables consumable: inventory){
            consumable.setX(0);
            consumable.setY(0);
        }
        setupEnemies();


        player[0].setX(64);
        player[0].setY(64);
    }

    private static void showCreditsVideo(Stage window) {
        try {
            URL videoUrl = Gameplay.class.getResource("/final.mp4");

            assert videoUrl != null;
            Media media = new Media(videoUrl.toString());
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            mediaView.setPreserveRatio(true);
            mediaView.setFitWidth(1000);
            mediaView.setFitHeight(750);

            StackPane videoPane = new StackPane(mediaView);
            videoPane.setStyle("-fx-background-color: black;");

            Scene videoScene = new Scene(videoPane, 1000, 750);

            mediaPlayer.setOnEndOfMedia(() -> {
                videoFinished = true;
                window.setScene(Game.getMainScene());
                AudioPlayer.playMainMenu();
            });

            videoScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                if (!videoFinished) {
                    mediaPlayer.stop();
                    videoFinished = true;
                    window.setScene(Game.getMainScene());
                    AudioPlayer.playMainMenu();
                }
            });

            window.setScene(videoScene);
            window.show();

            mediaPlayer.play();

        } catch (Exception e) {
            e.printStackTrace();
            window.setScene(Game.getMainScene());
        }
    }

    private static void resetAll() {
        Arrays.fill(player, null);

        for (int i = 0; i<enemiSize; i++){
            Arrays.fill(enemi[i], null);
        }

        resetInventory();
        resetCharacters();
        Combat.setNoRandomPosition(false);
        gameplayTimer.stop();
        mission = 0;


    }

    private static void resetInventory() {
        for (int i = 0; i < inventory.size(); i++){
            inventory.get(i).setQuantity(0);
        }
    }

    private static void resetCharacters() {
        Alessandra = new Alessandra();
        Azeli = new Azeli();
        Cintya = new Cintya();
        Draven = new Draven();
        Drekker = new Drekker();
        Globius = new Globius();
        Goldan = new Goldan();
        Gortana = new Gortana();
        Grisha = new Grisha();
        Groshta = new Groshta();
        Heloro = new Heloro();
        Higlob = new Higlob();
        Hobag = new Hobag();
        Hobgrou = new Hobgrou();
        Jax = new Jax();
        Lina = new Lina();
        Lyffa = new Lyffa();
        Lyrasa = new Lyrasa();
        Shira = new Shira();
        Sirael = new Sirael();
        Xaviru = new Xaviru();
        Zorak = new Zorak();

    }

    private static void gameLoop() {
        long initialTime = System.nanoTime();
        gameplayTimer = new AnimationTimer() {

            //Este metodo es el que maneja los frames por segundo, que son 60.
            @Override
            public void handle(long actualTime) {
                time = (actualTime - initialTime) / 1000000000;
                if (time == 60) {
                    time = 0;
                }
                actionPointLabel.setText("Action Points: " + actionPoints);
                /* Aqui se calcula el tiempo, que luego se usara
                para que parpadee el rango.
                */
                draw();
                actualizeState();

            }
        };
        gameplayTimer.start();
    }

    private static void draw() {
        drawBackground();
        drawGameplaySquares();
        drawCampaignMap();
        drawRange();
        drawPlayer();
        drawEnemy();
        drawConsumableToUse();
        drawConsumableAtMap();
        drawConsumableAtInventory();
        EtherStorm.drawStorm(graphics);
    }


    private static void drawEnemy() {

        for (int i =0; i< enemiSize; i++){
            for (int j = 0; j < enemi[i].length; j++){
                if(enemi[i][0].isAlive()){
                    enemi[i][0].range(enemi[i][0], graphics, time);
                    enemi[i][0].draw(enemi[i][0], graphics);
                    rangeCollitionAllEnemies();
                }
            }
        }
    }

    private static void drawBackground() {

        graphics.drawImage(new Image("background.png"), 0, 0);
        // Fondo.

    }

    private static void drawGameplaySquares() {
        graphics.drawImage(new Image("statsSquare.png"), 680, 32);
        // Cuadro de las estadisticas.

        graphics.drawImage(new Image("dialogueSquare.png"), 32, 710);
        // Cuadro de los dialogos.

        graphics.drawImage(new Image("inventorySquare.png"), 680, 542);
        // Cuadro del inventario.

    }

    private static void drawCampaignMap() {
           TileMap.drawCampaignMap(graphics, mission);


    }

    private static void drawRange() {
        player[0].range(graphics, time);
    }

    private static void drawPlayer() {
        graphics.drawImage(new Image(player[0].getImageName()), player[0].getX(), player[0].getY());
    }

    private static void drawConsumableToUse() {
        if (selectCharacterToConsumable){
            graphics.drawImage(new Image("selectCharacter.png"), squareX, squareY);
        }
        if (characterSelected){
            selectCharacterToConsumable = false;
        }
    }

    private static void drawConsumableAtMap() {
        if (Combat.isDropConsumable()) {
            for (int i = 0; i < inventory.size(); i++){
                if ((inventory.get(i).isDrawAtMap())){
                    graphics.drawImage(new Image(inventory.get(i).getImage()), inventory.get(i).getX(), inventory.get(i).getY());
                }}

        }
    }

    private static void drawConsumableAtInventory() {
        if(inventory.getFirst().getQuantity() > 0) {
            potionQuantity.setText("x"+inventory.getFirst().getQuantity());
            graphics.drawImage(new Image(inventory.getFirst().getImage()), 705, 602);
            potionQuantity.setVisible(true);
        }
        if (inventory.get(1).getQuantity()>0){
            manaQuantity.setText("x"+inventory.get(1).getQuantity());
            graphics.drawImage(new Image(inventory.get(1).getImage()), 765, 602);
            manaQuantity.setVisible(true);
        }
        if (inventory.get(2).getQuantity()>0){
            shardQuantity.setText("x"+inventory.get(2).getQuantity());
            graphics.drawImage(new Image(inventory.get(2).getImage()), 825, 602);
            shardQuantity.setVisible(true);
        }

    }


    private static void actualizeState() {
        actualizeCharacterData();
        actualizeLabels();
        checkIfConsumableIsOutOfMap();
        checkIfYouLoose();
        checkIfYouWin();
        checkIfCharacterIsAlive();
        checkIfPlayerCollideWithConsumable();
        checkIfPlayerDoesntHaveAnyItem();
        checkIfDrawInventory();
        checkIfPlayerCollideWithEnemy();
        applyStormDamageOnce();
    }

    private static void checkIfConsumableIsOutOfMap() {
        for (Consumables consumable: inventory){
            if (consumable.getX() == 0 && consumable.getY() == 0){
                consumable.setDrawAtMap(false);
            }
        }
    }

    private static void checkIfPlayerDoesntHaveAnyItem() {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getQuantity() < 1) {
                switch (i) {
                    case 0:
                        buttonInvisibilizer(useItem1);
                        break;
                    case 1:
                        buttonInvisibilizer(useItem2);
                        break;
                    case 2:
                        buttonInvisibilizer(useItem3);
                        break;
                }
            } else {
                switch (i) {
                    case 0:
                        buttonVisibilizer(useItem1);
                        break;
                    case 1:
                        buttonVisibilizer(useItem2);
                        break;
                    case 2:
                        buttonVisibilizer(useItem3);
                        break;

                }
            }

        }
    }

    private static void actualizeLabels() {

    }

    private static void applyStormDamageOnce(){

        boolean condition=isPlayerInsideStorm();

        if (condition && !methodDone ){
            EtherStorm.damageStorm(player);
            methodDone=true;
        }
        if (!condition){
            methodDone=false;
        }
    }
    private static boolean isPlayerInsideStorm() {
        int x = player[0].getX();
        int y = player[0].getY();
        for (int i = 0; i <= 2; i++) {
            if (x == EtherStorm.xPosition[i] && y == EtherStorm.yPosition[i]) {
                return true;
            }
        }
        return false;
    }


    private static void checkIfYouLoose() {
        if(areAllCharactersDead()){
            AudioPlayer.stopIfPlaying("tileMap");
            Gameover.gameOver(gameplayScene, gameplayTimer);
        }
    }


    private static void checkIfYouWin() {
        if (areAllEnemiesDead()){

            message.setVisible(true);
            goToAnotherMission.setDisable(false);
            goToAnotherMission.setVisible(true);
            if(mission == 1){
                message.setText("¡Increible! ¡Les has ganado a todos los golems! ¡Pero\nmas adentro del desierto hay mas amenazas!");
            }
            if (mission == 2){
                message.setText("¡Les has ganado a esas... cosas! ¡¿De donde habran\n salido?!");
            }
            if (mission == 3){
                message.setText("¡Le has ganado a la maxima criatura del mal! \n¡Nos has salvado!");
            }
        }
    }

    private static boolean areAllCharactersDead() {
        for (PlayerCharacter p : player) {
            if (p.getHealth() > 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean doAllHaveMana(){
        for (PlayerCharacter p : player){
            if (p.havesMana()){
                return true;
            }
        }
        return false;
    }

    private static void checkIfCharacterIsAlive() {
        for (int i=0;i<5;i++){
            if(player[i].getHealth() < 1){
                characterData[i].setVisible(false);
            }else{
                characterData[i].setVisible(true);
            }
        }
    }

    public static void actualizeCharacterData() {
        for (int i=0;i<5;i++){
            if (player[i].havesMana()){
                characterData[i].setText(player[i].getCharacterName()+": HP: "+player[i].getHealth()+" AP: "+ player[i].getAttack()+" MP: "+player[i].getMana());
            }else {
                characterData[i].setText(player[i].getCharacterName() + ": HP: " + player[i].getHealth() + " AP: " + player[i].getAttack());
            }
        }
    }

    private static boolean areAllEnemiesDead() {
        for(int i = 0; i < enemiSize; i++) {
            for (int j = 0; j < enemi[i].length;j++) {
                if (enemi[i][0].isAlive()) {
                    return false;
                }
            }
        }
            return true;
        }


    private static void checkIfPlayerCollideWithConsumable() {
        player[0].collideWithConsumable(inventory);
    }
    private static void checkIfDrawInventory() {
        for (int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getQuantity() <= 0){
                emptyInventoryLabel.setVisible(false);
            }
        }
    }
    private static void checkIfPlayerCollideWithEnemy() {

        for (int i = 0; i<enemiSize;i++){
            for (int j = 0; j < enemi[i].length; j++){
                if (enemi[i][j].isAlive()){
                    player[0].collideRange(enemi[i][j]);
                }
            }
        }


        for (int i = 0; i<enemiSize;i++){
            for (int j = 0; j < enemi[i].length; j++){
                if (enemi[i][j].isAlive()){
                    enemi[i][j].collideRange(player[0]);
                }
            }
        }

        for (int i = 0; i<enemiSize;i++){
            for (int j = 0; j < enemi[i].length; j++){
                if (enemi[i][j].isAlive()){
                    if (player[0].collideRange(enemi[i][0])) {
                        Combat.setupCombat(enemi[i]);
                        EnemyCharacter.setCollidePlayer(false);
                        PlayerCharacter.setCollideEnemy(false);
                        return;
                    }
                }
            }
        }



    }


    public static void actualizeConsumablesAtInventary(){
       actualizePotionAtInventary();
       actualizeManaAtInventary();
       actualizeShardAetherAtInventary();

    }

    private static void actualizeShardAetherAtInventary() {
        if (inventory.get(2).getQuantity() < 1){
            shardQuantity.setVisible(false);
        }
        else {
            shardQuantity.setText("X"+inventory.get(2).getQuantity());
        }
    }

    public static void actualizePotionAtInventary(){
        if (inventory.getFirst().getQuantity() < 1){
            potionQuantity.setVisible(false);
        }
        else {
            potionQuantity.setText("X"+inventory.getFirst().getQuantity());
        }
    }

    public static void actualizeManaAtInventary(){
        if (inventory.get(1).getQuantity() < 1){
            manaQuantity.setVisible(false);
        }
        else {
            manaQuantity.setText("X"+inventory.get(1).getQuantity());
        }
    }



    private static void playerMovement() {
        /* Por cada tecla presionada, el codigo evaluará
        los puntos de accion y la posicion del jugador
        y determinara si se mueve o no se mueve.
        */
        gameplayScene.setOnKeyPressed(event -> activeKeys.add(event.getCode().toString()));

        gameplayScene.setOnKeyReleased(event -> {
            activeKeys.remove(event.getCode().toString());

            if (!activeKeys.isEmpty()) return; // Si hay más de una tecla activa, no mover al jugador.

            switch (event.getCode().toString()) {

                case "A":
                    // Tecla A mueve el jugador hacia la izquierda-abajo (X-48, Y+32)
                    if (selectCharacterToConsumable){
                        break;
                    }
                   previusX = player[0].getX();
                   previusY = player[0].getY();

                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == downLimitOdd) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == leftLimit ) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + left);
                            player[0].setY(player[0].getY() + diagonalDown);

                        }
                    break;
                case "D":
                    if (selectCharacterToConsumable){
                        break;
                    }
                    // Tecla D mueve el jugador hacia la derecha-abajo (X+48, Y+32)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == downLimitOdd) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == rightLimit) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + right);
                            player[0].setY(player[0].getY() + diagonalDown);
                        }

                    break;
                case "W":
                    if (selectCharacterToConsumable){
                        if(squareY == 80 || selectedCharacter == 0){
                            break;
                        }
                        selectedCharacter--;
                        squareY -= nextCharacter;
                        break;
                    }
                    // Tecla W mueve el jugador hacia arriba (Y-64)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == upLimitOdd) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getY() == upLimitEven) {
                                actionPoints++;
                                break;
                            }
                            player[0].setY(player[0].getY() + up);
                        }

                    break;
                case "S":
                    if (selectCharacterToConsumable){
                        if(squareY == 160 || selectedCharacter == 4){
                            break;
                        }
                        selectedCharacter++;
                        squareY += nextCharacter;
                        break;
                    }
                    // Tecla S mueve el jugador hacia abajo (Y+64)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == downLimitOdd) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getY() == downLimitEven) {
                                actionPoints++;
                                break;
                            }
                            player[0].setY(player[0].getY() + down);
                        }

                    break;
                case "Q":
                    if (selectCharacterToConsumable){
                        break;
                    }
                    // Tecla Q mueve el jugador hacia la izquierda-arriba (X-48, Y-32)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }
                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == upLimitEven) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == leftLimit) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + left);
                            player[0].setY(player[0].getY() + diagonalUp);
                        }
                    break;
                case "E":
                    if (selectCharacterToConsumable){
                        break;
                    }
                    // Tecla E mueve el jugador hacia la derecha-arriba (X+48, Y-32)
                    previusX = player[0].getX();
                    previusY = player[0].getY();
                        if (actionPoints != noPoints) {
                            actionPoints--;
                        } else {
                            break;
                        }

                        if (actionPoints >= noPoints) {
                            if (player[0].getY() == upLimitEven) {
                                actionPoints++;
                                break;
                            }
                            if (player[0].getX() == rightLimit) {
                                actionPoints++;
                                break;
                            }
                            player[0].setX(player[0].getX() + right);
                            player[0].setY(player[0].getY() + diagonalUp);
                        }

                    break;
                case "R":
                    if (selectCharacterToConsumable){
                        break;
                    }
                    actionPoints = 2;


                    for (int i = 0; i<enemiSize;i++){
                        for (int j = 0; j < enemi[i].length; j++){
                            if (enemi[i][j].isAlive() && j == 0){
                                enemi[i][0].move(player[0].getX(), player[0].getY(), enemi[i][0].getX(),enemi[i][0].getY(), enemi[i][0]);
                            }
                        }
                    }
                    EtherStorm.moveEtherStorm();
                    break;
                case "T":
                    activateRange = !activateRange;
                    break;
                case "P":
                        PauseMenu.managePauseMenu();
                    break;
                case "SPACE":
                    if(selectCharacterToConsumable){
                        if (usePotion){
                            if (player[selectedCharacter].getHealth() > 0) {
                                Consumables potionConsumable = inventory.getFirst();
                                VitalityPotion potion = new VitalityPotion();
                                player[selectedCharacter].setHealth(player[selectedCharacter].getHealth() + potion.getPointsAdded());
                                potionConsumable.setQuantity(potionConsumable.getQuantity() - 1);
                                if (potionConsumable.getQuantity() < 1) {
                                    potionQuantity.setVisible(false);
                                }
                                selectCharacterToConsumable = false;
                                usePotion = false;
                                break;
                            }
                            break;
                        }
                        if (useMana) {
                                if (player[selectedCharacter].getHealth() > 0) {
                                    if (player[selectedCharacter].havesMana()) {
                                        Consumables potionConsumable = inventory.get(1);
                                        ManaPotion potion = new ManaPotion();
                                        player[selectedCharacter].setMana(player[selectedCharacter].getMana() + potion.getPointsAdded());
                                        potionConsumable.setQuantity(potionConsumable.getQuantity() - 1);
                                        if (potionConsumable.getQuantity() < 1) {
                                            manaQuantity.setVisible(false);
                                        }
                                        selectCharacterToConsumable = false;
                                        useMana = false;
                                        break;
                                    }
                                    break;
                                }
                                break;
                        }
                        if (useShard){
                            if (player[selectedCharacter].getHealth() > 0) {
                                Consumables shardConsumable = inventory.get(2);
                                ShardOfAether shard = new ShardOfAether();
                                player[selectedCharacter].setAttack(player[selectedCharacter].getAttack() + shard.getPointsAdded());
                                shardConsumable.setQuantity(shardConsumable.getQuantity() - 1);
                                if (shardConsumable.getQuantity() < 1) {
                                    shardQuantity.setVisible(false);
                                }
                                selectCharacterToConsumable = false;
                                useShard = false;
                                break;
                            }
                            break;
                        }

                    }

                    break;

            }
        });


    }

    private static boolean theresNoMoreManaCharacters(){
        for (int i = 0; i < player.length; i++){
            if (player[i].havesMana() && player[i].getHealth() > 0){
                return true;
            }
        }
        return false;
    }


    private static void rangeCollitionAllEnemies() {
        for(int i= 0; i< enemiSize; i++){
            for (int j = 0; j < enemi[i].length; j++){
                int xDistanceEvP = enemi[i][0].getX() - player[0].getX();
                int yDistanceEvP =  enemi[i][0].getY() - player[0].getY();
                if (activateRange) {
                    if (time % 2 == 0) {
                        Image rangoSobrepuesto = new Image("overRangeTerrain.png");
                        if (xDistanceEvP == 96 && yDistanceEvP == 0) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + right, player[0].getY() + diagonalDown);
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + right, player[0].getY() + diagonalUp);
                        }
                        if (xDistanceEvP == -96 && yDistanceEvP == 0) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + left, player[0].getY() + diagonalDown);
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + left, player[0].getY() + diagonalUp);
                        }
                        if (xDistanceEvP == 96 && yDistanceEvP == 64) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + right, player[0].getY() + diagonalDown);
                        }
                        if (xDistanceEvP == 96 && yDistanceEvP == -64) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + right, player[0].getY() + diagonalUp);
                        }
                        if (xDistanceEvP == -96 && yDistanceEvP == 64) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + left, player[0].getY() + diagonalDown);
                        }
                        if (xDistanceEvP == -96 && yDistanceEvP == -64) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + left, player[0].getY() + diagonalUp);
                        }
                        if (xDistanceEvP == 0 && yDistanceEvP == 128) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + down);

                        }
                        if (xDistanceEvP == 48 && yDistanceEvP == 96) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + down);
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() + diagonalDown);
                        }
                        if (xDistanceEvP == -48 && yDistanceEvP == 96) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + down);
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() + diagonalDown);
                        }
                        if (xDistanceEvP == 0 && yDistanceEvP == -128) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + up);

                        }
                        if (xDistanceEvP == 48 && yDistanceEvP == -96) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + up);
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() + 48, player[0].getY() + diagonalUp);
                        }
                        if (xDistanceEvP == -48 && yDistanceEvP == -96) {
                            graphics.drawImage(rangoSobrepuesto, player[0].getX(), player[0].getY() + up);
                            graphics.drawImage(rangoSobrepuesto, player[0].getX() - 48, player[0].getY() + diagonalUp);
                        }
                    }
                }
            }
        }

    }

    public static boolean isGrabConsumable() {
        return grabConsumable;
    }

    public static void setGrabConsumable(boolean grabConsumable) {
        Gameplay.grabConsumable = grabConsumable;
    }

    public static boolean isActivateRange() {
        return activateRange;
    }


    public static void startGameplayTimer() {
        gameplayTimer.start();
    }

    public static void stopGameplayTimer() {
        gameplayTimer.stop();
    }

    public static ArrayList<Consumables> getInventory() {
        return inventory;
    }

    public static EnemyCharacter[] getEnemy() {
        return enemy;
    }

    public static int getPreviusX() {
        return previusX;
    }

    public static int getPreviusY() {
        return previusY;
    }

    public static Scene getGameplayScene() {
        return gameplayScene;
    }

    public static GraphicsContext getGraphics() {
        return graphics;
    }

    public static int getActionPoints() {
        return actionPoints;
    }

    public static void setActionPoints(int actionPoints) {
        Gameplay.actionPoints = actionPoints;
    }

    public static PlayerCharacter[] getPlayer() {
        return player;
    }

}