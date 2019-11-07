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
    }
}