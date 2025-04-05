package game;

import dangers.Dangers;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int rows;
    private int cols;
    private int tileSize;
    private HexTile[][] tiles;
    private List<TerrainType> terrainTypes;
    private List<Dangers> dangers;
    private Character occupant;


    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.tiles = new HexTile[rows][cols];
        this.terrainTypes = new ArrayList<>();
        this.dangers = new ArrayList<>();
    }


    public void initializeTiles() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = new HexTile();
            }
        }
    }


    public void render() {
        System.out.println("Rendering board with " + rows + " rows and " + cols + " columns.");
    }
    public boolean getOccupant(int row, int col) {
        return false;
    }
    public boolean isOccupied(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Posición fuera de los límites del tablero.");
        }
        return tiles[row][col].getOccupant();
    }

    // Getters y setters
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public HexTile[][] getTiles() { return tiles; }
    public List<TerrainType> getTerrainTypes() { return terrainTypes; }
    public void setOccupant(Character occupant) { this.occupant = occupant; }
    public Character getOccupant() { return occupant; }


}
