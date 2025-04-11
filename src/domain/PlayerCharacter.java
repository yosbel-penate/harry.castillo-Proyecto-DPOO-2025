package domain;

import app.Campaign;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
// Temporizador del rango.


public class PlayerCharacter {
    private int x;
    private int y;
    private String imageName;




    public void draw(GraphicsContext graphics) {
        graphics.drawImage(new Image(imageName), x, y);
    }
    //Se ejecuta por cada iteracion del gameLoop.


    public PlayerCharacter(int x, int y, String imageName) {
        this.x = x;
        this.y = y;
        this.imageName = imageName;
    }

    public int getX() {return x;}

    public void setX(int x) {this.x = x;}

    public int getY() {return y;}

    public void setY(int y) {this.y = y;}

}
