package com.github.cbondurant;

import java.awt.Color;

public class TetrisModel{
    public static int width = 10;
    public static int height = 24;
    public Tetromino current, held;

    private TetrominoBag bag;

    private Color [][] grid;

    public TetrisModel(){
        this.grid = new Color[width][height];
        for (int i = 0; i < grid.length; ++i){
            for (int j = 0; j < grid[i].length; ++j){
                grid[i][j] = null;
            }
        }
        this.bag = new TetrominoBag();
        draw();
    }

    public void draw(){
        this.current = bag.drawTile();
        this.current.position = new Point(4,1);
    }

	public void hardDrop() {
        while (currentValid()) {
            current.position.add(Point.Down());
        }
        current.position.add(Point.Up());
        lockPiece();
        draw();
	}

	public void hold() {
        Tetromino temp = this.current;
        if (this.held != null){
            this.current = this.held;
        }else{
            draw();
        }
        this.held = temp;
	}

	public void shiftRight() {
        current.position.add(Point.Right());
        if (!currentValid()){
            current.position.add(Point.Left());
        }
	}

	public void shiftLeft() {
        current.position.add(Point.Left());
        if (!currentValid()){
            current.position.add(Point.Right());
        }
	}

	public void rotateCW() {
        // TODO: Add collision check and wallkick
        current.rotateCW();
        if (!currentValid()){
            current.rotateCCW();
        }
	}

	public void rotateCCW() {
        // TODO: Add collision check and wallkick
        current.rotateCCW();
        if (!currentValid()){
            current.rotateCW();
        }
	}

	public void drop() {
        current.position.add(Point.Down());
        if (!currentValid()){
            current.position.add(Point.Up());
            lockPiece();
            draw();
        }
    }

    public Color getTile(int x, int y){
        return grid[x][y];
    }
    
    public void lockPiece(){
        Point pos = current.position; 
        for (int i = 0; i < current.tiles.length; ++i){
            grid[pos.x + current.tiles[i].x][pos.y + current.tiles[i].y] = current.color;
        }
        clearLines();
    }

    private void clearLines(){
        // This goes row by row
        for (int i = 0; i < height; ++i){
            boolean rowClear = true;
            for (int j = 0; j < width; ++j){
                if (grid[j][i] == null) rowClear = false;
            }
            if (rowClear){
                // TODO: Move Rows Down
            for (int j = 0; j < width; ++j){
                System.out.println("Line");
                grid[j][i] = null;
                Color tmp = null;
                for (int k = 0; k <= i; ++k){
                    Color tmp2 = grid[j][k];
                    grid[j][k] = tmp;
                    tmp = tmp2;
                }
            }    
            }
        }
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