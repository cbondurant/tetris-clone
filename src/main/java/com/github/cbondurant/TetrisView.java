package com.github.cbondurant;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public final class TetrisView extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private TetrisModel model;

    static int TileWidth = 25, TileHeight = 25;

    public TetrisView(TetrisModel model) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.model = model;
    }

    public Dimension getPreferredSize() {
        return new Dimension(TetrisModel.width * TetrisView.TileWidth, TetrisModel.height * TetrisView.TileHeight);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < TetrisModel.width; ++i){
            for (int j = 0; j < TetrisModel.height; ++j){
                if (model.getTile(i, j) != null){
                    g.setColor(model.getTile(i, j));
                }else{
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * TetrisView.TileWidth, j * TetrisView.TileHeight, TetrisView.TileWidth, TetrisView.TileHeight);
            }
        }

        g.setColor(model.current.color);
        for (int i = 0; i < model.current.tiles.length; ++i){
            int x = model.current.position.x + model.current.tiles[i].x;
            int y = model.current.position.y + model.current.tiles[i].y;
            g.fillRect(x*TetrisView.TileWidth, y*TetrisView.TileHeight, TetrisView.TileWidth, TetrisView.TileHeight);
        }
        
        Point ghostCenter = model.ghostPosition();
        for (int i = 0; i < model.current.tiles.length; ++i){
            int x = ghostCenter.x + model.current.tiles[i].x;
            int y = ghostCenter.y + model.current.tiles[i].y;
            g.fillRect((x*TetrisView.TileWidth)+5, (y*TetrisView.TileHeight)+5, TetrisView.TileWidth-10, TetrisView.TileHeight-10);
        }

        g.setColor(Color.BLACK);
        for (int i = 0; i < TetrisModel.width; ++i){
            g.drawLine(i * TetrisView.TileWidth, 0, i * TetrisView.TileWidth, TetrisModel.height * TetrisView.TileHeight);
        }
        
        for (int i = 0; i < TetrisModel.height; ++i){
            g.drawLine(0, i * TetrisView.TileHeight, TetrisModel.width * TetrisView.TileWidth, i * TetrisView.TileHeight);
        }
    }
}