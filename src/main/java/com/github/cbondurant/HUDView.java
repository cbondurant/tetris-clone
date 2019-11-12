package com.github.cbondurant;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

class HUDView extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    TetrisModel model;
    Image tileSprites;

    HUDView(TetrisModel model){
        this.model = model;
        URL tileURL = getClass().getClassLoader().getResource("tiles.png");
        this.tileSprites = new ImageIcon(tileURL).getImage();
    }

    public Dimension getPreferredSize(){
        return new Dimension(150,300);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D)graphics;
        g.drawString("Goal: " + ((Integer)this.model.getClearGoal()).toString(), 5, 265);
        g.drawString("Score: " + ((Integer)this.model.score).toString(), 5, 275);
        g.drawString("Level: " + ((Integer)this.model.level).toString(), 5, 285);

        
        g.setColor(Color.BLACK);
        g.fillRect(0,0,150,150);
        Tetromino tet = model.bag.peekTile();
        for (Point pt : tet.tiles){
            g.drawImage(this.tileSprites, 
                               (pt.x+2) * TetrisView.TileWidth, (pt.y+2) * TetrisView.TileHeight,
                               (pt.x+3) * TetrisView.TileWidth, (pt.y+3) * TetrisView.TileHeight,
                               tet.index * TetrisView.TileWidth, 0,
                               (tet.index + 1) * TetrisView.TileWidth, TetrisView.TileHeight,
                               Color.WHITE, null);
                               
        }

        g.setColor(Color.BLACK);
        g.fillRect(0,100,150,150);
        if (model.held != null){
            for (Point pt : model.held.tiles){
                g.drawImage(this.tileSprites, 
                                   (pt.x+2) * TetrisView.TileWidth, 100 + ((pt.y+2) * TetrisView.TileHeight),
                                   (pt.x+3) * TetrisView.TileWidth, 100 + ((pt.y+3) * TetrisView.TileHeight),
                                   model.held.index * TetrisView.TileWidth, 0,
                                   (model.held.index + 1) * TetrisView.TileWidth, TetrisView.TileHeight,
                                   Color.WHITE, null);
                                   
            }
        }
    }
}