package com.github.cbondurant;

final class Point{
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void add(Point pt){
        this.x += pt.x;
        this.y += pt.y;
    }
    static Point add(Point a, Point b){
        return new Point(a.x+b.x, a.y+b.y);
    }

    public void sub(Point pt){
        this.x -= pt.x;
        this.y -= pt.y;
    }
    static Point sub(Point a, Point b){
        return new Point(a.x-b.x, a.y-b.y);
    }

    static Point Down()  {return new Point (0,1);}
    static Point Up()    {return new Point (0,-1);}
    static Point Left()  {return new Point (-1,0);}
    static Point Right() {return new Point (1,0);}
    static Point Zero()  {return new Point (0,0);}
}