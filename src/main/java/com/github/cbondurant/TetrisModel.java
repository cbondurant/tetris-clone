package com.github.cbondurant;

public class TetrisModel{
    public static int width = 10;
    public static int height = 24;
    public Tetromino current, held;

    private TetrominoBag bag;

    private int [][] grid;

    public TetrisModel(){
        this.grid = new int[width][height];
        this.bag = new TetrominoBag();
        draw();
    }

    public void draw(){
        this.current = bag.drawTile();
    }

	public void hardDrop() {
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
	}

	public void shiftLeft() {
	}

	public void rotateCW() {
	}

	public void rotateCCW() {
	}

	public void drop() {
	}
}