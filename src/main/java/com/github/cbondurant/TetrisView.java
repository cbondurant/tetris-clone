package com.github.cbondurant;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public final class TetrisView extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private TetrisModel model;
    private Image background, tileSprite;

    static int TileWidth = 25, TileHeight = 25;

    public TetrisView(TetrisModel model) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.model = model;
        URL backgroundURL = getClass().getClassLoader().getResource("background.png");
        this.background = new ImageIcon(backgroundURL).getImage();
        URL tileURL = getClass().getClassLoader().getResource("tiles.png");
        this.tileSprite = new ImageIcon(tileURL).getImage();
    }

    public Dimension getPreferredSize() {
        return new Dimension(TetrisModel.width * TetrisView.TileWidth, TetrisModel.height * TetrisView.TileHeight);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D)graphics;

        g.drawImage(this.background , 0, 0, null);


        // Gridlines
        g.setColor(Color.gray);
        for (int i = 0; i < TetrisModel.width; ++i){
            g.drawLine(i * TetrisView.TileWidth, 0, i * TetrisView.TileWidth, TetrisModel.height * TetrisView.TileHeight);
        }
        
        for (int i = 0; i < TetrisModel.height; ++i){
            g.drawLine(0, i * TetrisView.TileHeight, TetrisModel.width * TetrisView.TileWidth, i * TetrisView.TileHeight);
        }

        // Background Tiles
        for (int i = 0; i < TetrisModel.width; ++i){
            for (int j = 0; j < TetrisModel.height; ++j){
                if (model.getTile(i, j) != TetrisModel.EMPTY_TILE){
                    g.drawImage(this.tileSprite,
                                       i * TetrisView.TileWidth, j * TetrisView.TileHeight,
                                       (i+1) * TetrisView.TileWidth, (j+1) * TetrisView.TileHeight,
                                       TetrisView.TileWidth * model.getTile(i, j), 0,
                                       TetrisView.TileWidth * model.getTile(i, j) + TetrisView.TileWidth, TetrisView.TileHeight,
                                       Color.WHITE, null);
                }
                
            }
        }

        // Foreground Tiles
        for (int i = 0; i < model.current.tiles.length; ++i){
            int x = model.current.position.x + model.current.tiles[i].x;
            int y = model.current.position.y + model.current.tiles[i].y;
            
            g.drawImage(this.tileSprite,
                x * TetrisView.TileWidth, y * TetrisView.TileHeight,
                (x+1) * TetrisView.TileWidth, (y+1) * TetrisView.TileHeight,
                TetrisView.TileWidth * model.current.index, 0,
                TetrisView.TileWidth * model.current.index + TetrisView.TileWidth, TetrisView.TileHeight,
                Color.WHITE, null);
        }
        
        // Ghost
        Point ghostCenter = model.ghostPosition();
        for (int i = 0; i < model.current.tiles.length; ++i){
            int x = ghostCenter.x + model.current.tiles[i].x;
            int y = ghostCenter.y + model.current.tiles[i].y;
            g.fillRect((x*TetrisView.TileWidth)+5, (y*TetrisView.TileHeight)+5, TetrisView.TileWidth-10, TetrisView.TileHeight-10);
        }
    }
}