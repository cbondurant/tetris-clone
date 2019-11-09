package com.github.cbondurant;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

class HUDView extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    TetrisModel model;

    HUDView(TetrisModel model){
        this.model = model;
    }

    public Dimension getPreferredSize(){
        return new Dimension(100,300);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Goal: " + ((Integer)this.model.getClearGoal()).toString(), 5, 265);
        g.drawString("Score: " + ((Integer)this.model.score).toString(), 5, 275);
        g.drawString("Level: " + ((Integer)this.model.level).toString(), 5, 285);

        g.setColor(Color.BLACK);
        g.fillRect(0,0,100,100);
        g.setColor(model.bag.peekTile().color);
        for (Point pt :model.bag.peekTile().tiles){
            g.fillRect((pt.x+2) * 20, (pt.y+2) * 20, 20,20);
        }

        g.setColor(Color.BLACK);
        g.fillRect(0,100,100,100);
        if (model.held != null){
            g.setColor(model.held.color);
            for (Point pt :model.held.tiles){
                g.fillRect((pt.x+2) * 20, ((pt.y+2) * 20)+100, 20,20);
            }
        }
    }
}