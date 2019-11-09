package com.github.cbondurant;

import java.util.concurrent.ThreadLocalRandom;
import java.awt.Color;

class TetrominoBag{
    // #region Tetromino Definition
    private Tetromino [] items = {
        new Tetromino( // L
            new Point [] { new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(1, -1)},
            new Point [][] {
                { new Point( 0, 0), new Point( 1, 0), new Point( 1, 1), new Point( 0, -2), new Point( 1, -2)},
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point(-1, 0), new Point(-1, 1), new Point( 0, -2), new Point(-1, -2)}
            }, Color.ORANGE),
        new Tetromino( // J
            new Point [] { new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(-1, -1)},
            new Point [][]    {
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point( 1, 0), new Point( 1, 1), new Point( 0, -2), new Point( 1, -2)},
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point(-1, 0), new Point(-1, 1), new Point( 0, -2), new Point(-1, -2)}
            }, Color.BLUE),
        new Tetromino( // S
            new Point []{ new Point(-1, 0), new Point(0, 0), new Point(0, -1), new Point(1, -1)},
            new Point [][]{
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point( 1, 0), new Point( 1, 1), new Point( 0, -2), new Point( 1, -2)},
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point(-1, 0), new Point(-1, 1), new Point( 0, -2), new Point(-1, -2)}
            }, Color.GREEN),
        new Tetromino( // Z
            new Point []{ new Point(-1, -1), new Point(0, 0), new Point(1, 0), new Point(0, -1)},
            new Point [][]{
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point( 1, 0), new Point( 1, 1), new Point( 0, -2), new Point( 1, -2)},
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point(-1, 0), new Point(-1, 1), new Point( 0, -2), new Point(-1, -2)}
            }, Color.RED),
        new Tetromino( // O
            new Point []{ new Point(0, -1), new Point(0, 0), new Point(1, 0), new Point(1, -1)},
            new Point [][]{
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 1), new Point( 0, 1), new Point( 0, 1), new Point( 0, 1), new Point( 0, 1)},
                { new Point(-1, 1), new Point(-1, 1), new Point(-1, 1), new Point(-1, 1), new Point(-1, 1)},
                { new Point(-1, 0), new Point(-1, 0), new Point(-1, 0), new Point(-1, 0), new Point(-1, 0)} 
        }, Color.YELLOW),
        new Tetromino( // I
            new Point [] { new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0)},
            new Point [][] {
                { new Point( 0, 0), new Point(-1, 0), new Point( 2, 0), new Point(-1, 0), new Point( 2, 0)},
                { new Point(-1, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, -1), new Point( 0, 2)},
                { new Point(-1, -1), new Point( 1, -1), new Point(-2, -1), new Point( 1, 0), new Point(-2, 0)},
                { new Point( 0, -1), new Point( 0, -1), new Point( 0, -1), new Point( 0, 1), new Point( 0, -2)}
        }, Color.CYAN),
        new Tetromino( // T
            new Point [] { new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, -1)},
            new Point [][] {
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point( 1, 0), new Point( 1, 1), new Point( 0, -2), new Point( 1, -2)},
                { new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0), new Point( 0, 0)},
                { new Point( 0, 0), new Point(-1, 0), new Point(-1, 1), new Point( 0, -2), new Point(-1, -2)}
       }, Color.MAGENTA)
    };
    //#endregion
    private int index;

    TetrominoBag(){
        this.index = 0;
        shuffle();
    }

    private void shuffle(){
        for (int i = 0; i < items.length; i++){
            int swap = ThreadLocalRandom.current().nextInt(i, items.length);
            Tetromino temp = this.items[i];
            this.items[i] = this.items[swap];
            this.items[swap] = temp;
        }
        this.index = 0;
    }

	public Tetromino drawTile() {
        if (index == items.length){
            shuffle(); // Sets index to 0
        }
		return this.items[index++];
    }
    
    public Tetromino peekTile(){
        if (index == items.length){
            shuffle();
        }
        return this.items[index];
    }
}

class Tetromino{
    Point [] tiles;
    Point [][] rot_offsets;
    Point position;
    Color color;
    private int rotState;

    Tetromino(Point [] tiles, Point [][] offsets, Color color){
        this.rotState = 0;
        this.tiles = tiles;
        this.rot_offsets = offsets;
        this.position = new Point(5,5);
        this.color = color;
    }

    public void zeroRotation(){
        while (rotState != 0){
            rotateCW();
        }
    }

    public void rotateCW(){
        // TODO: Add offset checks
        rotState = (rotState + 1) % 4;
        for (int i = 0; i < tiles.length; ++i){
            tiles[i] = new Point(-tiles[i].y, tiles[i].x);
        }
    }
    public Point [] cwOffsets(){
        Point [] offsets = new Point [5];
        for (int i = 0; i < offsets.length; ++i){
            offsets[i] = Point.sub(rot_offsets[rotState][i], rot_offsets[(rotState+1) % 4][i]);
        }
        return offsets;
    }

    // rotState+3 because of modulo wrapping
    public void rotateCCW(){
        rotState = (rotState + 3) % 4;
        for (int i = 0; i < tiles.length; ++i){
            tiles[i] = new Point(tiles[i].y , -tiles[i].x);
        }
    }
    public Point [] ccwOffsets(){
        Point [] offsets = new Point [5];
        for (int i = 0; i < offsets.length; ++i){
            offsets[i] = Point.sub(rot_offsets[rotState][i], rot_offsets[(rotState+3) % 4][i]);
        }
        return offsets;
    }
}
