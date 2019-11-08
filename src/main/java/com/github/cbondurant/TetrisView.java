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
        return new Dimension(TetrisModel.width * 25, TetrisModel.height * 25);
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

    }
}