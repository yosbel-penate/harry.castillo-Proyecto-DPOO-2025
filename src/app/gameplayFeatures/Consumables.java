package app.gameplayFeatures;

import domain.generalClasses.Inventory;

public class Consumables {
    private int x;
    private int y;
    private String image;
    private int quantity;
    private boolean drawAtMap;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isDrawAtMap() {
        return drawAtMap;
    }

    public void setDrawAtMap(boolean drawAtMap) {
        this.drawAtMap = drawAtMap;
    }


}
