package com.github.cbondurant;

import java.awt.Color;
import java.lang.Math;

public class TetrisModel{
    public static int width = 10;
    public static int height = 24;
    public int score;
    public int level;
    public static int goalMultiplier = 5;
    public int goal;
    private final static int[] clearScores = { 0, 1, 3, 5, 8 };// The array of points awarded for each number of line
                                                               // clears
    public Tetromino current, held;

    public boolean running;

    public TetrominoBag bag;
    private boolean holdAllowed;

    double dropDelay = 0.5d; // Gravity Delay
    static final double LOCK_DELAY = 0.5d; // This is constant
    double dropCounter = 0d; // timing mechanism
    double lockCounter = 0d;

    private Color[][] grid;

    public int getClearGoal(){
        return this.level * TetrisModel.goalMultiplier;
    }

    public TetrisModel() {
        this.grid = new Color[width][height];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                grid[i][j] = null;
            }
        }
        this.bag = new TetrominoBag();
        draw();
        this.holdAllowed = true;
        this.running = true;
        this.level = 1;
    }

    private void endGame() {
        this.running = false;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                grid[i][j] = Color.GRAY;
            }
        }
    }

    public void resetGame() {
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                grid[i][j] = null;
            }
        }
        draw();
        this.holdAllowed = true;
        this.running = true;
    }

    public void draw() {
        this.lockCounter = 0d;
        this.current = bag.drawTile();
        this.current.zeroRotation();
        this.current.position = new Point(4, 1);
        if (!currentValid()) {
            endGame();
        }
    }

    public void hardDrop() {
        while (currentValid()) {
            current.position.add(Point.Down());
        }
        current.position.add(Point.Up());
        lockPiece();
        draw();
    }

    public Point ghostPosition() {
        Point origin = new Point(current.position);
        while (currentValid()) {
            current.position.add(Point.Down());
        }
        current.position.add(Point.Up());
        Point ghost = current.position;
        current.position = origin;
        return ghost;
    }

    public void hold() {
        if (this.holdAllowed) {
            this.holdAllowed = false;
            Tetromino temp = this.current;
            if (this.held != null) {
                this.current = this.held;
                this.current.position = new Point(4, 1);
            } else {
                draw();
            }
            this.held = temp;
        }
    }

    public void shiftRight() {
        current.position.add(Point.Right());
        if (!currentValid()) {
            current.position.add(Point.Left());
        }
    }

    public void shiftLeft() {
        current.position.add(Point.Left());
        if (!currentValid()) {
            current.position.add(Point.Right());
        }
    }

    public void onUpdate(double deltaTime) {
        this.dropCounter += deltaTime;
        this.lockCounter += deltaTime;
        while (this.dropCounter > this.dropDelay) {
            this.dropCounter -= this.dropDelay;
            drop();
        }
    }

    public void rotateCW() {
        Point[] offsets = current.cwOffsets();
        current.rotateCW();
        for (int i = 0; i < offsets.length; ++i) {
            current.position.add(offsets[i]);
            if (currentValid()) {
                this.lockCounter = 0d;
                return;
            }
            current.position.sub(offsets[i]);
        }
        current.rotateCCW();
    }

    public void rotateCCW() {
        Point[] offsets = current.ccwOffsets();
        current.rotateCCW();
        for (int i = 0; i < offsets.length; ++i) {
            current.position.add(offsets[i]);
            if (currentValid()) {
                this.lockCounter = 0d;
                return;
            }
            current.position.sub(offsets[i]);
        }
        current.rotateCW();
    }

    public void drop() {
        current.position.add(Point.Down());
        if (!currentValid()) {
            current.position.add(Point.Up());
            if (this.lockCounter > TetrisModel.LOCK_DELAY) {
                lockPiece();
                draw();
            }
        }
        else{
            this.lockCounter = 0;
        }
    }

    public Color getTile(int x, int y) {
        return grid[x][y];
    }

    public void lockPiece() {
        Point pos = current.position;
        this.holdAllowed = true;
        for (int i = 0; i < current.tiles.length; ++i) {
            grid[pos.x + current.tiles[i].x][pos.y + current.tiles[i].y] = current.color;
        }
        clearLines();
    }

    private void clearLines() {
        // This goes row by row
        int clearCount = 0;
        for (int i = 0; i < height; ++i) {
            boolean rowClear = true;
            for (int j = 0; j < width; ++j) {
                if (this.grid[j][i] == null)
                    rowClear = false;
            }
            if (rowClear) {
                ++clearCount;
                for (int j = 0; j < width; ++j) {
                    this.grid[j][i] = null;
                    Color tmp = null;
                    for (int k = 0; k <= i; ++k) {
                        Color tmp2 = this.grid[j][k];
                        this.grid[j][k] = tmp;
                        tmp = tmp2;
                    }
                }
            }
        }
        this.score += TetrisModel.clearScores[clearCount];
        while (this.score > this.goal){
            System.out.println(this.level);
            ++this.level;
            this.goal += TetrisModel.goalMultiplier * this.level;
            this.dropDelay = Math.pow((0.8-((this.level-1)*0.007)),(this.level-1));
        }
        System.out.println(score);
    }

    private Boolean currentValid(){
        for (int i = 0; i < current.tiles.length; ++i){
            if (!locationValid(Point.add(current.tiles[i], current.position))){
                return false;
            }
        }
        return true;
    }

    private Boolean locationValid(Point pt){
        if (pt.x < 0 || pt.y < 0 || width <= pt.x || height <= pt.y){
            return false;
        }
        if (grid[pt.x][pt.y] != null){
            return false;
        }
        return true;
    }
}