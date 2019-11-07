package com.github.cbondurant;

import java.util.concurrent.ThreadLocalRandom;

class TetrominoBag{
    private Tetromino [] items = {};
    private int index;

    TetrominoBag(){
        this.index = 0;
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
}

class Tetromino{
    Point [] tiles;
    Point [][] rot_offsets;
    Point fulcrum, position;

    Tetromino(Point [] tiles, Point [][] offsets, Point fulcrum){
        this.tiles = tiles;
        this.rot_offsets = offsets;
        this.fulcrum = fulcrum;
        this.position = Point.Zero();
    }


}

class Point{
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void add(Point pt){
        this.x += pt.x;
        this.y += pt.y;
    }

    public void sub(Point pt){
        this.x -= pt.x;
        this.y -= pt.y;
    }

    static Point Down()  {return new Point (0,-1);}
    static Point Up()    {return new Point (0,1);}
    static Point Left()  {return new Point (-1,0);}
    static Point Right() {return new Point (1,0);}
    static Point Zero()  {return new Point (0,0);}
}