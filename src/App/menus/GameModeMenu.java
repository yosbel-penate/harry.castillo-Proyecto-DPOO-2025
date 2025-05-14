package app.menus;

import app.main.AudioPlayer;
import app.main.Game;
import app.main.Roaster.Roaster;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameModeMenu {
    private  static Button campaign=new Button("Campaign");
    private static Button pvp=new Button("PVP");
    private  static Button tournament=new Button("Tournament");
    private static Button back=new Button("Back");
    private static VBox gameModeMenu;

    public static void initGameModeMenu(){
        campaign.getStyleClass().add("menu-button");
        pvp.getStyleClass().add("menu-button");
        tournament.getStyleClass().add("menu-button");
        back.getStyleClass().add("menu-button");

        gameModeMenu=new VBox(10);
        gameModeMenu.getChildren().addAll(campaign,pvp,tournament,back);
        gameModeMenu.setAlignment(Pos.CENTER_RIGHT);
        gameModeMenu.setPadding(new Insets(0,0,0,10));

        hideMenu();

        campaign.setOnAction(_ -> {
            AudioPlayer.playButtonSound();
            AudioPlayer.stopMainMenu();
            Roaster.initialize();
        });
        back.setOnAction(_ ->{
            AudioPlayer.playButtonSound();
            hideMenu();
        });

    }

    public static VBox getGameModeMenu(){
        return gameModeMenu;
    }
    public static void hideMenu(){
        gameModeMenu.setVisible(false);
        gameModeMenu.setMouseTransparent(true);
    }
    public static void showMenu(){
        gameModeMenu.setVisible(true);
        gameModeMenu.setMouseTransparent(false);
    }
}
