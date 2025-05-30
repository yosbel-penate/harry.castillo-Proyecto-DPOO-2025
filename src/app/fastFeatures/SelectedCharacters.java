package app.fastFeatures;

public class SelectedCharacters {
    private int x;
    private int y;
    private int numberImage;
    private boolean characterActivated;

    public SelectedCharacters(int x, int y, int numberImage, boolean characterActivated) {
        this.x = x;
        this.y = y;
        this.numberImage = numberImage;
        this.characterActivated = characterActivated;
    }

    public SelectedCharacters() {
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

    public boolean isCharacterActivated() {
        return characterActivated;
    }

    public void setCharacterActivated(boolean characterActivated) {
        this.characterActivated = characterActivated;
    }

    public int getNumberImage() {
        return numberImage;
    }

    public void setNumberImage(int numberImage) {
        this.numberImage = numberImage;
    }
}
